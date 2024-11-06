module com.mohit.workshop1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mohit.workshop1 to javafx.fxml;
    exports com.mohit.workshop1.controller;
    exports com.mohit.workshop1.interfaces;
    opens com.mohit.workshop1.interfaces to javafx.fxml;
    exports com.mohit.workshop1.model;
    opens com.mohit.workshop1.model to javafx.fxml;
}