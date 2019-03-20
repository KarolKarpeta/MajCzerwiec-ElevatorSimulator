package com.codecool;

public class Elevator {
    private static final int GOING_UP = 1;
    private static final int WAITING = 0;
    private static final int GOING_DOWN = -1;
    private java.util.LinkedList<Person> people;
    private static final int CAPACITY = 3;
    private java.util.PriorityQueue<Floor> tasks;
    private Floor floor;

    //bierze piętro z PriorityQueue i wyładowuje i ładuje ludzi,
    //a jak masz pusta kolejke to stoi gdzie stała
    //jak ma pełne capacity to
    public void moveUp(Floor floor) {

    }

    public void moveDown(Floor floor) {

    }

    public void unloadPeople() {
    }

    public void loadPeople() {
    }

    private int newTaskDirection(int destinationFloorNumber) {
        int currentFloorNumber = this.floor.getFloorNumber();
        if (destinationFloorNumber < currentFloorNumber) {
            return GOING_DOWN;
        } else if (destinationFloorNumber > currentFloorNumber) {
            return GOING_UP;
        } else {
            return WAITING;
        }
    }

    private int currentDirection() {
        int currentFloorNumber = this.floor.getFloorNumber();
        if (tasks.peek() != null) {
            int nextFloorNumber = tasks.peek().getFloorNumber();
            if (nextFloorNumber < currentFloorNumber) {
                return GOING_DOWN;
            } else if (nextFloorNumber > currentFloorNumber) {
                return GOING_UP;
            } else {
                return WAITING;
            }
        } else {
            return WAITING;
        }

    }

    public boolean isAvailable(Floor destinationFloor) {
        int newTaskDirection = newTaskDirection(destinationFloor.getFloorNumber());
        return (people.size() < CAPACITY && this.currentDirection() == newTaskDirection)
                || tasks.isEmpty();
    }

    public Elevator(Floor floor) {
        this.floor = floor;
    }
}
