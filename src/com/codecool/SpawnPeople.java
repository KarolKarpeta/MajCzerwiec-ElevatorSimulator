package com.codecool;

public class SpawnPeople implements Runnable {
    private static final int NUMBER_OF_PEOPLE_TO_CREATE = 3;
    @Override
    public void run() {
        Building kolejowa = Building.getBuilding();
        for (int i = 0; i < NUMBER_OF_PEOPLE_TO_CREATE; i++) {
            kolejowa.generatePerson();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
