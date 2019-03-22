package com.codecool;

import java.util.Random;

public class Person {
    private final int destinationFloor;
    private final String name;

    public Person(int destinationFloor) {
        this.destinationFloor = destinationFloor;
        this.name = generateRandomName();
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    private String generateRandomName(){
        Random random = new Random();

    String[] names = {"Maciek", "Józek", "Antek", "Janek", "Krzychu", "Marian", "Ferdek", "Walduś", "Halina", "Grażyna", "Hela",
                        "Jaćka", "Heniek", "Stasek", "Franek", "Zbynek", "Zenek", "Brygida", "Kuba", "Klemens", "Eustachy", "Edward",
                        "Eugeniusz", "Fryderyk", "Gniewomir", "Gustaw", "Gżegoż", "Irek", "Jerzy", "Julian", "Kajetan", "Konstanty",
                        "Kryspin", "Krzesimir", "Kondrad", "Kazek", "Leopold", "Leszek", "Leopold", "Maurycy", "Mieszko"};

        return names[random.nextInt(names.length)];
    }

    public String getName() {
        return name;
    }
}
