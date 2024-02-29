package com.strikkeapp.strikkeapp.dbo;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;

public class ProjectBox extends StackPane {
    public ProjectBox(String projectName) {
        Label label = new Label(projectName);
        getChildren().add(label);
    }
}
