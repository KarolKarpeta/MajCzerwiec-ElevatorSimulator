package com.codecool;

import java.util.LinkedList;
import java.util.Random;

public class Building {
    private final Floor[] floors;
    private final Elevator[] elevators;
    private static Building thisBuilding;

    public static Building getBuilding() {
        return thisBuilding;
    }

    private static Building createBuilding(int nrOfFloors, int nrOfElevators) {
        if (thisBuilding == null) {
            thisBuilding = new Building(nrOfFloors, nrOfElevators);
        } else {
            System.out.println("Building already exists!");
        }
        return thisBuilding;

    }

    private Building(int nrOfFloors, int nrOfElevators) {
        floors = new Floor[nrOfFloors];
        for (int i = 0; i < nrOfFloors; i++) {
            floors[i] = new Floor(i);
        }
        elevators = new Elevator[nrOfElevators];
        for (int i = 0; i < nrOfElevators; i++) {
            elevators[i] = new Elevator(floors[0]);
        }
    }

    public Floor getLowerFloor(Floor floor) {
        return floors[floor.getFloorNumber() - 1];
    }

    public Floor getHigherFloor(Floor floor) {
        return floors[floor.getFloorNumber() + 1];
    }

    private void sendElevator(Floor destination, Elevator elevator) {
        //budynek daje polecenie windzie, że ma jechać na dane piętro
    }

    public void generatePerson() {
        Random randomNumber = new Random();

        int currentFloor = randomNumber.nextInt(floors.length);
        int destinationFloor = currentFloor;

        while (destinationFloor == currentFloor) {
            destinationFloor = randomNumber.nextInt(floors.length);
        }

        Person person = new Person(destinationFloor);
        floors[currentFloor].addPersonToRelevantQueue(person);

    }

    public static void main(String[] args) {
        Building kolejowa5na7 = createBuilding(8, 1);
        SpawnPeople spawnPeople = new SpawnPeople();
        (new Thread(spawnPeople)).start();
        System.out.println("Coś");
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

    /**
     * Sends task to the chosen Elevator
     *
     * @param task
     */
    public void handleTask(Task task) {
        //wybieramy windę
        //weż dostępne windy DONE
        LinkedList<Elevator> availableElevators = getAvailableElevators(task.getDestinationFloorNumber());
        Elevator theChosenElevator;
        if (availableElevators.size() > 0) {
            int distanceToClosestElevator = Integer.MAX_VALUE;

            for (Elevator elevator : availableElevators) {
                int distanceFromPersonToElevator = Math.abs(task.getStartFloorNumber() - elevator.getFloor().getFloorNumber());

                if (distanceFromPersonToElevator < distanceToClosestElevator) {
                    distanceToClosestElevator = distanceFromPersonToElevator;
                    //znajdź odpowiednią windę!!!!!
                }
            }
            //spośród dostępnych wind weź tę, która jest najbliżej

        } else {//jak nie ma dostępnych wind:
            int smallestNumberOfTasks = Integer.MAX_VALUE;

            for (Elevator elevator: elevators) {
                int numberOfTasks = elevator.getNumberOfTasks();

                if(numberOfTasks < smallestNumberOfTasks) {
                    smallestNumberOfTasks = numberOfTasks;
                    //znajdź odpowiednią windę!!!!!
                }
            }


            //weź windę, która ma najmniej tasków
        }

        //wybrana winda otrzymuje Task
        theChosenElevator.addTask(task);
    }

}
