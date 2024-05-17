package com.example.oopslab13.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class USER {
    private final StringProperty name;
    private final StringProperty enrollment;
    private final StringProperty email;
    private final StringProperty password;

    public USER() {
        this.name = new SimpleStringProperty();
        this.enrollment = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getEnrollment() {
        return enrollment.get();
    }

    public void setEnrollment(String enrollment) {
        this.enrollment.set(enrollment);
    }

    public StringProperty enrollmentProperty() {
        return enrollment;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
