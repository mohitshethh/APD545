package com.mohit.workshop4n5.controller;

import com.mohit.workshop4n5.exceptions.*;
import com.mohit.workshop4n5.model.Inventory;
import com.mohit.workshop4n5.model.Part;
import com.mohit.workshop4n5.model.Product;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class ModifyProductController {

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
    private Button removeAssociatedPartButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchPartField;

    public static Inventory inventory;
    public static Product productToModify;
    public static Stage dialogStage;
    private MainController mainController;


    public ModifyProductController() {
    }

    public ModifyProductController(Inventory inventory, Product productToModify, Stage dialogStage) {
        this.inventory = inventory;
        this.productToModify = productToModify;
        this.dialogStage = dialogStage;
    }
    public ModifyProductController(Inventory _inventory, MainController mainController) {
        this.inventory = _inventory;
        this.mainController = mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public static void setDialogStage(Stage _dialogStage) {
        dialogStage = _dialogStage;
    }

    public static void setInventory(Inventory _inventory) {
        inventory = _inventory;
    }

    public static void setProductToModify(Product _product) {
        productToModify = _product;
    }

    @FXML
    public void initialize() {
        idField.setText(String.valueOf(productToModify.getId()));
        nameField.setText(productToModify.getName());
        inventoryLevelField.setText(String.valueOf(productToModify.getStock()));
        priceCostField.setText(String.valueOf(productToModify.getPrice()));
        maxField.setText(String.valueOf(productToModify.getMax()));
        minField.setText(String.valueOf(productToModify.getMin()));

        partsTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
        associatedPartsTable.setItems(FXCollections.observableArrayList(productToModify.getAllAssociatedParts()));

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
    private void handleSaveButtonClick() throws ProductDetailsException, ProductPriceException, MinMaxValueException, InventoryValueException {        try {

        if (nameField.getText().isEmpty() || priceCostField.getText().isEmpty() || inventoryLevelField.getText().isEmpty()) {
            throw new ProductDetailsException("Product must have a name, price, and inventory level.");
        }

        double price = Double.parseDouble(priceCostField.getText());
        double partsCost = associatedPartsTable.getItems().stream().mapToDouble(Part::getPrice).sum();
        if (price < partsCost) {
            throw new ProductPriceException("Product price cannot be less than the cost of the parts.");
        }

        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());
        if (min > max) {
            throw new MinMaxValueException("Min value cannot be greater than max value.");
        }

        int inventory = Integer.parseInt(inventoryLevelField.getText());
        if (inventory < min || inventory > max) {
            throw new InventoryValueException("Inventory value must be between min and max values.");
        }

        if (associatedPartsTable.getItems().isEmpty()) {
            throw new ProductPartsException("Product must have at least one part.");
        }

        productToModify.setName(nameField.getText());
        productToModify.setPrice(price);
        productToModify.setStock(inventory);
        productToModify.setMax(max);
        productToModify.setMin(min);
        productToModify.setAssociatedParts(associatedPartsTable.getItems());

        mainController.updateProductsObservableList();

        dialogStage.close();
    } catch (ProductDetailsException | ProductPriceException | MinMaxValueException | InventoryValueException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred while saving the product.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    } catch (ProductPartsException e) {
        throw new RuntimeException(e);
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
        alert.setHeaderText("Cancel Part Modification");
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
