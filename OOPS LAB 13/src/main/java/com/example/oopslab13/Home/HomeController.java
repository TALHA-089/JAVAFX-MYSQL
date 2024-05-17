package com.example.oopslab13.Home;

import com.example.oopslab13.Models.USER;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeController {

    @FXML
    private Button btnGoBack;

    @FXML
    private TableView<USER> UserTable;

    @FXML
    private TableColumn<USER, String> nameColumn;

    @FXML
    private TableColumn<USER, String> enrollmentColumn;

    @FXML
    private TableColumn<USER, String> emailColumn;

    @FXML
    private TableColumn<USER,String> passwordColumn;

    private String userEnrollment;

    public void setUserEnrollment(String enrollment) {
        this.userEnrollment = enrollment;
        loadUserData();
    }

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        enrollmentColumn.setCellValueFactory(new PropertyValueFactory<>("enrollment"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void loadUserData() {
        final String DB_URL = "jdbc:mysql://localhost/OOPS?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "Talha@786";

        ObservableList<USER> users = FXCollections.observableArrayList();

        String sql = "SELECT * FROM OOPS.USER WHERE ENROLLMENT = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, userEnrollment);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    USER user = new USER();
                    user.setName(resultSet.getString("USERNAME"));
                    user.setEnrollment(resultSet.getString("ENROLLMENT"));
                    user.setEmail(resultSet.getString("EMAIL"));
                    user.setPassword(resultSet.getString("PASSWORD"));
                    users.add(user);
                }

                UserTable.setItems(users);

            } catch (SQLException e) {
                e.printStackTrace();
                // Handle result set errors here
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection errors here
        }
    }

    @FXML
    protected void GoBack() throws IOException {
        Stage primaryStage = (Stage) btnGoBack.getScene().getWindow();
        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("/com/example/oopslab13/LandingPage.fxml"));
        Parent firstView = firstLoader.load();

        FXMLLoader secondLoader = new FXMLLoader(getClass().getResource("/com/example/oopslab13/Register.fxml"));
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
}
