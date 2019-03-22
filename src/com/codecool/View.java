package com.codecool;

public class View {

    public static void buildingCreationMessage(int nrOfFloors, int nrOfElevators) {
        System.out.println("Created building with " + nrOfFloors + " floors and " + nrOfElevators + " elevators. Here the game begins...");
    }

    public static void personCreationMessage(int floor, int destinationFloor) {
        System.out.println("Created person " + floor + " => " + destinationFloor);
    }

    public static void confirmTaskAssignmentToElevator(Elevator elevator, Task task) {
        System.out.print(elevator.getName() + " on " + elevator.getFloor().getFloorNumber() +
                " floor, received a task [" + task.getDestinationFloorNumber() + ", ");
        if(task.hasToLoad()){
            System.out.println("load]");
        }else{
            System.out.println("unload]");
        }
    }
}
