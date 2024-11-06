package com.mohit.workshop2.controller;

import java.util.ArrayList;
import java.util.List;

import com.mohit.workshop2.model.Customer;
import com.mohit.workshop2.model.Pizza;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OrderController {

    @FXML
    private CheckBox cbAnchovies, cbBacon, cbDacon, cbDriedShrimps, cbExtraCheese, cbGrilledChicken,
            cbGroundBeef, cbHam, cbJalapeno, cbMushrooms, cbPepperoni, cbRoastedGarlic,
            cbShreddedChicken, cbSpinach, cbSunDriedTomatoes, cdPineapple;

    @FXML
    private RadioButton rbDeepDish, rbExtraLarge, rbLarge, rbMedium, rbSmall, rbNormal, rbThin;

    @FXML
    private ToggleGroup pizzaSizeGroup;

    @FXML
    private ToggleGroup crustTypeGroup;

    @FXML
    private Spinner<Integer> spinnerQuantity;


    @FXML
    private TextField tfCustomer;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextArea taSummary;

    @FXML
    private final List<CheckBox> normalToppingsCheckBoxes = new ArrayList<>();

    @FXML
    private final List<CheckBox> meatToppingsCheckBoxes = new ArrayList<>();


    private Customer customer;
    private Pizza pizza;
    private int quantity;

    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        spinnerQuantity.setValueFactory(valueFactory);

        normalToppingsCheckBoxes.add(cdPineapple);
        normalToppingsCheckBoxes.add(cbSunDriedTomatoes);
        normalToppingsCheckBoxes.add(cbSpinach);
        normalToppingsCheckBoxes.add(cbJalapeno);
        normalToppingsCheckBoxes.add(cbRoastedGarlic);
        normalToppingsCheckBoxes.add(cbMushrooms);
        normalToppingsCheckBoxes.add(cbExtraCheese);
        normalToppingsCheckBoxes.add(cbDacon);
        normalToppingsCheckBoxes.add(cbDriedShrimps);
        normalToppingsCheckBoxes.add(cbAnchovies);

        meatToppingsCheckBoxes.add(cbShreddedChicken);
        meatToppingsCheckBoxes.add(cbPepperoni);
        meatToppingsCheckBoxes.add(cbHam);
        meatToppingsCheckBoxes.add(cbGroundBeef);
        meatToppingsCheckBoxes.add(cbGrilledChicken);
        meatToppingsCheckBoxes.add(cbBacon);
    }

    @FXML
    void btnClear(ActionEvent event) {
        tfCustomer.clear();
        tfPhone.clear();
        spinnerQuantity.getValueFactory().setValue(1);
        pizzaSizeGroup.selectToggle(null);
        crustTypeGroup.selectToggle(null);
        clearCheckboxes(normalToppingsCheckBoxes);
        clearCheckboxes(meatToppingsCheckBoxes);
    }

    @FXML
    void btnPlaceOrder(ActionEvent event) {
        String customerName = tfCustomer.getText();
        String customerNumber = tfPhone.getText();
        quantity = spinnerQuantity.getValue();

        customer = new Customer(customerName, customerNumber, quantity);

        String size = ((RadioButton)pizzaSizeGroup.getSelectedToggle()).getText();
        String crust = ((RadioButton)crustTypeGroup.getSelectedToggle()).getText();

        List<String> selectedToppings = new ArrayList<>();
        for (CheckBox checkBox : normalToppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedToppings.add(checkBox.getText());
            }
        }
        for (CheckBox checkBox : meatToppingsCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedToppings.add(checkBox.getText());
            }
        }

        pizza = new Pizza(size, crust, selectedToppings);
        double totalPrice = pizza.calculateTotalPrice(size, crust, selectedToppings);

        StringBuilder orderSummary = new StringBuilder();
        orderSummary.append("Quantity: ").append(quantity).append("\n\n");
        orderSummary.append("Pizza Size: ").append(size).append("\n");
        orderSummary.append("Crust Type: ").append(crust).append("\n");
        orderSummary.append("Toppings: ").append(String.join(", ", selectedToppings)).append("\n\n");
        orderSummary.append("Total Price: $").append(String.format("%.2f", totalPrice)).append("\n");

        taSummary.setText(orderSummary.toString());
    }

    @FXML
    void btnOrderSummary(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mohit/workshop2/OrderSummary.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Order Summary");
            stage.setScene(new Scene(root));

            OrderSummaryController summaryController = loader.getController();
            String summaryText = getOrderSummary(customer, pizza, quantity);
            summaryController.setSummaryText(summaryText);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getOrderSummary(Customer customer, Pizza pizza, int quantity) {
        if (customer == null || pizza == null) {
            return "Order details are not available.";
        }

        StringBuilder summary = new StringBuilder("Order Summary:\n");
        summary.append("Customer Name: ").append(customer.getName()).append("\n");
        summary.append("Customer Phone: ").append(customer.getPhoneNumber()).append("\n");
        summary.append("Pizza Size: ").append(pizza.getSize()).append("\n");
        summary.append("Crust: ").append(pizza.getCrust()).append("\n");
        summary.append("Quantity: ").append(quantity).append("\n\n");

        double pizzaPrice = pizza.calculateTotalPrice(pizza.getSize(), pizza.getCrust(), pizza.getToppings()); // Calculate total price per pizza
        double totalBeforeTax = pizzaPrice * quantity;
        summary.append("Total before tax: $").append(String.format("%.2f", totalBeforeTax)).append("\n");

        double taxRate = 0.08; // Tax rate
        double totalAfterTax = totalBeforeTax * (1 + taxRate);
        summary.append("Total to be paid (after tax): $").append(String.format("%.2f", totalAfterTax)).append("\n");

        return summary.toString();
    }

    private void clearCheckboxes(List<CheckBox> checkboxes) {
        for (CheckBox checkBox : checkboxes) {
            if (checkBox != null) {
                checkBox.setSelected(false);
            }
        }
    }
}
