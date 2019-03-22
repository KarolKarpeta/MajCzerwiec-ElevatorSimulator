package com.codecool;

import java.util.LinkedList;

public class Elevator implements Runnable {
    private LinkedList<Person> people = new LinkedList<>();
    private static final int CAPACITY = 3;
    private LinkedList<Task> tasks = new LinkedList<>();
    private Floor floor;
    private String name;
    private static final int SPEED = 1000;

    String getName() {
        return name;
    }

    @Override
    public void run() {

        while (true) {//TODO dac tu zmienną/metodę, że jak wpisze q + [ENTER] to programIsRunnng zmienia sie na false
            if (getNumberOfTasks() > 0) {
                this.handleTask();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleTask() {
        Task currentTask = getCurrentTask();//winda tylko patrzy, jaki ma task na 1. priorytecie
        //TODO: moveOneStep zajmuje 1000ms tylko jeśli winda się porusza
        moveOneStep(currentTask);//TODO wywołuje moveStepUp albo moveDown albo nic nie wywołuje (jak winda stoi)
        unloadPeople();
        loadPeople();//while loading people, elevator gets tasks from them
        //getTasksFromPeople();//TODO bierze taski od ludzi i daje je Budynkowi do handlowania (handluj z tym)
        System.out.println(this.name + " handle task");
    }

    private Task getCurrentTask() {
        //TODO jeśli jest pełna, to bierze pierwszy task poleający na wyładowaniu
        //bo teraz tylko bierze pierwszyst task z brzegu i nie patrzy co bierze
        return this.tasks.peek();
    }

    LinkedList<Task> getTasks() {
        return tasks;
    }

    private void moveOneStep(Task task) {
        Direction current = getCurrentDirection();
        if (current == Direction.GOING_UP) {
            moveStepUp();
        } else if (current == Direction.GOING_DOWN) {
            moveStepDown();
        }
    }

    private void moveStepUp() {
        try {
            Thread.sleep(SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.floor = Building.getBuilding().getHigherFloor(this.floor);
    }

    private void moveStepDown() {
        try {
            Thread.sleep(SPEED);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.floor = Building.getBuilding().getLowerFloor(this.floor);
    }

    public void unloadPeople() {
        Floor currentFloor = this.getFloor();
        for (Person person: people) {
            if(person.getDestinationFloor() == currentFloor.getFloorNumber()) {
                people.remove(person);
                currentFloor.addToTransportedPeople(person);
            }
        }

        removeCompletedTasks();
    }

    private void removeCompletedTasks(){
        for (Task task: this.tasks) {
            if(task.getDestinationFloorNumber() == this.getFloor().getFloorNumber()) {
                tasks.remove(task);
            }
        }
    }


    private boolean hasFreeSpace() {
        return people.size() < CAPACITY;
    }

    private Person loadPersonFromDownQueue() {
        people.add(floor.popPersonFromDownQueue());
        return people.peekLast();
    }

    private Person loadPersonFromUpQueue() {
        people.add(floor.popPersonFromUpQueue());
        return people.peekLast();
    }

    private void getTask(Person newPassenger) {
        tasks.add(new Task(newPassenger));
    }

    public void loadPeople() {
        //jeżeli winda jedzie do góry to weź ludzi z kolejki Up
        //jeżeli winda jedzie na dół to weź ludzi z kolejki Down
        //bierz ludzi jeden po drógim, dopóki masz wolne miejsca
        //weź taska od każdego pipola
        Person loadedPerson;
        while (hasFreeSpace() || !floor.isEmpty()) {
            if (getCurrentDirection() == Direction.GOING_DOWN) {
                loadedPerson = loadPersonFromDownQueue();
            } else {
                loadedPerson = loadPersonFromUpQueue();
            }
            getTask(loadedPerson);
        }
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

    private Direction getCurrentDirection() {
        int currentFloorNumber = this.floor.getFloorNumber();
        if (tasks.peek() != null) {
            int nextFloorNumber = tasks.peek().getDestinationFloorNumber();
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

    boolean isAvailable(int destinationFloorNumber) {
        Direction newTaskDirection = getNewTaskDirection(destinationFloorNumber);
        System.out.println(people);
        return (people.size() < CAPACITY && this.getCurrentDirection() == newTaskDirection)
                || tasks.isEmpty();
    }

    Elevator(Floor floor, int elevatorNumber) {
        this.floor = floor;
        this.name = "Winda" + elevatorNumber;
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    Floor getFloor() {
        return floor;
    }

    int getNumberOfTasks() {
        return tasks.size();
    }
}
