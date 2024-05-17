package com.example.oopslab13.Login;

import com.example.oopslab13.Home.HomeController;
import com.example.oopslab13.SQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.*;

public class LoginController extends SQL {

    @FXML
    private Button btnLogin;

    @FXML
    private TextField tfEnrollment;

    @FXML
    private PasswordField pfPassword;

    @FXML
    protected void LoginUser() {

        String enrollment = tfEnrollment.getText();
        String password = pfPassword.getText();

        if (enrollment.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "ERROR", "Empty fields");
        } else {
            final String DB_URL = "jdbc:mysql://localhost/OOPS?serverTimezone=UTC";
            final String USERNAME = "root";
            final String PASSWORD = "Talha@786";

            try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
                String sql = "SELECT * FROM OOPS.USER WHERE ENROLLMENT = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, enrollment);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String dbPassword = resultSet.getString("PASSWORD");

                            if (BCrypt.checkpw(password, dbPassword)) {
                                showAlert(Alert.AlertType.INFORMATION, "Success", "WELCOME " + resultSet.getString("USERNAME"));

                                Stage stage = (Stage) btnLogin.getScene().getWindow();
                                FXMLLoader fxmlLoader = new FXMLLoader(SQL.class.getResource("Home.fxml"));
                                Scene scene = new Scene(fxmlLoader.load());
                                HomeController homeController = fxmlLoader.getController();
                                homeController.setUserEnrollment(enrollment);
                                stage.setTitle("WELCOME!");
                                stage.setScene(scene);
                                stage.setMinHeight(400);
                                stage.setMaxHeight(400);
                                stage.setMinWidth(600);
                                stage.setMaxWidth(600);
                                stage.show();
                            } else {
                                showAlert(Alert.AlertType.ERROR, "Failure", "Invalid Credentials");
                            }
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Failure", "Invalid Credentials");
                        }
                    }
                }
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Failure", "Connection Error!");
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
