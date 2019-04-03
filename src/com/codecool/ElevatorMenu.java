package com.codecool;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ElevatorMenu extends Application {
    int elevatorsNo;
    int floorsNo;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Elevator Simulator");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome kurła");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Number of elevators:");
        grid.add(userName, 0, 1);

        TextField elevatorsNo = new TextField();
        grid.add(elevatorsNo, 1, 1);

        Label pw = new Label("Number of floors:");
        grid.add(pw, 0, 2);

        TextField floorsNo = new TextField();
        grid.add(floorsNo, 1, 2);

        Button button = new Button("Start elevators");

        button.setOnAction( e -> {

            assignElevatorsAndFloorsNos(elevatorsNo.getText(), floorsNo.getText());

            grid.getChildren().clear();
        } );

        grid.add(button, 2,2);
        Scene scene = new Scene(grid, 600, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void assignElevatorsAndFloorsNos(String elevatorsNo, String floorsNo) {
        try {
            this.elevatorsNo = Integer.parseInt(elevatorsNo);
        } catch(NumberFormatException ex) {
            System.out.println("Error: " + elevatorsNo + " is not a number!");
        }

        try {
            this.floorsNo = Integer.parseInt(floorsNo);
        } catch(NumberFormatException ex) {
            System.out.println("Error: " + floorsNo + " is not a number!");
        }

        System.out.println("Number of elevators: " + this.elevatorsNo + "\nNumber of floors: " + this.floorsNo);
    }
}
