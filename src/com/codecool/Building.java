package com.codecool;

public class Building {
    private final Floor[] floors;
    private final Elevator[] elevators;

    public Building(int nrOfFloors, int nrOfElevators) {
        this.floors = new Floor[nrOfFloors];
        this.elevators = new Elevator[nrOfElevators];
    }

    private void moveElevator(Floor destination, Elevator elevator) {
    }
}
