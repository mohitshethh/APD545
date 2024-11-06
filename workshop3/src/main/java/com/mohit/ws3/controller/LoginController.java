    package com.mohit.ws3.controller;

    import com.mohit.ws3.startApplication;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.Node;
    import javafx.scene.Scene;
    import javafx.scene.control.Alert;
    import javafx.scene.control.PasswordField;
    import javafx.scene.control.TextField;
    import javafx.stage.Stage;

    import java.io.IOException;

    public class LoginController{
        @FXML
        private TextField usernameTF;

        @FXML
        private PasswordField passwordTF;

        @FXML
        public void onButtonClick(ActionEvent actionEvent) throws IOException {
            String username = usernameTF.getText();
            String password = passwordTF.getText();

            if (("user1".equals(username) && "pass1".equals(password)) ||
                    ("user2".equals(username) && "pass2".equals(password))) {
                FXMLLoader fxmlLoader = new FXMLLoader(startApplication.class.getResource("CustomerDetailView.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Customer Details");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();

                //close the current window
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.close();
            } else {
                showErrorAlert("Incorrect Username or Password!");
            }
        }

        private void showErrorAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }