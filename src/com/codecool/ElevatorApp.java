package com.codecool;

import javafx.application.Application;
import javafx.stage.Stage;

public class ElevatorApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Elevator simulator");
        primaryStage.show();
    }
}
