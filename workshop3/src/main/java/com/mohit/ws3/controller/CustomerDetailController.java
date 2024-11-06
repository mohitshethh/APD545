package com.mohit.ws3.controller;

import com.mohit.ws3.startApplication;
import com.mohit.ws3.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerDetailController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField cityField;

    @FXML
    private ComboBox<String> provinceComboBox;

    @FXML
    private void handleOkAction(ActionEvent actionEvent) throws IOException {
        // Validate inputs and show alert if any field is empty
        if (nameField.getText().isEmpty()) {
            showAlert("Name is required");
            nameField.requestFocus();
            return;
        }

        if (phoneField.getText().isEmpty()) {
            showAlert("Phone number is required");
            phoneField.requestFocus();
            return;
        }

        if (cityField.getText().isEmpty()) {
            showAlert("City is required");
            cityField.requestFocus();
            return;
        }

        if (provinceComboBox.getValue() == null) {
            showAlert("Province is required");
            provinceComboBox.requestFocus();
            return;
        }

        // Create Customer object
        Customer customer = new Customer(
                nameField.getText(),
                phoneField.getText(),
                cityField.getText(),
                provinceComboBox.getValue()
        );

        // Load CustomerDetailView
        FXMLLoader fxmlLoader = new FXMLLoader(startApplication.class.getResource("LoanCalculatorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Customer Details");
        stage.setResizable(false);
        stage.setScene(scene);

        LoanCalculatorController controller = fxmlLoader.getController();
        controller.setCustomer(customer);

        stage.show();

        //close the current window
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();

        // Placeholder for navigation
        System.out.println("Customer created: " + customer);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
