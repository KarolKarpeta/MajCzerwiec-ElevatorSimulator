package com.codecool;

import java.util.LinkedList;
import java.util.Random;

public class Building {
    private final Elevator[] elevators;
    private final Floor[] floors;
    private static Building thisBuilding;

    public Floor[] getFloors() {
        return floors;
    }


    public Elevator[] getElevators() {
        return elevators;
    }


    public static Building getBuilding() {
        return thisBuilding;
    } //model

    private static void createBuilding(int nrOfFloors, int nrOfElevators) {//model
        if (thisBuilding == null) {
            thisBuilding = new Building(nrOfFloors, nrOfElevators);
        } else {
            System.out.println("Building already exists!");
        }
    }

    private Building(int nrOfFloors, int nrOfElevators) {//model
        floors = new Floor[nrOfFloors];
        for (int i = 0; i < nrOfFloors; i++) {
            floors[i] = new Floor(i);
        }
        elevators = new Elevator[nrOfElevators];
        for (int i = 0; i < nrOfElevators; i++) {
            elevators[i] = new Elevator(floors[0], i);
        }
        View.buildingCreationMessage(nrOfFloors, nrOfElevators);
    }

    public Floor getLowerFloor(Floor floor) {
        return floors[floor.getFloorNumber() - 1];
    }//zabezpieczyć na nullpointerexception

    public Floor getHigherFloor(Floor floor) {
        return floors[floor.getFloorNumber() + 1];
    }//zaebpieczyc jw.

    void generatePerson() {//controller
        Random randomNumber = new Random();

        int currentFloor = randomNumber.nextInt(floors.length);
        int destinationFloor = currentFloor;

        while (destinationFloor == currentFloor) {
            destinationFloor = randomNumber.nextInt(floors.length);
        }

        Person person = new Person(destinationFloor);
        floors[currentFloor].addPersonToRelevantQueue(person);

    }

    private LinkedList<Elevator> getAvailableElevators(int destinationFloorNumber) {
        LinkedList<Elevator> availableElevators = new LinkedList<>();
        for (Elevator elevator : elevators) {
            if (elevator.isAvailable(destinationFloorNumber)) {
                availableElevators.add(elevator);
            }
        }
        return availableElevators;
    }

    private Elevator getElevatorWithSmallestNumberOfTasks() {
        int smallestNumberOfTasks = Integer.MAX_VALUE;
        Elevator leastBusy = elevators[0];
        for (Elevator elevator : elevators) {
            int numberOfTasks = elevator.getNumberOfTasks();
            if (numberOfTasks < smallestNumberOfTasks) {
                smallestNumberOfTasks = numberOfTasks;
                leastBusy = elevator;
            }
        }
        return leastBusy;
    }

    private Elevator getClosestElevator(LinkedList<Elevator> availableElevators, Task task) {
        int distanceToClosestElevator = Integer.MAX_VALUE;
        Elevator theClosestElevator = availableElevators.get(0);
        for (Elevator elevator : availableElevators) {
            int distanceFromPersonToElevator = Math.abs(task.getDestinationFloorNumber() - elevator.getFloor().getFloorNumber());
            if (distanceFromPersonToElevator < distanceToClosestElevator) {
                distanceToClosestElevator = distanceFromPersonToElevator;
                theClosestElevator = elevator;
            }
        }
        return theClosestElevator;
    }

    void assignTask(Task task) { //controller
        LinkedList<Elevator> availableElevators = getAvailableElevators(task.getDestinationFloorNumber());
        Elevator theChosenElevator;
        if (availableElevators.size() > 0) {
            theChosenElevator = getClosestElevator(availableElevators, task);
        } else {
            theChosenElevator = getElevatorWithSmallestNumberOfTasks();
        }
        theChosenElevator.addTask(task);
        if (!theChosenElevator.isOperating()) {
            theChosenElevator.activate();
        }
        View.confirmTaskAssignmentToElevator(theChosenElevator, task);
    }

    public static void main(String[] args) {//controller
        createBuilding(4, 1);
        PeopleSpawner peopleSpawner = new PeopleSpawner();
        Thread spawnThread = new Thread(peopleSpawner);
        spawnThread.start();
        try {
            spawnThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        peopleSpawner = new PeopleSpawner(20, 5);
        spawnThread = new Thread(peopleSpawner);
        spawnThread.start();
        try {
            spawnThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }
}
