package com.codecool;

public class View {

    public static void buildingCreationMessage(int nrOfFloors, int nrOfElevators) {
        System.out.println("Created building with " + nrOfFloors + " floors and " + nrOfElevators + " elevators. Here the game begins...");
    }

    public static void personCreationMessage(int floor, int destinationFloor){
        System.out.println("Created person on "+floor+" floor with destination floor "+destinationFloor);
    }

    public static void confirmTaskAssignmentToElevator(Elevator elevator, Task task){
        System.out.println("Elevator:" + elevator.getName() + " that is on floor " + elevator.getFloor().getFloorNumber() +
                ", received a task to go to " + task.getDestinationFloorNumber() +
                " floor and load/trasport and unload person (true/false): " + task.hasToLoad());

    }
}
