package com.codecool;

public class View {

    public static void buildingCreationMessage(int nrOfFloors, int nrOfElevators) {
        System.out.println("Created building with " + nrOfFloors + " floors and " + nrOfElevators + " elevators. Here the game begins...\n");
    }

    public static void personCreationMessage(int floor, Person person){
        System.out.println("Created person " + person.getName() + " on "+floor+" floor with destination floor "+person.getDestinationFloor() + ".");
    }

    public static void confirmTaskAssignmentToElevator(Elevator elevator, Task task){
        if (task.hasToLoad()) {
        System.out.println("Elevator: " + elevator.getName() + " on floor " + elevator.getFloor().getFloorNumber() +
                ", task => " + task.getDestinationFloorNumber() +
                " to take a person.");
        } else {
            System.out.println("Elevator:" + elevator.getName() + " loads person on floor " + elevator.getFloor().getFloorNumber() +
                    "and is to transport a person to " + task.getDestinationFloorNumber() +
                    " .");
        }
    }

    public static void transportedPersonMessage(Person person) {
        System.out.println("Person "+ person.getName() + " successfully transported to floor " + person.getDestinationFloor() + ".");
    }

    public static void personLoadMessage(Floor floor, Elevator elevator, Person person) {
        System.out.println("Person " + person.getName() + " onboarded on floor " + floor.getFloorNumber() + " by Elevator: " + elevator.getName());
    }
}
