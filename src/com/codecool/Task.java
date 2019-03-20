package com.codecool;

public class Task {
    private int destinationFloorNumber;
    private boolean load;


    public Task(int destinationFloorNumber, boolean load){
        this.destinationFloorNumber = destinationFloorNumber;
        this.load = load;
    }

    public int getDestinationFloorNumber() {
        return destinationFloorNumber;
    }

}
