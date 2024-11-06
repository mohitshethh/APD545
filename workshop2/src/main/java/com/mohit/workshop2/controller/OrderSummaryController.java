package com.mohit.workshop2.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrderSummaryController {

    @FXML
    private Text txtOrderSummary;

    public void setSummaryText(String summary) {
        txtOrderSummary.setText(summary);
    }

    @FXML
    void closeWindow() {
        Stage stage = (Stage) txtOrderSummary.getScene().getWindow();
        stage.close();
    }
}
