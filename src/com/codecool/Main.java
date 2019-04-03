package com.codecool;

import java.util.Scanner;

import static com.codecool.Building.createBuilding;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nrOfFloors = 1,
                nrOfElevators = 1;

        try {
            System.out.println("Type number of floors: ");
            nrOfFloors = sc.nextInt();
            System.out.println("Type number of elevators: ");
            nrOfElevators = sc.nextInt();
        } catch (NumberFormatException e) {
            System.out.println("Not a number!!!");
        }

        createBuilding(nrOfFloors, nrOfElevators);
        PeopleSpawner peopleSpawner = new PeopleSpawner();
        Thread spawnThread = new Thread(peopleSpawner);
        spawnThread.start();
        try {
            spawnThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        peopleSpawner = new PeopleSpawner(20, 5);
        spawnThread = new Thread(peopleSpawner);
        spawnThread.start();
        try {
            spawnThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }


}
