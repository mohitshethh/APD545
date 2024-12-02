module com.mohit.workshop6n7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;


    opens com.mohit.workshop6n7 to javafx.fxml;
    exports com.mohit.workshop6n7;
    exports com.mohit.workshop6n7.controller;
    opens com.mohit.workshop6n7.controller to javafx.fxml;
    opens com.mohit.workshop6n7.model to javafx.base;
}