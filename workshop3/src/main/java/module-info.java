module com.mohit.ws3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mohit.ws3 to javafx.fxml;
    exports com.mohit.ws3;
    exports com.mohit.ws3.controller;
    opens com.mohit.ws3.controller to javafx.fxml;
}