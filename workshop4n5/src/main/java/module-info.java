module com.mohit.workshop4n5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mohit.workshop4n5 to javafx.fxml;
    exports com.mohit.workshop4n5;
}