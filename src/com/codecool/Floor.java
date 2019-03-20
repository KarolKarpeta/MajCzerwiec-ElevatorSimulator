package com.codecool;

import java.util.LinkedList;
import java.util.List;

public class Floor {
    private List<Person> upQueue = new LinkedList<>();
    private List<Person> downQueue = new LinkedList<>();
    private int floorNumber;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void summonElevator() {
        //floor mówi budynkowi, że chce windę, bo ma ludzi jadących na dół i/lub górę
    }

    /**
     * Appends Person to upQueue  or DownQueue
     * @param person already created Person object to add to the Floor
     */
    public void addPersonToRelevantQueue(Person person) {
        if(person.getDestinationFloor() > this.floorNumber) {
            upQueue.add(person);
        } else {
            downQueue.add(person);
        }
        Task newTask = new Task(floorNumber, person.getDestinationFloor());
        Building.getBuilding().handleTask(newTask);
        View.personCreationMessage(this.getFloorNumber(), person.getDestinationFloor());
        View.showFloorStatus(this);
    }


    public List<Person> getUpQueue(){
        return upQueue;
    }

    public List<Person> getDownQueue() {
        return downQueue;
    }


    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }


}
