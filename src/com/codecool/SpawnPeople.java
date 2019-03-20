package com.codecool;

public class SpawnPeople implements Runnable {
    private int numberOfPeopleToCreate = 3;
    @Override
    public void run() {
        Building kolejowa = Building.getBuilding();
        for (int i = 0; i < numberOfPeopleToCreate; i++) {
            kolejowa.generatePerson();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
