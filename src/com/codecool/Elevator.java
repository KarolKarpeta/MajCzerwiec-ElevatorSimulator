package com.codecool;

import java.util.PriorityQueue;
import java.util.List;
import java.util.LinkedList;

public class Elevator implements Runnable {
    private LinkedList<Person> people = new LinkedList<>();
    private static final int CAPACITY = 3;
    private LinkedList<Task> tasks = new LinkedList<>();
    private Floor floor;
    private String name;

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
        Task currentTask = popTask();//it also removes Task from list
        this.goToFloor(currentTask.getDestinationFloorNumber());
        if (currentTask.hasToLoad()) {
            this.loadPeople();//while loading people, elevator gets tasks from them
        } else {
            this.unloadPeople();
        }
        System.out.println(this.name + " handle task");
    }


    private Task popTask() {
        return this.tasks.remove();
    }

    LinkedList<Task> getTasks() {
        return tasks;
    }

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
