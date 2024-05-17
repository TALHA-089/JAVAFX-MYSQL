package com.example.oopslab13.Register;

import com.example.oopslab13.Models.USER;
import com.example.oopslab13.SQL;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController extends SQL {

    @FXML
    private TextField tfUserName;

    @FXML
    private TextField tfEnrollment;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfConfirmPassword;

    @FXML
    protected void RegisterUser() {

        String username = tfUserName.getText();
        String enrollment = tfEnrollment.getText();
        String email = tfEmail.getText();
        String password = pfPassword.getText();
        String confirmPassword = pfConfirmPassword.getText();

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Password", "Both Password Fields do not Match!");
        } else {
            USER user = new USER();
            user.setName(username);
            user.setEmail(email);
            user.setEnrollment(enrollment);
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

            final String DB_URL = "jdbc:mysql://localhost/OOPS?serverTimezone=UTC";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "Talha@786";

            String sql = "INSERT INTO OOPS.USER (USERNAME, ENROLLMENT, PASSWORD, EMAIL) VALUES (?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEnrollment());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getEmail());

                int addedRows = preparedStatement.executeUpdate();

                if (addedRows > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "SUCCESS!", username + " Registered Successfully");
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Failure!", username + " not Registered");
                }

            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Failure!", "Registration failed due to a database error");
                e.printStackTrace();
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

    private void clearFields() {
        tfUserName.clear();
        tfEnrollment.clear();
        tfEmail.clear();
        pfPassword.clear();
        pfConfirmPassword.clear();
    }
}
