package com.codecool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class Elevator implements Runnable {
    private static final int CAPACITY = 10;
    private static final int SPEED = 100;
    private Floor floor;
    private String name;
    private List<Task> newTasks = new LinkedList<>();
    private LinkedList<Person> people = new LinkedList<>();
    private LinkedBlockingQueue<Task> tasks = new LinkedBlockingQueue<>();//TODO to znowu na zwykłą LinkedList, i ona jest wewnętrzną strukturą windy
    private java.lang.Thread thread = new Thread(this);
    //TODO specjalne pudełko/BlockingQueue? na nowe taski pochodzące z zewnątrz

    void activate() {
        thread.start();
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    Elevator(Floor floor, int elevatorNumber) {
        this.floor = floor;
        this.name = "Winda" + elevatorNumber;
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

    private Task getCurrentTask() {
        //TODO jeśli jest pełna, to bierze pierwszy task polegający na wyładowaniu
        //bo teraz tylko bierze pierwszys task z brzegu i nie patrzy co bierze
        return this.tasks.peek();
    }

    Floor getFloor() {
        return floor;
    }


    String getName() {
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

    int getNumberOfTasks() {
        return tasks.size();
    }

    public Person[] getPeople() {
        return people.toArray(new Person[0]);
    }

    private void getTask(Person newPassenger) {
        //TODO jeśli winda już ma taki task, to go nie dodawaj
        tasks.add(new Task(newPassenger));
    }

    LinkedBlockingQueue<Task> getTasks() {
        return tasks;
    }

    private void handleTask() {
        //TODO: moveOneStep zajmuje 1000ms tylko jeśli winda się porusza
        moveOneStep();//TODO wywołuje moveStepUp albo moveDown albo nic nie wywołuje (jak winda stoi)
        unloadPeople();
        loadPeople();
    }

    private boolean hasFreeSpace() {
        return people.size() < CAPACITY;
    }

    //TODO kontroler odpala run(jeśli wcześniej winda nie miała tasków), a jak winda wykonuje ostatni task,to run się zamyka
    boolean isAvailable(int destinationFloorNumber) {
        Direction newTaskDirection = getNewTaskDirection(destinationFloorNumber);
        return (people.size() < CAPACITY && this.getCurrentDirection() == newTaskDirection)
                || tasks.isEmpty();
    }

    public boolean isOperating() {
        return getNumberOfTasks() > 0;
    }

    public void loadPeople() {
        Direction elevatorDirection = getCurrentDirection();
        while (hasFreeSpace() && !floor.isEmpty(elevatorDirection)) {
            loadPerson(getCurrentDirection());
            removeLoadingTasks(floor.getFloorNumber());
            getTask(people.peekLast());
            System.out.println("load person");
        }
    }

    private void loadPerson(Direction direction) {
        Person newPassenger;
        if (Direction.GOING_UP.equals(direction)) {
            newPassenger = floor.popPersonFromUpQueue();
        } else if (Direction.GOING_DOWN.equals(direction)) {
            newPassenger = floor.popPersonFromDownQueue();
        } else {
            newPassenger = floor.popPersonFromAnyQueue();
        }
        if (newPassenger != null) {
            people.add(newPassenger);
        }
    }

    private void moveOneStep() {
        Direction current = getCurrentDirection();
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
            }
        }
        removeCompletedTasks();
    }

    private void removeCompletedTasks() {
        for (Task task : this.tasks) {
            if (task.getDestinationFloorNumber() == this.getFloor().getFloorNumber()) {
                tasks.remove(task);
            }
        }
    }

    private void removeLoadingTasks(int floorNumber) {
        Task toRemove = new Task(floorNumber, true);
        while (tasks.remove(toRemove)) {

        }
    }

    @Override
    public void run() {
        System.out.println(name + " start");
        while (isOperating()) {//TODO dac tu zmienną/metodę, że jak wpisze q + [ENTER] to programIsRunnng zmienia sie na false
            View.showFloors();
            View.showElevator(this);
            this.handleTask();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " shutdown");
    }

}
