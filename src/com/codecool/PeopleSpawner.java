package com.codecool;

public class PeopleSpawner implements Runnable {
    private final int numberOfPeopleToCreate;
    private final int spawnInterval;//milliseconds

    PeopleSpawner(int spawnInterval, int numberOfPeople){
        this.spawnInterval = spawnInterval;
        this.numberOfPeopleToCreate = numberOfPeople;
    }

    PeopleSpawner(){
        this.spawnInterval = 10000;
        this.numberOfPeopleToCreate = 3;
    }

    @Override
    public void run() {
        Building kolejowa = Building.getBuilding();
        for (int i = 0; i < numberOfPeopleToCreate; i++) {
            kolejowa.generatePerson();
            try {
                Thread.sleep(spawnInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i == numberOfPeopleToCreate - 1) {
                System.out.println("i chuj   (koniec programu)");
            }
        }
    }
}
