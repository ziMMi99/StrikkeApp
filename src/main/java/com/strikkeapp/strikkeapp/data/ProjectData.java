package com.strikkeapp.strikkeapp.data;

import com.strikkeapp.strikkeapp.dbo.Project;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectData {

    public void insertProjectIntoDB(Project project) {
        DataHandler data = new DataHandler();

        try (CallableStatement statement = data.makeCall("CALL project_addProject(?)(?)(?)")) {
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setString(3, project.getNote());

            statement.executeQuery();

        } catch (SQLException e) {
            System.out.println("Could not insert into database - " + e.getMessage());
        }
    }
}
