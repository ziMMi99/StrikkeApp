package com.strikkeapp.strikkeapp.controllers.loginControllers;

import com.strikkeapp.strikkeapp.Application;
import com.strikkeapp.strikkeapp.data.DataHandler;
import com.strikkeapp.strikkeapp.data.LoginData;
import com.strikkeapp.strikkeapp.dbo.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class LoginController {
    @FXML
    private TextField usernameField;

    public static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    @FXML
    private PasswordField passwordField;

    public void setSignupHyperlinkAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/strikkeapp/strikkeapp/register.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/strikkeapp/strikkeapp/projects.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Application.class.getResource("projects.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public boolean checkUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
        LoginData data = new LoginData();

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());

        StringBuilder HashedInputPassword = new StringBuilder();
        for (byte b : hash) {
            HashedInputPassword.append(String.format("%02x", b));
        }

        return data.getHashedPassword(username).contentEquals(HashedInputPassword);
    }


    public void setLoginButtonAction(ActionEvent event)  {
        String inputUsername = usernameField.getText();
        String inputPassword = passwordField.getText();
        LoginData loginData = new LoginData();
        try {
            boolean matching = checkUsernameAndPassword(inputUsername, inputPassword);
            if (matching) {
                String currentUserID = loginData.getUserIDByName(inputUsername);
                currentUser = new User(currentUserID, inputUsername);
                switchToHome(event);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setContentText("Username and password does not match!");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isEmpty()) {
                    System.out.println("Alert closed");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not find algorithm - " + e.getMessage());
        }

    }
}