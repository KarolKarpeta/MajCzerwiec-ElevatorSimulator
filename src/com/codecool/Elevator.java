package com.codecool;

import java.util.LinkedList;

public class Elevator implements Runnable {
    private LinkedList<Person> people = new LinkedList<>();
    private static final int CAPACITY = 3;
    private LinkedList<Task> tasks = new LinkedList<>();
    private Floor floor;
    private String name;
    private static final int SPEED = 1000;
    private static boolean programIsRunning = true;

    String getName() {
        return name;
    }

    @Override
    public void run() {

        while (programIsRunning) {//TODO dac tu zmienną/metodę, że jak wpisze q + [ENTER] to programIsRunning zmienia sie na false
            if (getNumberOfTasks() > 0) { //asynchronous call - wygooglować
                //callback / event etc
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
        //TODO: moveOneStep zajmuje 1000ms tylko jeśli winda się porusza
        moveOneStep();//TODO wywołuje moveStepUp albo moveDown albo nic nie wywołuje (jak winda stoi)
        unloadPeople();
        loadPeople();
    }

    private Task getCurrentTask() {
        //TODO jeśli jest pełna, to bierze pierwszy task polegający na wyładowaniu
        //bo teraz tylko bierze pierwszys task z brzegu i nie patrzy co bierze
        return this.tasks.peek();
    }

    LinkedList<Task> getTasks() {
        return tasks;
    }

    private void moveOneStep() {
        Direction current = getCurrentDirection();
        if (Direction.GOING_UP.equals(current)) {
            moveStepUp();
        } else if (Direction.GOING_DOWN.equals(current)) {
            moveStepDown();
        }
    }

    private void moveStepUp() { //TODO połączyć 2 metody za pomocą parametru
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
                View.transportedPersonMessage(person, this);
            }
        }

        removeCompletedTasks();
    }

    private void removeCompletedTasks(){
        for (Task task: this.tasks) { //TODO druga struktura, bo w tym przypadku, jeśli ktoś coś dorzuci do tasks w trakcie iteracji po tej pętli, to program się wykrzaczy
            // trzeba zrobić drugą strukturę, w której będą sobie czekać dodawane taski
            if(task.getDestinationFloorNumber() == this.getFloor().getFloorNumber()) {
                tasks.remove(task);
            }
        }
    }


    private boolean hasFreeSpace() {
        return people.size() < CAPACITY;
    }

    private void loadPersonFromDownQueue(Elevator elevator) {
        people.add(floor.popPersonFromDownQueue(elevator));
    }

    private void loadPersonFromUpQueue(Elevator elevator) {
        people.add(floor.popPersonFromUpQueue(elevator));
    }

    private void loadPersonFromAnyQueue(Elevator elevator) {
        people.add(floor.popPersonFromAnyQueue(elevator));
    }

    private void getTask(Person newPassenger) {
        tasks.add(new Task(newPassenger));
    }

    public void loadPeople() {
        while (hasFreeSpace() && !floor.isEmpty()) {
            if (getCurrentDirection() == Direction.GOING_DOWN) {
                loadPersonFromDownQueue(this);
            } else if(getCurrentDirection() == Direction.GOING_UP){
                loadPersonFromUpQueue(this);
            }else{
                loadPersonFromAnyQueue(this);
            }
            getTask(people.peekLast());

            //View.personLoadMessage(this.getFloor(), this);
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
