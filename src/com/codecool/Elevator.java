package com.codecool;

public class Elevator {
    private java.util.LinkedList<Person> people;
    private static final int CAPACITY = 3;
    private java.util.PriorityQueue<Floor> tasks;
    private Floor floor;

    public enum Direction {
        GOING_UP, WAITING, GOING_DOWN
    }

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

    private Direction newTaskDirection(int destinationFloorNumber) {
        int currentFloorNumber = this.floor.getFloorNumber();
        if (destinationFloorNumber < currentFloorNumber) {
            return Direction.GOING_DOWN;
        } else if (destinationFloorNumber > currentFloorNumber) {
            return Direction.GOING_UP;
        } else {
            return Direction.WAITING;
        }
    }

    private Direction currentDirection() {
        int currentFloorNumber = this.floor.getFloorNumber();
        if (tasks.peek() != null) {
            int nextFloorNumber = tasks.peek().getFloorNumber();
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

    public boolean isAvailable(Floor destinationFloor) {
        Direction newTaskDirection = newTaskDirection(destinationFloor.getFloorNumber());
        return (people.size() < CAPACITY && this.currentDirection() == newTaskDirection)
                || tasks.isEmpty();
    }

    public Elevator(Floor floor) {
        this.floor = floor;
    }
}
