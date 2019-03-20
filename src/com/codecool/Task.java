package com.codecool;

public class Task {
    public int getDestinationFloorNumber() {
        return destinationFloorNumber;
    }

    private int destinationFloorNumber;
    private boolean load;

    public Task(int destinationFloorNumber, boolean load){
        this.destinationFloorNumber = destinationFloorNumber;
        this.load = load;
    }
}
