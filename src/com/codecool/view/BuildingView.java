package com.codecool.view;

import com.codecool.model.Building;
import com.codecool.model.Elevator;
import com.codecool.model.Floor;
import javafx.scene.layout.Pane;
import com.codecool.controller.Config;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BuildingView {


    public BuildingView(Pane layout, Building building) {
        HBox elevatorsArea = new HBox(10);
        Elevator[] elevators = building.getElevators();
        for (int i = 0; i < Config.ELEVATORS_NUMBER; i++) {
            ElevatorView elevatorView = new ElevatorView(elevators[i]);
            //elevators[i].setElevatorView(elevatorView);
            elevatorsArea.getChildren().add(elevatorView);
        }
        VBox floorsArea = new VBox();
        Floor[] floors = building.getFloors();
        for (int i = 0; i < Config.FLOORS_NUMBER; i++) {
            FloorView floorView = new FloorView(floors[i]);
            //floorView.getChildren().add(new PersonView());
            floorsArea.getChildren().add(floorView);
        }
        layout.getChildren().addAll(new HBox(elevatorsArea, floorsArea));
    }
}
