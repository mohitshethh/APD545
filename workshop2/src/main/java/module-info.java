module com.mohit.workshop2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mohit.workshop2 to javafx.fxml;
    exports com.mohit.workshop2;
    exports com.mohit.workshop2.controller;
    opens com.mohit.workshop2.controller to javafx.fxml;
    exports com.mohit.workshop2.model;
    opens com.mohit.workshop2.model to javafx.fxml;
}