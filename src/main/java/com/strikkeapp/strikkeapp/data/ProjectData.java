package com.strikkeapp.strikkeapp.data;

import com.strikkeapp.strikkeapp.controllers.loginControllers.LoginController;
import com.strikkeapp.strikkeapp.dbo.Project;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ProjectData class, that extends DataHandler.
 * Takes care of all sql, and database related code
 * Makes use of "makeCall()" method from DataHandler
 *
 * @author StrikkeApp team
 */
public class ProjectData extends DataHandler{

    /**
     * Makes a call to a stored procedure, which inserts a project into the database
     *
     * @param project - The project to be inserted
     */
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

    /**
     * Gets all projects made by a specific user.
     *
     * @param userID - The user whose projects are to be returned
     * @return ArrayList - All projects made by the user
     */
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

    /**
     * Method to retrieve a specific project from the database using the name
     *
     * @param projectName - the name of the project to find in the database
     * @return Project - and instance of the project class
     */
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
