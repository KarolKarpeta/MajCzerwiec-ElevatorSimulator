package com.codecool;

public class View {
    public static void personCreationMessage(int floor, int destinationFloor){
        System.out.println("Created person on "+floor+" floor with destination floor "+destinationFloor);
    }

    public static void showFloorStatus(Floor floor){
        System.out.println(floor.getUpQueue().toString());
        System.out.println(floor.getDownQueue().toString());
    }
}
