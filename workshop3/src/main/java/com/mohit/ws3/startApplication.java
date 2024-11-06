/**********************************************
 Workshop #3
 Course: DSA545
 Last Name: Sheth
 First Name:Mohit
 ID:114498223
 Section: ZAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date:1/11/2024
 **********************************************/

package com.mohit.ws3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class startApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(startApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login Window");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}