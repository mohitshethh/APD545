/**********************************************
 Workshop #2
 Course:APD545
 Last Name: Sheth
 First Name: Mohit
 ID:114498223
 Section: ZAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date: 10/6/2024
 **********************************************/

package com.mohit.workshop2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("orderView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Pizza Ordering Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}