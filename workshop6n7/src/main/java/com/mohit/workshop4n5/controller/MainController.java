package com.mohit.workshop4n5.controller;

import com.mohit.workshop4n5.StartApplication;
import com.mohit.workshop4n5.exceptions.ProductDeleteException;
import com.mohit.workshop4n5.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class MainController {

    @FXML
    private Button addPartButton;

    @FXML
    private Button addProductButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private TextField searchPartField;

    @FXML
    private TextField searchProductField;

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
    private TableColumn<Part, Integer> productIdColumn;

    @FXML
    private TableColumn<Part, String> productNameColumn;

    @FXML
    private TableColumn<Part, Double> productPriceColumn;

    @FXML
    private TableColumn<Part, Integer> productStockColumn;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private Inventory inventory = new Inventory();

    private final ObservableList<Part> partsObservableList;
    private final ObservableList<Product> productsObservableList;

    public MainController() {
        partsObservableList = FXCollections.observableArrayList(inventory.getAllParts());
        productsObservableList = FXCollections.observableArrayList(inventory.getAllProducts());
        productsObservableList.addListener((ListChangeListener<Product>) c -> {
            productsTable.setItems(productsObservableList);
        });

        /// Add sample parts to the inventory
        Part part1 = new InHouse("Engine", 500.0, 10, 1, 20, 1234);
        inventory.addPart(part1);
        Part part2 = new Outsourced("Transmission", 1200.0, 7, 1, 15, "AutoParts Co.");
        inventory.addPart(part2);
        Part part3 = new InHouse("Brake Pads", 70.0, 50, 1, 100, 5678);
        inventory.addPart(part3);
        Part part4 = new Outsourced("Windshield", 200.0, 15, 1, 30, "GlassWorks Inc.");
        inventory.addPart(part4);
        Part part5 = new InHouse("Air Filter", 10.0, 100, 1, 200, 9012);
        inventory.addPart(part5);
        Part part6 = new Outsourced("Oil Filter", 10.0, 100, 1, 200, "Filter Kings");
        inventory.addPart(part6);
        Part part7 = new InHouse("Alternator", 100.0, 10, 1, 20, 3456);
        inventory.addPart(part7);
        Part part8 = new Outsourced("Battery", 120.0, 15, 1, 30, "Energy Plus");
        inventory.addPart(part8);
        Part part9 = new InHouse("Spark Plug", 5.0, 100, 1, 200, 7890);
        inventory.addPart(part9);
        Part part10 = new Outsourced("Tire", 80.0, 50, 1, 100, "Rubber Manufacturers");
        inventory.addPart(part10);

        // Add sample products to the inventory
        Product product1 = new Product("Sedan", 20000.0, 5, 1, 10);
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        product1.addAssociatedPart(part3);
        inventory.addProduct(product1);

        Product product2 = new Product("SUV", 25000.0, 3, 1, 6);
        product2.addAssociatedPart(part1);
        product2.addAssociatedPart(part2);
        product2.addAssociatedPart(part4);
        inventory.addProduct(product2);

        Product product3 = new Product("Pickup Truck", 30000.0, 4, 1, 8);
        product3.addAssociatedPart(part1);
        product3.addAssociatedPart(part2);
        product3.addAssociatedPart(part5);
        inventory.addProduct(product3);

        Product product4 = new Product("Sports Car", 60000.0, 2, 1, 4);
        product4.addAssociatedPart(part1);
        product4.addAssociatedPart(part2);
        product4.addAssociatedPart(part6);
        inventory.addProduct(product4);

        Product product5 = new Product("Minivan", 28000.0, 3, 1, 6);
        product5.addAssociatedPart(part1);
        product5.addAssociatedPart(part2);
        product5.addAssociatedPart(part7);
        inventory.addProduct(product5);

        Product product6 = new Product("Convertible", 35000.0, 2, 1, 4);
        product6.addAssociatedPart(part1);
        product6.addAssociatedPart(part2);
        product6.addAssociatedPart(part8);
        inventory.addProduct(product6);

        Product product7 = new Product("Luxury Sedan", 40000.0, 3, 1, 6);
        product7.addAssociatedPart(part1);
        product7.addAssociatedPart(part2);
        product7.addAssociatedPart(part9);
        inventory.addProduct(product7);

        Product product8 = new Product("Luxury SUV", 45000.0, 2, 1, 4);
        product8.addAssociatedPart(part1);
        product8.addAssociatedPart(part2);
        product8.addAssociatedPart(part10);
        inventory.addProduct(product8);

        Product product9 = new Product("Electric Car", 35000.0, 3, 1, 6);
        product9.addAssociatedPart(part4);
        product9.addAssociatedPart(part5);
        product9.addAssociatedPart(part6);
        inventory.addProduct(product9);

        Product product10 = new Product("Hybrid", 30000.0, 4, 1, 8);
        product10.addAssociatedPart(part7);
        product10.addAssociatedPart(part8);
        product10.addAssociatedPart(part9);
        inventory.addProduct(product10);

    }

    public void updateProductsObservableList() {
        productsObservableList.setAll(inventory.getAllProducts());
    }
    public void updatePartsObservableList() {
        partsObservableList.setAll(inventory.getAllParts());
    }

    public void initialize() {
        updateProductsObservableList();
        updatePartsObservableList();

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTable.setItems(partsObservableList);
        productsTable.setItems(productsObservableList);

        searchPartField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                partsTable.setItems(FXCollections.observableArrayList(inventory.getAllParts()));
            } else {
                List<Part> matchingPartsByName = inventory.searchPartByName(newValue);
                Part matchingPartById = inventory.searchPartByID(newValue);
                if (matchingPartById != null) {
                    matchingPartsByName.add(matchingPartById);
                }
                partsTable.setItems(FXCollections.observableArrayList(matchingPartsByName));
            }
        });

        searchProductField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                productsTable.setItems(FXCollections.observableArrayList(inventory.getAllProducts()));
            } else {
                List<Product> matchingProducts = inventory.searchProductByName(newValue);
                productsTable.setItems(FXCollections.observableArrayList(matchingProducts));
            }
        });
    }

    @FXML
    private void handleAddPartButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("/com/mohit/workshop4n5/AddPart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddPartController controller = fxmlLoader.getController();
        controller.setInventory(inventory);
        controller.setMainController(this);
        Stage stage = new Stage();
        controller.setDialogStage(stage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleModifyPartButtonClick() throws IOException {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart != null) {
            ModifyPartController.setPartToModify(selectedPart);
            ModifyPartController.setInventory(inventory);
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("ModifyPart.fxml"));
            Parent parent = fxmlLoader.load();
            ModifyPartController controller = fxmlLoader.getController();
            controller.setMainController(this);
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            ModifyPartController.setDialogStage(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Part Selected");
            alert.setHeaderText("No Part Selected");
            alert.setContentText("Please select a part from the table to modify");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeletePart() {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Part");
        alert.setContentText("Are you sure you want to delete this part?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            inventory.deletePart(selectedPart);
            partsObservableList.setAll(inventory.getAllParts());
            partsTable.setItems(partsObservableList);
        }
    }

    @FXML
    private void handleAddProduct() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("AddProduct.fxml"));
        fxmlLoader.setControllerFactory(c -> new AddProductController(inventory, this));
        Scene scene = new Scene(fxmlLoader.load());
        AddProductController controller = fxmlLoader.getController();
        Stage stage = new Stage();
        controller.setMainController(this);
        controller.setDialogStage(stage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleModifyProduct() throws IOException {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            ModifyProductController.setProductToModify(selectedProduct);
            ModifyProductController.setInventory(inventory);
            FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("ModifyProduct.fxml"));
            Parent parent = fxmlLoader.load();

            ModifyProductController controller = fxmlLoader.getController();

            controller.setMainController(this);

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            ModifyProductController.setDialogStage(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Product Selected");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product from the table to modify");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteProduct() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Product");
        alert.setContentText("Are you sure you want to delete this product?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            try {
                if (selectedProduct != null && !selectedProduct.getAllAssociatedParts().isEmpty()) {
                    throw new ProductDeleteException("Cannot delete a product that has a part assigned to it.");
                }

                inventory.deleteProduct(selectedProduct);

                updateProductsObservableList();
            } catch (ProductDeleteException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("An error occurred while deleting the product.");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }

    @FXML
    private void handleExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Exit Program");
        alert.setContentText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            Platform.exit();
        }
    }


}
