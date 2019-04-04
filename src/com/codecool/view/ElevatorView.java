package com.codecool.view;

import com.codecool.controller.Config;
import com.codecool.model.Elevator;
import com.codecool.model.Floor;
import com.codecool.model.Person;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.LinkedBlockingQueue;

public class ElevatorView extends Rectangle implements PropertyChangeListener {
    private Floor floor;
    private int passengersNumber;

    public ElevatorView(Floor floor) {
        super(Config.ELEVATOR_WIDTH, Config.FLOOR_HEIGHT, Color.AQUAMARINE);
        this.setY(5 * Config.FLOOR_HEIGHT);
        this.floor = floor;
        passengersNumber = 0;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("floor")) {
            this.setFloor((Floor) evt.getNewValue());
        }else if(evt.getPropertyName().equals("passengersNumber")){
            this.passengersNumber = (int) evt.getNewValue();
        }
        int floorNumber = floor.getFloorNumber();
        this.setY(floorNumber * Config.FLOOR_HEIGHT);
        System.out.println("Elevator in " + floorNumber + " floor, has " + passengersNumber + " people");
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }
}
