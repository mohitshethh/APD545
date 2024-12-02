module com.mohit.workshop4n5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.mohit.workshop4n5 to javafx.fxml;
    exports com.mohit.workshop4n5;
    exports com.mohit.workshop4n5.controller;
    opens com.mohit.workshop4n5.controller to javafx.fxml;
    opens com.mohit.workshop4n5.model to javafx.base;
}