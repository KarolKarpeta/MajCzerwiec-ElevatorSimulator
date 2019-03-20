package com.codecool;

public class SpawnPeople implements Runnable {

    @Override
    public void run() {
        Building kolejowa = Building.getBuilding();
        for (int i = 0; i < 100; i++) {
            //start spawning people for first building
            kolejowa.generatePerson();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
