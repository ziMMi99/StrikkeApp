package com.strikkeapp.strikkeapp.controllers;

import com.strikkeapp.strikkeapp.data.ProjectData;
import com.strikkeapp.strikkeapp.dbo.Project;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Optional;

public class ProjectController {

    @FXML
    Button addProjectBtn;
    @FXML
    TextField projectName;
    @FXML
    TextArea projectDescripton, projectNotes;


    public void setAddProjectBtn() {
        TextInputDialog textInputDialog = new TextInputDialog();

        textInputDialog.setTitle("New Project");
        textInputDialog.setHeaderText("Enter Project information");

        textInputDialog.getDialogPane().getButtonTypes().set(0, ButtonType.FINISH);

        TextField pro_name = new TextField();
        TextArea pro_desc = new TextArea();
        TextArea pro_note = new TextArea();
        pro_name.setPromptText("Enter project name");
        pro_desc.setPromptText("Enter project Description");
        pro_note.setPromptText("Enter project notes");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        grid.add(new Label("Project name: "), 1,1);
        grid.add(pro_name,1,1);
        grid.add(new Label("Project Description: "), 1,2);
        grid.add(pro_desc,1,2);
        grid.add(new Label("Project Notes: "), 1, 3);
        grid.add(pro_note,1,3);


        textInputDialog.getDialogPane().setContent(grid);

        textInputDialog.setResultConverter(dialogButton -> {
            ArrayList<String> projectInfo = new ArrayList();
            if (dialogButton == ButtonType.FINISH) {
                projectInfo.add(pro_name.getText());
                projectInfo.add(pro_desc.getText());
                projectInfo.add(pro_note.getText());

                Project project = new Project(projectInfo.get(0), projectInfo.get(1), projectInfo.get(2));

                ProjectData projectData = new ProjectData();
                projectData.insertProjectIntoDB(project);

                addProject(project);

                return String.valueOf(projectInfo);
            }
            return null;
        });

        Optional<String> result = textInputDialog.showAndWait();


    }

    private void addProject(Project project) {
        System.out.println(project);

    }

}
