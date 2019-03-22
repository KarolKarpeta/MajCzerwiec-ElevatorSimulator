package com.codecool;

public class View {


    public static void print(Task task){
        System.out.print("["+task.getDestinationFloorNumber());
        if(task.hasToLoad()){
            System.out.print(", load], ");
        }else{
            System.out.print(", unload], ");
        }
    }

    public static void print(Person[] people){
        System.out.print("People:");
        for(Person person : people){
            System.out.print(" " + person.getDestinationFloor());
        }
        System.out.println();
    }

    public static void showElevator(Elevator elevator){
        System.out.println(elevator.getName() + " on " + elevator.getFloor().getFloorNumber()+" floor");
        System.out.print("Tasks: ");
        for(Task task : elevator.getTasks()){
            print(task);
        }
        System.out.println();
        print(elevator.getPeople());
        System.out.println("#############################################################");
    }

    public static void buildingCreationMessage(int nrOfFloors, int nrOfElevators) {
        System.out.println("Building: " + nrOfFloors + " floors and " + nrOfElevators + " elevators");
    }

    public static void personCreationMessage(int floor, int destinationFloor) {
        System.out.println("Created person " + floor + " => " + destinationFloor);
    }

}
