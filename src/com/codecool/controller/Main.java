package com.codecool.controller;


import javafx.geometry.Rectangle2D;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class Main extends Application {

    Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Elevator Simulator");
        StackPane layout = new StackPane();
        Scene scene = new Scene(layout, 1300, 740);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }
}
