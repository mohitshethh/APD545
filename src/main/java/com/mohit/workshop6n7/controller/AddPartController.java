package com.mohit.workshop6n7.controller;

import com.mohit.workshop6n7.exceptions.InventoryValueException;
import com.mohit.workshop6n7.exceptions.MinMaxValueException;
import com.mohit.workshop6n7.exceptions.PartDetailsException;
import com.mohit.workshop6n7.model.InHouse;
import com.mohit.workshop6n7.model.Inventory;
import com.mohit.workshop6n7.model.Outsourced;
import com.mohit.workshop6n7.model.Part;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class AddPartController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField companyMachineIdField;

    @FXML
    private TextField idField;

    @FXML
    private RadioButton inHouseRadioButton;

    @FXML
    private TextField inventoryLevelField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField nameField;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private ToggleGroup partTypeGroup;

    @FXML
    private TextField priceCostField;

    @FXML
    private TextField minField;

    @FXML
    private Button saveButton;

    private Inventory inventory;

    private Stage dialogStage;

    private MainController mainController;

    public AddPartController(Inventory inventory, MainController mainController) {
        this.inventory = inventory;
        this.mainController = mainController;
    }

    public AddPartController() {

    }

    @FXML
    public void initialize() {
        partTypeGroup = new ToggleGroup();
        this.inHouseRadioButton.setToggleGroup(partTypeGroup);
        this.outsourcedRadioButton.setToggleGroup(partTypeGroup);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @FXML
    private void handleSaveButtonClick() {
        try {
            String name = nameField.getText();
            int inventoryLevel = Integer.parseInt(inventoryLevelField.getText());
            double priceCost = Double.parseDouble(priceCostField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());
            String companyMachineId = companyMachineIdField.getText();

            if (name.isEmpty() || priceCostField.getText().isEmpty() || inventoryLevelField.getText().isEmpty()) {
                throw new PartDetailsException("Part must have a name, price, and inventory level.");
            }

            if (min > max) {
                throw new MinMaxValueException("Min value cannot be greater than max value.");
            }

            if (inventoryLevel < min || inventoryLevel > max) {
                throw new InventoryValueException("Inventory value must be between min and max values.");
            }

            Part part;
            if (inHouseRadioButton.isSelected()) {
                part = new InHouse(name, priceCost, inventoryLevel, min, max, Integer.parseInt(companyMachineId));
            } else {
                part = new Outsourced(name, priceCost, inventoryLevel, min, max, companyMachineId);
            }
            inventory.addPart(part);

            nameField.clear();
            inventoryLevelField.clear();
            priceCostField.clear();
            maxField.clear();
            minField.clear();
            companyMachineIdField.clear();

            mainController.updatePartsObservableList();

            dialogStage.close();
        } catch (PartDetailsException | MinMaxValueException | InventoryValueException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred while saving the part.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancelButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Part Creation");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dialogStage.close();
        }
    }

}
