package com.codecool;

import java.util.LinkedList;
import java.util.Random;

public class Building {
    public Floor[] getFloors() {
        return floors;
    }

    private final Floor[] floors;

    public Elevator[] getElevators() {
        return elevators;
    }

    private final Elevator[] elevators;
    private static Building thisBuilding;

    public static Building getBuilding() {
        return thisBuilding;
    }

    private static void createBuilding(int nrOfFloors, int nrOfElevators) {
        if (thisBuilding == null) {
            thisBuilding = new Building(nrOfFloors, nrOfElevators);
        } else {
            System.out.println("Building already exists!");
        }
    }

    private Building(int nrOfFloors, int nrOfElevators) {
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
    }

    public Floor getHigherFloor(Floor floor) {
        return floors[floor.getFloorNumber() + 1];
    }

    void generatePerson() {
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

    void assignTask(Task task) {
        LinkedList<Elevator> availableElevators = getAvailableElevators(task.getDestinationFloorNumber());
        Elevator theChosenElevator;
        if (availableElevators.size() > 0) {
            theChosenElevator = getClosestElevator(availableElevators, task);
        } else {
            theChosenElevator = getElevatorWithSmallestNumberOfTasks();
        }
        if(theChosenElevator.isOperating()){
            theChosenElevator.addTask(task);
        }else{
            theChosenElevator.addTask(task);
            theChosenElevator.activate();
        }
    }

    public static void main(String[] args) {
        createBuilding(8, 1);
        PeopleSpawner peopleSpawner = new PeopleSpawner();
        (new Thread(peopleSpawner)).start();
    }
}
