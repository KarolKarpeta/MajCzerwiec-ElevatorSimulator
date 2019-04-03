package com.codecool.view;

import com.codecool.controller.Config;
import com.codecool.model.Elevator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ElevatorView extends Rectangle {

    public ElevatorView(){
        super(Config.ELEVATOR_WIDTH, Config.FLOOR_HEIGHT, Color.AQUAMARINE);
    }

    public void showElevator(Elevator elevator){
        int floorNumber = elevator.getFloor().getFloorNumber();
        double newY = floorNumber * Config.FLOOR_HEIGHT;
        this.setY(newY);
    }
}
