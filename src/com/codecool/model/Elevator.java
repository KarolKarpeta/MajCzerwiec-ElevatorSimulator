package com.codecool.model;

import com.codecool.view.View;

import java.util.concurrent.LinkedBlockingQueue;


public class Elevator implements Runnable {
    private static final int CAPACITY = 10;
    private static final int SPEED = 100;
    private Floor floor;
    private String name;
    private LinkedBlockingQueue<Task> newTasks = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Person> people = new LinkedBlockingQueue<>();
    private LinkedBlockingQueue<Task> tasks = new LinkedBlockingQueue<>();//TODO to znowu na zwykłą LinkedList, i ona jest wewnętrzną strukturą windy
    private java.lang.Thread thread;
    //TODO specjalne pudełko/BlockingQueue? na nowe taski pochodzące z zewnątrz
    //TODO dodanie ElevatorState, do brana dostepnych wind
    public void activate() {
        if (!thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
            System.out.println("Thread started");
        }
    }

    public void addTask(Task task) {
        newTasks.add(task);
        View.confirmTaskAssignmentToElevator(this, task);
    }

    Elevator(Floor floor, int elevatorNumber) {
        this.floor = floor;
        this.name = "Winda" + elevatorNumber;
        this.thread = new Thread("thread"+name);
    }

    public Direction getCurrentDirection() {
        int currentFloorNumber = this.floor.getFloorNumber();
        Task currentTask = getCurrentTask();
        if (currentTask != null) {
            int nextFloorNumber = currentTask.getDestinationFloorNumber();
            if (nextFloorNumber < currentFloorNumber) {
                return Direction.GOING_DOWN;
            } else if (nextFloorNumber > currentFloorNumber) {
                return Direction.GOING_UP;
            } else {
                return Direction.WAITING;
            }
        } else {
            return Direction.WAITING;
        }
    }

    private Task getCurrentTask() {
        takeNewTask();
        if (hasFreeSpace()) {
            for (Task task : tasks) {
                if (!task.hasToLoad()) {
                    return task;
                }
            }
        }
        return tasks.peek();
    }

    public Floor getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

    private Direction getNewTaskDirection(int destinationFloorNumber) {
        int currentFloorNumber = this.floor.getFloorNumber();
        if (destinationFloorNumber < currentFloorNumber) {
            return Direction.GOING_DOWN;
        } else if (destinationFloorNumber > currentFloorNumber) {
            return Direction.GOING_UP;
        } else {
            return Direction.WAITING;
        }
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public Person[] getPeople() {
        return people.toArray(new Person[0]);
    }

    private void handleTask() {
        Direction currentDirection = getCurrentDirection();
        moveOneStep(currentDirection);
        unloadPeople();
        loadPeople(currentDirection);
    }

    private boolean hasTasksOrPassengers() {
        return !tasks.isEmpty() || !people.isEmpty();
    }

    private boolean hasFreeSpace() {
        return people.size() < CAPACITY;
    }

    //TODO kontroler odpala run(jeśli wcześniej winda nie miała tasków), a jak winda wykonuje ostatni task,to run się zamyka
    boolean isAvailable(int destinationFloorNumber) {
        Direction newTaskDirection = getNewTaskDirection(destinationFloorNumber);
        return (hasFreeSpace() && newTaskDirection.equals(getCurrentDirection()))
                || tasks.isEmpty();
    }

    public boolean isOperating() {
        if(thread != null){
            return thread.isAlive();
        }else{
            return false;
        }

    }

    public void loadPeople(Direction elevatorDirection) {
        Person newPassenger;
        Task newTask;
        while (hasFreeSpace() && !floor.isEmpty(elevatorDirection)) {
            newPassenger = loadPerson(elevatorDirection);
            newTask = newPassenger.getUnloadingTask();
            addTask(newTask);
        }
    }

    private Person loadPerson(Direction direction) {
        Person newPassenger;
        if (Direction.GOING_UP.equals(direction)) {
            newPassenger = floor.popPersonFromUpQueue();
        } else if (Direction.GOING_DOWN.equals(direction)) {
            newPassenger = floor.popPersonFromDownQueue();
        } else {
            newPassenger = floor.popPersonFromAnyQueue();
        }
        if (newPassenger != null) {
            View.personLoadMessage(this, newPassenger);
            people.add(newPassenger);
            removeLoadingTask(floor.getFloorNumber());
        }
        return newPassenger;
    }

    private void moveOneStep(Direction current) {
        try {
            Thread.sleep(SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Direction.GOING_UP.equals(current)) {
            floor = Building.getBuilding().getHigherFloor(this.floor);
        } else if (Direction.GOING_DOWN.equals(current)) {
            floor = Building.getBuilding().getLowerFloor(this.floor);
        }
    }

    public void unloadPeople() {
        Floor currentFloor = this.getFloor();
        for (Person person : people) {
            if (person.getDestinationFloor() == currentFloor.getFloorNumber()) {
                people.remove(person);
                currentFloor.addToTransportedPeople(person);
                //View.transportedPersonMessage(person, this);
            }
        }
        removeUnloadingTasks(currentFloor.getFloorNumber());
    }

    private void removeUnloadingTasks(int floorNumber) {
        for (Task task : tasks) {
            if (!task.hasToLoad() && task.getDestinationFloorNumber() == floorNumber) {
                tasks.remove(task);
            }
        }
    }

    private void removeLoadingTask(int floorNumber) {
        for (Task task : tasks) {
            if (task.hasToLoad() && task.getDestinationFloorNumber() == floorNumber) {
                tasks.remove(task);
            }
        }
    }

    @Override
    public void run() {
        System.out.println(name + " start");
        takeNewTask();
        while (hasTasksOrPassengers()) {
            //View.showFloors();
            View.showElevator(this);
            handleTask();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //View.showFloors();
        //View.showElevator(this);
        System.out.println(name + " shutdown");

    }

    private void takeNewTask() {
        Task newTask = newTasks.poll();
        if (newTask != null) {
            tasks.add(newTask);
        }
    }

}