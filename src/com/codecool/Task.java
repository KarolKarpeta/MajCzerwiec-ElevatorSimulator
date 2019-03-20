package com.codecool;

public class Task {
    private int destinationFloorNumber;
    private int startFloorNumber;

    public Task(int startFloorNumber, int destinationFloorNumber){
        this.startFloorNumber = startFloorNumber;
        this.destinationFloorNumber = destinationFloorNumber;
    }

    public int getDestinationFloorNumber() {
        return destinationFloorNumber;
    }

    public int getStartFloorNumber() {
        return startFloorNumber;
    }
}
