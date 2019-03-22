package com.codecool;

import java.util.LinkedList;
import java.util.List;

public class Floor {
    private LinkedList<Person> upQueue = new LinkedList<>();
    private LinkedList<Person> downQueue = new LinkedList<>();
    private List<Person> transportedPeople = new LinkedList<>();
    private int floorNumber;

    public int getFloorNumber() {
        return floorNumber;
    }
    public List<Person> getTransportedPeople() {
        return transportedPeople;
    }

    public void addToTransportedPeople(Person transportedPerson) {
        this.transportedPeople.add(transportedPerson);
    }

    public Person popPersonFromDownQueue(){
        return downQueue.remove();
    }
    public Person popPersonFromUpQueue(){
        return upQueue.remove();
    }

    public Person popPersonFromAnyQueue(){
        if(!upQueue.isEmpty()){
            return upQueue.remove();
        }else{
            return downQueue.remove();
        }
    }

    boolean isEmpty(){
        return upQueue.isEmpty() || downQueue.isEmpty();
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
        Task newTask = new Task(floorNumber, true);
        Building.getBuilding().assignTask(newTask);
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
