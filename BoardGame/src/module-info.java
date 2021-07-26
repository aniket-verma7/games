module BoardGame {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires junit;
    opens com.application.model;
    opens com.application;
    opens com.application.controller;
}
