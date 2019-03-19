package com.codecool;

public class Building {
    private final Floor[] floors;
    private final Elevator[] elevators;

    public Building(Floor[] floors, Elevator[] elevators) {
        this.floors = floors;
        this.elevators = elevators;
    }

    private void moveElevator(Floor destination, Elevator elevator) {
    }
}
