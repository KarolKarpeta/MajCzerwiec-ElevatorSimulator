package com.codecool;

public class Task {
    private int destinationFloorNumber;

    public boolean hasToLoad() {
        return load;
    }

    private boolean load;


    public Task(int destinationFloorNumber, boolean load){
        this.destinationFloorNumber = destinationFloorNumber;
        this.load = load;
    }

    public int getDestinationFloorNumber() {
        return destinationFloorNumber;
    }

}
