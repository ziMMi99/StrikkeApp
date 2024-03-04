package com.strikkeapp.strikkeapp.data;

import com.strikkeapp.strikkeapp.controllers.loginControllers.LoginController;
import com.strikkeapp.strikkeapp.dbo.Project;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectData extends DataHandler{

    public void insertProjectIntoDB(Project project) {

        try (CallableStatement cs = makeCall("{CALL project_addProject(?, ?, ?, ?)}")) {
            cs.setString(1, project.getName());
            cs.setString(2, LoginController.getCurrentUser().getUserID());
            cs.setString(3, project.getDescription());
            cs.setString(4, project.getNote());

            cs.execute();

        } catch (SQLException e) {
            System.out.println("Could not insert into database - " + e.getMessage());
        }
    }

    public ArrayList<Project> getProjectsByUserID(String userID) {
        ArrayList<Project> projects = new ArrayList<>();
        try (CallableStatement cs = makeCall("{CALL project_getProjectsByUserID(?)}")) {
            cs.setString(1, userID);
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("pro_name");
                String description = resultSet.getString("pro_desc");
                String note = resultSet.getString("pro_note");

                Project project = new Project(name, description, note);
                projects.add(project);
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve projects from the database - " + e.getMessage());
        }
        return projects;
    }

    public Project getProjectByName(String projectName) {
        Project project = null;
        try (CallableStatement cs = makeCall("{CALL project_getProjectByName(?)}")){
            cs.setString(1, projectName);
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("pro_name");
                String description = resultSet.getString("pro_desc");
                String note = resultSet.getString("pro_note");

                project = new Project(name, description, note);
            }
        } catch (SQLException e) {

        }
        return project;
    }
}
