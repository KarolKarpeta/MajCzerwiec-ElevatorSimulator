package com.codecool;

public class Task {
    public int getDestinationFloorNumber() {
        return destinationFloorNumber;
    }

    int destinationFloorNumber;
    int startFloorNumber;

    public Task(int startFloorNumber, int destinationFloorNumber){
        this.startFloorNumber = startFloorNumber;
        this.destinationFloorNumber = destinationFloorNumber;
    }
}
