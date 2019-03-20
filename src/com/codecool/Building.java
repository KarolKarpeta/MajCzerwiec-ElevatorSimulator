package com.codecool;

import java.util.Random;

public class Building {
    private final Floor[] floors;
    private final Elevator[] elevators;

    public Building(int nrOfFloors, int nrOfElevators) {
        floors = new Floor[nrOfFloors];
        for (int i = 0; i < nrOfFloors; i++) {
            floors[i] = new Floor(i);
        }
        elevators = new Elevator[nrOfElevators];
        for (int i = 0; i < nrOfElevators; i++) {
            elevators[i] = new Elevator(floors[0]);
        }
    }

    public Floor getLowerFloor(Floor floor){
        return floors[floor.getFloorNumber()-1];
    }

    public Floor getHigherFloor(Floor floor){
        return floors[floor.getFloorNumber()+1];
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
        floors[currentFloor].addPerson(person);
        View.personCreationMessage(currentFloor, destinationFloor);
    }

    public static void main(String[] args) {
        Building kolejowa5na7 = new Building(8, 1);
        kolejowa5na7.generatePerson();



    }

}
