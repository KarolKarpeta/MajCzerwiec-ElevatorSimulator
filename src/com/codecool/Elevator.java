package com.codecool;

import java.util.PriorityQueue;
import java.util.LinkedList;


public class Elevator {
    private LinkedList<Person> people;
    private static final int CAPACITY = 3;
    private PriorityQueue<Task> tasks;
    private Floor floor;

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

    public boolean isAvailable(int destinationFloorNumber) {
        Direction newTaskDirection = getNewTaskDirection(destinationFloorNumber);
        return (people.size() < CAPACITY && this.getCurrentDirection() == newTaskDirection)
                || tasks.isEmpty();
    }

    public Elevator(Floor floor) {
        this.floor = floor;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public Floor getFloor() {
        return floor;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }
}
