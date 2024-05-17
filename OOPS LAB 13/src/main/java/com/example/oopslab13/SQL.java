package com.example.oopslab13;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SQL extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
        Parent firstView = firstLoader.load();

        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent secondView = secondLoader.load();

        BorderPane root = new BorderPane();
        root.setLeft(firstView);
        root.setRight(secondView);

        primaryStage.setTitle("WELCOME!");
        primaryStage.setScene(new Scene(root, 610, 460));
        primaryStage.setMinWidth(610);
        primaryStage.setMinHeight(460);
        primaryStage.setMaxHeight(460);
        primaryStage.setMaxWidth(610);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
