package com.codecool.model;

import java.util.LinkedList;
import java.util.List;

public class Floor {

    private LinkedList<Person> upQueue = new LinkedList<>();
    private LinkedList<Person> downQueue = new LinkedList<>();
    private List<Person> transportedPeople = new LinkedList<>();
    private int floorNumber;


    /**
     * Appends Person to upQueue  or DownQueue
     *
     * @param person already created Person object to add to the Floor
     */
    public void addPersonToRelevantQueue(Person person) {
        //Terminal.personCreationMessage(this.getFloorNumber(), person.getDestinationFloor());
        //Terminal.personCreationMessage(this.getFloorNumber(), person);
        if (person.getDestinationFloor() > this.floorNumber) {
            upQueue.add(person);
        } else {
            downQueue.add(person);
        }

        System.out.println("XXXXXXXXXXXXX");//TODO ta linijka jest wykonywana dopiero jak winda przestanie działać
        //TODO naprawić to, bo winda ma max 1 Task i max 1 pasażera. Może Floor ma działać na swoim wątku?
        //TODO może Floor ma podobnie jak winda mieć swój Thread oraz pudełko na nowe taski
    }

    public void addToTransportedPeople(Person transportedPerson) {
        this.transportedPeople.add(transportedPerson);
    }

    boolean isEmpty(Direction elevatorDirection) {
        if (Direction.GOING_UP.equals(elevatorDirection)) {
            return upQueue.isEmpty();
        } else if (Direction.GOING_DOWN.equals(elevatorDirection)) {
            return downQueue.isEmpty();
        } else {
            return upQueue.isEmpty() && downQueue.isEmpty();
        }
    }

    boolean isEmpty() {
        return upQueue.isEmpty() && downQueue.isEmpty();
    }

    public LinkedList<Person> getDownQueue() {
        return downQueue;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Person> getTransportedPeople() {
        return transportedPeople;
    }

    public LinkedList<Person> getUpQueue() {
        return upQueue;
    }

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Person popPersonFromAnyQueue() {
        if (!upQueue.isEmpty()) {
            return popPersonFromUpQueue();
        } else {
            return popPersonFromDownQueue();
        }
    }

    public Person popPersonFromDownQueue() {
        return downQueue.pollFirst();
    }

    public Person popPersonFromUpQueue() {
        return upQueue.pollFirst();
    }
}
