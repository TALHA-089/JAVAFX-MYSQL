package com.example.oopslab13;

import com.example.oopslab13.Login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LandingPageController {

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnExit;

    @FXML
    protected void RegisterPage() throws IOException {

        Stage primaryStage = (Stage) btnRegister.getScene().getWindow();
        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
        Parent firstView = firstLoader.load();

        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent secondView = secondLoader.load();

        BorderPane root = new BorderPane();
        root.setLeft(firstView);
        root.setRight(secondView);

        primaryStage.setTitle("WELCOME!");
        primaryStage.setScene(new Scene(root, 610, 460));
        primaryStage.show();
    }

    @FXML
    protected void LoginPage() throws IOException {

        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("LandingPage.fxml"));
        Parent firstView = firstLoader.load();

        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent secondView = secondLoader.load();

        BorderPane root = new BorderPane();
        root.setLeft(firstView);
        root.setRight(secondView);

        primaryStage.setTitle("WELCOME!");
        primaryStage.setScene(new Scene(root, 610, 460));
        primaryStage.show();
    }

    @FXML
    protected void ExitApplication(){
        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
        primaryStage.close();
    }

}
