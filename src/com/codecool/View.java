package com.codecool;

public class View {
    public static void personCreationMessage(int floor, int destinationFloor){
        System.out.println("Created person on "+floor+" floor with destination floor "+destinationFloor);
    }

    public static void showFloorStatus(Floor floor){
        System.out.println(floor.getUpQueue().toString());
        System.out.println(floor.getDownQueue().toString());
    }

    public static void showElevator(Elevator elevator){
        System.out.println("Elevator:"+elevator);
        System.out.println("At floor: "+elevator.getFloor().getFloorNumber());
        System.out.print("Tasks:");
        for(Task task : elevator.getTasks()){
            System.out.print("floor: "+task.getDestinationFloorNumber()+", load: "+task.hasToLoad()+"; ");
        }
        System.out.println();
    }
}
