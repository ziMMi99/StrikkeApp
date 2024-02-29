package com.strikkeapp.strikkeapp.controllers;

import com.strikkeapp.strikkeapp.dbo.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


import java.net.URL;
import java.util.ResourceBundle;

public class ProjectDetailsController implements Initializable {
    @FXML
    private Label projectNameLabel;
    @FXML
    private TextArea projectDescriptionTextArea;
    @FXML
    private TextArea projectNotesTextArea;

    Project currentProject = ProjectController.getCurrentProject();

    public void setProjectInfo(Project project) {
        projectNameLabel.setText(project.getName());
        projectDescriptionTextArea.setText(project.getDescription());
        projectNotesTextArea.setText(project.getNote());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProjectInfo(currentProject);

    }
}
