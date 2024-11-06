package com.mohit.ws3.controller;

import com.mohit.ws3.model.Loan;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class SavedRatesController {

    private List<Loan> savedRates;

    public void SavedRates(List<Loan> savedRates) {
        this.savedRates = savedRates;
    }
    private LoanCalculatorController mainController;

    public void setMainController(LoanCalculatorController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private ListView<String> savedRatesListView;

    public void setSavedRates(List<Loan> savedRates) {
        savedRatesListView.setItems(FXCollections.observableArrayList(
                savedRates.stream()
                        .map(loan -> String.format("Name: %s, Vehicle: %s, Rate: %.2f%%",
                                loan.customer.getName(), loan.getVehicleType(), loan.getInterestRate() * 100))
                        .toList()
        ));
    }

    @FXML
    private void initialize() {
        savedRatesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = savedRatesListView.getSelectionModel().getSelectedIndex();
                Loan selectedLoan = savedRates.get(selectedIndex);

                loadLoanInMainApplication(selectedLoan);
            }
        });
    }

    private void loadLoanInMainApplication(Loan selectedLoan) {
        mainController.loadLoanData(selectedLoan);
    }

    @FXML
    private void handleCloseButtonAction() {
        Stage stage = (Stage) savedRatesListView.getScene().getWindow();
        stage.close();
    }
}