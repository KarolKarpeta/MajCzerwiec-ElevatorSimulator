package com.codecool;

import java.util.LinkedList;
import java.util.List;

public class Floor {
    private LinkedList<Person> upQueue = new LinkedList<>();
    private LinkedList<Person> downQueue = new LinkedList<>();
    private int floorNumber;

    public int getFloorNumber() {
        return floorNumber;
    }

    public Person popPersonFromDownQueue(){
        return downQueue.remove();
    }
    public Person popPersonFromUpQueue(){
        return upQueue.remove();
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
