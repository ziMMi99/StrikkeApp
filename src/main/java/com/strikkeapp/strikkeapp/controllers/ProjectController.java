package com.strikkeapp.strikkeapp.controllers;

import com.strikkeapp.strikkeapp.Application;
import com.strikkeapp.strikkeapp.controllers.loginControllers.LoginController;
import com.strikkeapp.strikkeapp.data.DataHandler;
import com.strikkeapp.strikkeapp.data.ProjectData;
import com.strikkeapp.strikkeapp.dbo.Project;
import com.strikkeapp.strikkeapp.dbo.ProjectBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the controller for the projects view. It is responsible for managing the display of projects and their details, as well as handling user interactions with projects.
 *
 * @author StrikeApp Team
 */
public class ProjectController implements Initializable {

    /**
     * The FlowPane that contains the project boxes.
     */
    @FXML
    private FlowPane projectFlowBox;

    /**
     * The current project that is being viewed.
     */
    private static Project currentProject;

    /**
     * Returns the current project that is being viewed.
     *
     * @return the current project
     */
    public static Project getCurrentProject() {
        return currentProject;
    }

    /**
     * Adds a new project to the list of projects.
     */
    public void setAddProjectBtn() {
        TextInputDialog textInputDialog = new TextInputDialog();
        textInputDialog.getDialogPane().getStylesheets().add(Application.class.getResource("projects.css").toExternalForm());

        textInputDialog.setTitle("New Project");
        textInputDialog.setHeaderText("Enter Project information");

        textInputDialog.getDialogPane().getButtonTypes().set(0, ButtonType.FINISH);

        TextField pro_name = new TextField();
        TextArea pro_desc = new TextArea();
        TextArea pro_note = new TextArea();
        pro_note.setPrefSize(100, 100);
        pro_name.setPromptText("Enter project name");
        pro_desc.setPromptText("Enter project Description");
        pro_note.setPromptText("Enter project notes");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        grid.add(new Label("Project name: "), 1, 1);
        grid.add(pro_name, 1, 1);
        grid.add(new Label("Project Description: "), 1, 2);
        grid.add(pro_desc, 1, 2);
        grid.add(new Label("Project Notes: "), 1, 3);
        grid.add(pro_note, 1, 3);

        textInputDialog.getDialogPane().setContent(grid);

        textInputDialog.setResultConverter(dialogButton -> {
            ArrayList<String> projectInfo = new ArrayList<>();
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

    /**
     * Adds a project box to the list of projects and sets its properties.
     *
     * @param project the project to add
     */
    private void addProject(Project project) {
        ProjectBox projectBox = new ProjectBox(project.getName());

        projectFlowBox.getChildren().add(projectBox);
        projectBox.getStyleClass().add("project-box");

        projectBox.setOnMouseClicked(mouseEvent -> {
            try {
                openProject(project.getName(), mouseEvent);
            } catch (IOException e) {
                e.getMessage();
            }
        });
    }

    /**
     * Opens the project details view for the specified project.
     *
     * @param projectTitle the title of the project
     * @param event the mouse event that triggered the action
     * @throws IOException if there is an error loading the FXML file
     */
    public void openProject(String projectTitle, MouseEvent event) throws IOException {
        ProjectData data = new ProjectData();
        currentProject = data.getProjectByName(projectTitle);

        FXMLLoader loader = new FXMLLoader(Application.class.getResource("project_details.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Application.class.getResource("project_details.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProjectData data = new ProjectData();
        ArrayList<Project> projects = data.getProjectsByUserID(LoginController.currentUser.getUserID());
        for (Project project : projects) {
            addProject(project);
        }
    }
}
