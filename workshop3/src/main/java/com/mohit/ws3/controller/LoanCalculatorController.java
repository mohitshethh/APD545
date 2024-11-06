package com.mohit.ws3.controller;

import com.mohit.ws3.startApplication;
import com.mohit.ws3.model.Customer;
import com.mohit.ws3.model.Loan;
import com.mohit.ws3.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculatorController {

    @FXML
    private Button calculateButton;

    @FXML
    private Button clearButton;

    @FXML
    private RadioButton customInterestRateRadio;

    @FXML
    private TextField customInterestRateTextField;

    @FXML
    private TextField downPaymentTextField;

    @FXML
    private TextField estimatedPaymentTextField;

    @FXML
    private ToggleGroup interestRateToggleGroup;

    @FXML
    private Slider loanPeriodSlider;

    @FXML
    private ChoiceBox<String> paymentFrequencyChoiceBox;

    @FXML
    private Button saveRateButton;

    @FXML
    private Button showSavedRatesButton;

    @FXML
    private ChoiceBox<String> vehicleAgeChoiceBox;

    @FXML
    private TextField vehiclePriceTextField;

    @FXML
    private ChoiceBox<String> vehicleTypeChoiceBox;

    private final List<Loan> savedRates = new ArrayList<>();

    private Customer customer;

    private double downPayment;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private double estimatedPayment;

    @FXML
    public void initialize() {
        vehicleTypeChoiceBox.setItems(FXCollections.observableArrayList("Car", "Truck", "Family Van"));
        vehicleAgeChoiceBox.setItems(FXCollections.observableArrayList("New", "Used"));
        paymentFrequencyChoiceBox.setItems(FXCollections.observableArrayList("Weekly", "Bi-weekly", "Monthly"));

        loanPeriodSlider.setMin(12);
        loanPeriodSlider.setMax(96);
        loanPeriodSlider.setMajorTickUnit(12);
        loanPeriodSlider.setShowTickLabels(true);
        loanPeriodSlider.setShowTickMarks(true);

        // Listener for enabling/disabling custom interest rate
        customInterestRateTextField.setDisable(true);
        interestRateToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            if (newToggle == customInterestRateRadio) {
                customInterestRateTextField.setDisable(false);
            } else {
                customInterestRateTextField.setDisable(true);
                customInterestRateTextField.clear();
            }
        });

        // Slider ChangeListener
        loanPeriodSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateEstimatedPayment());
    }

    @FXML
    private void handleClearButtonAction() {
        vehicleTypeChoiceBox.getSelectionModel().clearSelection();
        vehicleAgeChoiceBox.getSelectionModel().clearSelection();
        vehiclePriceTextField.clear();
        downPaymentTextField.clear();
        interestRateToggleGroup.selectToggle(null);
        customInterestRateTextField.clear();
        customInterestRateTextField.setDisable(true);
        loanPeriodSlider.setValue(12);
        paymentFrequencyChoiceBox.getSelectionModel().clearSelection();
        estimatedPaymentTextField.clear();
    }

    @FXML
    private void handleCalculateButtonAction() {
        try {
            double price = Double.parseDouble(vehiclePriceTextField.getText());
            double downPayment = Double.parseDouble(downPaymentTextField.getText());
            double interestRate = getSelectedInterestRate();
            int periodMonths = (int) loanPeriodSlider.getValue();
            String frequency = paymentFrequencyChoiceBox.getValue();

            estimatedPayment = calculateLoanPayment(price, downPayment, interestRate, periodMonths, frequency);
            estimatedPaymentTextField.setText(String.format("$%.2f (%s)", estimatedPayment, frequency));

        } catch (NumberFormatException e) {
            showAlert("Invalid input", "Please enter valid numerical values.");
        }
    }

    private double getSelectedInterestRate() {
        if (customInterestRateRadio.isSelected()) {
            return Double.parseDouble(customInterestRateTextField.getText());
        } else {
            RadioButton selectedRadioButton = (RadioButton) interestRateToggleGroup.getSelectedToggle();
            String rateText = selectedRadioButton.getText().replace("%", "");
            return Double.parseDouble(rateText) / 100;
        }
    }

    @FXML
    private void handleSaveRateButtonAction() {
        try {
            downPayment = Double.parseDouble(downPaymentTextField.getText());
            Vehicle vehicle = new Vehicle(
                    vehicleTypeChoiceBox.getValue(),
                    vehicleAgeChoiceBox.getValue(),
                    Double.parseDouble(vehiclePriceTextField.getText())
            );
            Loan loan = new Loan(
                    (int) loanPeriodSlider.getValue(),
                    getSelectedInterestRate(),
                    paymentFrequencyChoiceBox.getValue(),
                    estimatedPayment,
                    customer,
                    vehicle
            );
            savedRates.add(loan);
            showAlert("Success", "Rate saved successfully.");
        } catch (NumberFormatException e) {
            showAlert("Error", "Please ensure all fields are correctly filled out before saving.");
        }
    }

    @FXML
    private void handleShowSavedRatesButtonAction() throws IOException {
        // Implement the method to display saved rates in a ListView dialog.
        FXMLLoader loader = new FXMLLoader(startApplication.class.getResource("SavedRatesView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setTitle("Saved Rates");
        stage.setResizable(false);
        stage.setScene(scene);

        SavedRatesController controller = loader.getController();
        controller.setSavedRates(savedRates);

        stage.show();
    }

    private double calculateLoanPayment(double price, double downPayment, double interestRate, int periodMonths, String frequency) {
        double principal = price - downPayment;
        double monthlyRate = interestRate / 12;

        int paymentFrequency;
        switch (frequency) {
            case "Weekly":
                paymentFrequency = 4;
                break;
            case "Bi-weekly":
                paymentFrequency = 2;
                break;
            default:
                paymentFrequency = 1;
        }

        double totalInterest = 0.0;
        double totalPayment;
        double paymentAmount;
        if (interestRate != 0) {
            double temp = Math.pow(1 + monthlyRate / paymentFrequency, periodMonths * paymentFrequency);
            totalPayment = (principal * monthlyRate / paymentFrequency * temp) / (temp - 1);
            totalInterest = totalPayment * periodMonths * paymentFrequency - principal;
            paymentAmount = totalPayment;
        } else {
            totalPayment = principal / (periodMonths * paymentFrequency);
            paymentAmount = totalPayment;
        }

        return paymentAmount;
    }

    private void updateEstimatedPayment() {
        try {
            double price = Double.parseDouble(vehiclePriceTextField.getText());
            double downPayment = Double.parseDouble(downPaymentTextField.getText());
            double interestRate = getSelectedInterestRate();
            int periodMonths = (int) loanPeriodSlider.getValue();
            String frequency = paymentFrequencyChoiceBox.getValue();

            double estimatedPayment = calculateLoanPayment(price, downPayment, interestRate, periodMonths, frequency);
            estimatedPaymentTextField.setText(String.format("$%.2f (%s)", estimatedPayment, frequency));
        } catch (NumberFormatException e) {
            estimatedPaymentTextField.clear();
        }
    }

    public void loadLoanData(Loan loan) {
        vehicleTypeChoiceBox.setValue(loan.getVehicleType());
        vehicleAgeChoiceBox.setValue(loan.vehicle.getAge());
        vehiclePriceTextField.setText(String.valueOf(loan.vehicle.getPrice()));
        downPaymentTextField.setText(String.valueOf(downPayment));
        customInterestRateTextField.setText(String.valueOf(loan.getInterestRate() * 100));
        loanPeriodSlider.setValue(loan.getPeriodMonths());
        paymentFrequencyChoiceBox.setValue(loan.getPaymentFrequency());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
