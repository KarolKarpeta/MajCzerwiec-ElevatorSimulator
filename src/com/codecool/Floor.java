package com.codecool;

import java.util.LinkedList;
import java.util.List;

public class Floor {
    private List<Person> upQueue = new LinkedList<>();
    private List<Person> downQueue = new LinkedList<>();
    private int floorNumber;

    public void summonElevator() {

    }

    /**
     * Appends Person to upQueue  or DownQueue
     * @param person already created Person object to add to the Floor
     */
    public void addPerson(Person person) {

    }

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
