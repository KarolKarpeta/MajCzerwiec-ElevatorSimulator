package com.codecool;

import java.util.Random;

public class Building {
    private final Floor[] floors;
    private final Elevator[] elevators;

    public Building(int nrOfFloors, int nrOfElevators) {
        this.floors = new Floor[nrOfFloors];
        this.elevators = new Elevator[nrOfElevators];
    }

    private void moveElevator(Floor destination, Elevator elevator) {
    }

    public void generatePerson() {
        Random randomNumber = new Random();

        int currentFloor = randomNumber.nextInt(floors.length);
        int destinationFloor = currentFloor;

        while (destinationFloor == currentFloor) {
            destinationFloor = randomNumber.nextInt(floors.length);
        }

        Person person = new Person(destinationFloor);

        floors[currentFloor].addPerson(person);

    }

    public static void main(String[] args) {
        Building kolejowa5na7 = new Building(8, 1);




    }

}
