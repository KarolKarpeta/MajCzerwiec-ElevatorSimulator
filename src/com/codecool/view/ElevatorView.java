package com.codecool.view;

import com.codecool.controller.Config;
import com.codecool.model.Elevator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ElevatorView extends Rectangle {



    public ElevatorView(Elevator elevator) {
        super(Config.ELEVATOR_WIDTH, Config.FLOOR_HEIGHT, Color.AQUAMARINE);
        int floorNumber = elevator.getFloor().getFloorNumber();
        this.setY(floorNumber * Config.FLOOR_HEIGHT);
    }

    public void showElevator(Elevator elevator) {
        int floorNumber = elevator.getFloor().getFloorNumber();
        this.setY(floorNumber * Config.FLOOR_HEIGHT);
        System.out.println("showElevator");
    }
}
