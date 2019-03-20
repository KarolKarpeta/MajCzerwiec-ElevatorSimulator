package com.codecool;

import java.util.PriorityQueue;

public class Elevator {
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

    public boolean isAvailable(int destinationFloorNumber) {
        Direction newTaskDirection = getNewTaskDirection(destinationFloorNumber);
        return (people.size() < CAPACITY && this.getCurrentDirection() == newTaskDirection)
                || tasks.isEmpty();
    }

    public Elevator(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }
}
