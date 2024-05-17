module com.example.oopslab13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jBCrypt;

    // Open necessary packages to javafx.fxml for reflective access
    opens com.example.oopslab13 to javafx.fxml;
    opens com.example.oopslab13.Models to javafx.fxml;
    opens com.example.oopslab13.Home to javafx.fxml;
    opens com.example.oopslab13.Login to javafx.fxml;
    opens com.example.oopslab13.Register to javafx.fxml;

    // Export packages
    exports com.example.oopslab13;
    exports com.example.oopslab13.Models;
    exports com.example.oopslab13.Home;
    exports com.example.oopslab13.Login;
    exports com.example.oopslab13.Register;
}
