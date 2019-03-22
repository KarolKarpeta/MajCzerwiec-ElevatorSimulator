package com.codecool;

public class View {

    public static void buildingCreationMessage(int nrOfFloors, int nrOfElevators) {
        System.out.println("Created building with " + nrOfFloors + " floors and " + nrOfElevators + " elevators. Here the game begins...");
    }

    public static void personCreationMessage(int floor, int destinationFloor){
        System.out.println("Created person on "+floor+" floor with destination floor "+destinationFloor);
    }

    public static void confirmTaskAssignmentToElevator(Elevator elevator, Task task){
        if (task.hasToLoad()) {
        System.out.println("Elevator:" + elevator.getName() + " on floor " + elevator.getFloor().getFloorNumber() +
                ", task => " + task.getDestinationFloorNumber() +
                " to take a person.");
        } else {
            System.out.println("Elevator:" + elevator.getName() + " loads person on floor " + elevator.getFloor().getFloorNumber() +
                    "and is to transport a person to " + task.getDestinationFloorNumber() +
                    " .");
        }
    }

    public static void transportedPersonMessage(Person person) {
        System.out.println("Person successfully transported to floor " + person.getDestinationFloor() + ".");
    }
}
