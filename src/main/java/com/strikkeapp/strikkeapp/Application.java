package com.strikkeapp.strikkeapp;

import com.strikkeapp.strikkeapp.controllers.HomeController;
import com.strikkeapp.strikkeapp.data.DataHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("projects.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Strikke App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}