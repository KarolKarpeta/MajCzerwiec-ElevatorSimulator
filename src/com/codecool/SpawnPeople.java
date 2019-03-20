package com.codecool;

public class SpawnPeople implements Runnable {

    @Override
    public void run() {
        Building kolejowa = Building.getBuilding();
        for (int i = 0; i < 100; i++) {
            kolejowa.generatePerson();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
