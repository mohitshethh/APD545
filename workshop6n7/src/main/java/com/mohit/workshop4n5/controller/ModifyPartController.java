package com.mohit.workshop4n5.controller;

import com.mohit.workshop4n5.exceptions.InventoryValueException;
import com.mohit.workshop4n5.exceptions.MinMaxValueException;
import com.mohit.workshop4n5.exceptions.PartDetailsException;
import com.mohit.workshop4n5.model.InHouse;
import com.mohit.workshop4n5.model.Inventory;
import com.mohit.workshop4n5.model.Outsourced;
import com.mohit.workshop4n5.model.Part;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class ModifyPartController {

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
    private TextField minField;

    @FXML
    private TextField nameField;

    @FXML
    private RadioButton outsourcedRadioButton;

    @FXML
    private TextField priceCostField;

    @FXML
    private ToggleGroup toggleGroup;

    public static Inventory inventory;
    public static Part partToModify;
    public static Stage dialogStage;

    private MainController mainController;

    public ModifyPartController() {
    }

    public ModifyPartController(Inventory inventory, Part partToModify, Stage dialogStage) {
        this.inventory = inventory;
        this.partToModify = partToModify;
        this.dialogStage = dialogStage;
    }
    public ModifyPartController(Inventory inventory, MainController mainController) {
        this.inventory = inventory;
        this.mainController = mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public static void setDialogStage(Stage _dialogStage) {
        dialogStage = _dialogStage;
    }
    @FXML
    public static void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }


    public static void setPartToModify(Part _partToModify) {
        partToModify = _partToModify;
    }
    @FXML
    public void initialize() {

        inHouseRadioButton.setDisable(true);
        outsourcedRadioButton.setDisable(true);
        idField.setText(String.valueOf(partToModify.getId()));
        nameField.setText(partToModify.getName());
        inventoryLevelField.setText(String.valueOf(partToModify.getStock()));
        priceCostField.setText(String.valueOf(partToModify.getPrice()));
        maxField.setText(String.valueOf(partToModify.getMax()));
        minField.setText(String.valueOf(partToModify.getMin()));

        if (partToModify instanceof InHouse) {
            inHouseRadioButton.setSelected(true);
            companyMachineIdField.setText(String.valueOf(((InHouse) partToModify).getMachine()));
        } else {
            outsourcedRadioButton.setSelected(true);
            companyMachineIdField.setText(((Outsourced) partToModify).getCompanyName());
        }
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

            partToModify.setName(name);
            partToModify.setPrice(priceCost);
            partToModify.setStock(inventoryLevel);
            partToModify.setMax(max);
            partToModify.setMin(min);
            if (partToModify instanceof InHouse) {
                ((InHouse) partToModify).setMachine(Integer.parseInt(companyMachineId));
            } else {
                ((Outsourced) partToModify).setCompanyName(companyMachineId);
            }

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
        alert.setHeaderText("Cancel Part Modification");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dialogStage.close();
        }
    }
}
