package com.codecool.controller;

import com.codecool.model.Building;
import com.codecool.model.PeopleSpawner;
import com.codecool.view.ElevatorView;
import com.codecool.view.FloorView;
import com.codecool.view.PersonView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    private static void prepareWindow(Stage stage){
        stage.setTitle("Elevator Simulator");
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        HBox elevatorsArea = new HBox(10);
        for (int i = 0; i < Config.ELEVATORS_NUMBER; i++) {
            ElevatorView elevatorView = new ElevatorView();
            elevatorsArea.getChildren().add(elevatorView);
        }
        VBox floorsArea = new VBox();
        for (int i = 0; i < Config.FLOORS_NUMBER; i++) {
            FloorView floorView = new FloorView();
            //floorView.getChildren().add(new PersonView());
            floorsArea.getChildren().add(floorView);
        }
        layout.getChildren().addAll(new HBox(elevatorsArea, floorsArea));

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        prepareWindow(stage);

        /*
        Building.createBuilding(FLOORS_NUMBER, ELEVATORS_NUMBER);
        PeopleSpawner peopleSpawner;
        Thread spawnThread;

        peopleSpawner = new PeopleSpawner(5, 11);
        spawnThread = new Thread(peopleSpawner);
        spawnThread.run();
        */

    }
}
