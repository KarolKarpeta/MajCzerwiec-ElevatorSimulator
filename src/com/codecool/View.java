package com.codecool;

import java.util.concurrent.LinkedBlockingQueue;

public class View {


    public static void showFloors(){
        Building kolejowa = Building.getBuilding();
        Floor[] floors = kolejowa.getFloors();
        for (int i = floors.length-1; i >= 0; i--) {
            for(Elevator elevator : kolejowa.getElevators()){
                if(elevator.getFloor().getFloorNumber() == floors[i].getFloorNumber())
                System.out.print("#");
            }
            System.out.println("\tFloor "+floors[i].getFloorNumber()+" Up:"+floors[i].getUpQueue()
            +" Down:"+floors[i].getDownQueue()+" Transported: "+floors[i].getTransportedPeople());
        }
    }

    public static void print(Task task){
        if(task == null){
            System.out.print("null");
            return;
        }
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
        System.out.println(elevator.getName() + " on " + elevator.getFloor().getFloorNumber()+" floor, direction:"+elevator.getCurrentDirection());
        System.out.print("Tasks: ");
        print(elevator.getTasks());
        print(elevator.getPeople());
        System.out.println("#############################################################");
    }

    public static void buildingCreationMessage(int nrOfFloors, int nrOfElevators) {
        System.out.println("Building: " + nrOfFloors + " floors and " + nrOfElevators + " elevators");
    }

    public static void personCreationMessage(int floor, int destinationFloor) {
        System.out.println("Created person " + floor + " => " + destinationFloor);
    }

    public static void print(LinkedBlockingQueue<Task> tasks){
        for(Task task : tasks){
            print(task);
        }
        System.out.println();
    }
}
