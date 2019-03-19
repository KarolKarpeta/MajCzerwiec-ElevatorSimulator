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

    public static void main(String[] args) {
        Building kolejowa5na7 = new Building(8, 1);
    }

}
