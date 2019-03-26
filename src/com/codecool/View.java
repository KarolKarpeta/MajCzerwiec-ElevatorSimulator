package com.codecool;

import java.util.concurrent.LinkedBlockingQueue;

public class View {

    public static void buildingCreationMessage(int nrOfFloors, int nrOfElevators) {
        System.out.println("Building: " + nrOfFloors + " floors and " + nrOfElevators + " elevators. Here the game begins...\n");
    }

    public static void confirmTaskAssignmentToElevator(Elevator elevator, Task task) {
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

    public static void personCreationMessage(int floor, int destinationFloor) {
        System.out.println("Created person " + floor + " => " + destinationFloor);
    }

    public static void personCreationMessage(int floor, Person person) {
        System.out.println("Created person " + person.getName() + " on " + floor + " floor with destination floor " + person.getDestinationFloor() + ".");
    }

    public static void personLoadMessage(Elevator elevator, Person person) {
        System.out.println("Person " + person.getName() + " onboarded on floor " + elevator.getFloor().getFloorNumber() + " by " + elevator.getName() + ".");
    }

    public static void print(Task task) {
        if (task == null) {
            System.out.print("null");
            return;
        }
        System.out.print("[" + task.getDestinationFloorNumber());
        if (task.hasToLoad()) {
            System.out.print(", load], ");
        } else {
            System.out.print(", unload], ");
        }
    }

    public static void print(Person[] people) {
        System.out.print("People:");
        for (Person person : people) {
            System.out.print(" " + person.getDestinationFloor());
        }
        System.out.println();
    }

    public static void print(LinkedBlockingQueue<Task> tasks) {
        for (Task task : tasks) {
            print(task);
        }
        System.out.println();
    }

    public static void showElevator(Elevator elevator) {
        System.out.println(elevator.getName() + " on " + elevator.getFloor().getFloorNumber() + " floor, direction:" + elevator.getCurrentDirection());
        System.out.print("Tasks: ");
        print(elevator.getTasks());
        print(elevator.getPeople());
        System.out.println("#############################################################");
    }

    public static void showFloors() {
        Building kolejowa = Building.getBuilding();
        Floor[] floors = kolejowa.getFloors();
        for (int i = floors.length - 1; i >= 0; i--) {
            for (Elevator elevator : kolejowa.getElevators()) {
                if (elevator.getFloor().getFloorNumber() == floors[i].getFloorNumber())
                    System.out.print("#");
            }
            System.out.println("\tFloor " + floors[i].getFloorNumber() + " Up:" + floors[i].getUpQueue()
                    + " Down:" + floors[i].getDownQueue() + " Transported: " + floors[i].getTransportedPeople());
        }
    }

    public static void transportedPersonMessage(Person person, Elevator elevator) {
        System.out.println("Person " + person.getName() + " successfully transported to floor " + person.getDestinationFloor() + " by " + elevator.getName() + ".");
    }
}
