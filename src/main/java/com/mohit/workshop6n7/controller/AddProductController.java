package com.mohit.workshop6n7.controller;

import com.mohit.workshop6n7.exceptions.*;
import com.mohit.workshop6n7.model.Inventory;
import com.mohit.workshop6n7.model.Part;
import com.mohit.workshop6n7.model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class AddProductController {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;

    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartStockColumn;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField inventoryLevelField;

    @FXML
    private TextField maxField;

    @FXML
    private TextField minField;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableColumn<Part, Integer> partStockColumn;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TextField priceCostField;

    @FXML
    private Button removeButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchPartField;

    private Inventory inventory;
    private Stage dialogStage;
    private MainController mainController;

    public AddProductController(Inventory inventory, MainController mainController) {
        this.inventory = inventory;
        this.mainController = mainController;
    }

    public AddProductController() {
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    @FXML
    public void initialize() {
        partsTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    @FXML
    private void handleSaveButtonClick() {
        try {
            String name = nameField.getText();
            int inventoryLevel = Integer.parseInt(inventoryLevelField.getText());
            double price = Double.parseDouble(priceCostField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());

            if (name.isEmpty() || priceCostField.getText().isEmpty() || inventoryLevelField.getText().isEmpty()) {
                throw new ProductDetailsException("Product must have a name, price, and inventory level.");
            }

            double partsCost = associatedPartsTable.getItems().stream().mapToDouble(Part::getPrice).sum();
            if (price < partsCost) {
                throw new ProductPriceException("Product price cannot be less than the cost of the parts.");
            }

            if (min > max) {
                throw new MinMaxValueException("Min value cannot be greater than max value.");
            }

            if (inventoryLevel < min || inventoryLevel > max) {
                throw new InventoryValueException("Inventory value must be between min and max values.");
            }

            if (associatedPartsTable.getItems().isEmpty()) {
                throw new ProductPartsException("Product must have at least one part.");
            }

            Product newProduct = new Product(name, price, inventoryLevel, min, max);
            for (Part part : associatedPartsTable.getItems()) {
                newProduct.addAssociatedPart(part);
            }

            inventory.addProduct(newProduct);
            mainController.updatePartsObservableList();
            mainController.updateProductsObservableList();
            dialogStage.close();
        } catch (ProductDetailsException | ProductPriceException | MinMaxValueException | InventoryValueException |
                 ProductPartsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred while saving the product.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddButtonClick() {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedPartsTable.getItems().add(selectedPart);
        }
    }

    @FXML
    private void handleRemoveAssociatedPartButtonClick() {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            associatedPartsTable.getItems().remove(selectedPart);
        }
    }

    @FXML
    private void handleCancelButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Product Creation");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dialogStage.close();
        }
    }

    @FXML
    private void handleSearchPart() {
        String searchValue = searchPartField.getText();
        List<Part> matchingPartsByName = inventory.searchPartByName(searchValue);
        Part matchingPartById = inventory.searchPartByID(searchValue);
        if (matchingPartById != null) {
            matchingPartsByName.add(matchingPartById);
        }
        partsTable.setItems(FXCollections.observableArrayList(matchingPartsByName));
    }
}
