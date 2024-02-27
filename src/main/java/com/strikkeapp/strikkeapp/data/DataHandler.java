package com.strikkeapp.strikkeapp.data;

import java.sql.*;

public class DataHandler {

    private Connection connection;

    public DataHandler() {
        loadJdbcDriver();
        openConnection();
    }

    private void loadJdbcDriver() {
        try {
            System.out.println("Loading JDBC driver...");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            System.out.println("JDBC driver loaded");

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver!");
        }
    }

    private void openConnection() {
        try {
            String connectionString =
                    "jdbc:sqlserver://localhost:1433;" +
                            "instanceName=SQLEXPRESS;" +
                            "databaseName=" + "Strik_DB" + ";" +
                            "integratedSecurity=true;" +
                            "trustServerCertificate=true;";

            System.out.println(connectionString);

            System.out.println("Connecting to the database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to the database");

        } catch (SQLException e) {

            System.out.println("Could not connect to database: " + "Strik_DB");
            System.out.println("Could not connect to the database: " + "Strik_DB");

            System.out.println(e.getMessage());
        }
    }

    public ResultSet executeStoredProcedure(String procedure, String input) {
        ResultSet resultSet = null;
        try (CallableStatement cs = connection.prepareCall(procedure)) {
            cs.setString(1, input);
            resultSet = cs.executeQuery();
        } catch (SQLException e) {
            System.out.println("Could not create callable statement - " + e.getMessage());
        }
        return resultSet;
    }

    public ResultSet executeStoredProcedure(String procedure, String input, String input2) {
        ResultSet resultSet = null;
        try (CallableStatement cs = connection.prepareCall(procedure)) {
            cs.setString(1, input);
            cs.setString(2, input2);
            resultSet = cs.executeQuery();
        } catch (SQLException e) {
            System.out.println("Could not create callable statement - " + e.getMessage());
        }
        return resultSet;
    }

    public ResultSet executeStoredProcedure(String procedure, int input) {
        ResultSet resultSet = null;
        try (CallableStatement cs = connection.prepareCall(procedure)) {
            cs.setInt(1, input);
            resultSet = cs.executeQuery();
        } catch (SQLException e) {
            System.out.println("Could not create callable statement - " + e.getMessage());
        }
        return resultSet;
    }

    public ResultSet executeStoredProcedure(String procedure, int input, int input2) {
        ResultSet resultSet = null;
        try (CallableStatement cs = connection.prepareCall(procedure)) {
            cs.setInt(1, input);
            cs.setInt(2, input2);
            resultSet = cs.executeQuery();
        } catch (SQLException e) {
            System.out.println("Could not create callable statement - " + e.getMessage());
        }
        return resultSet;
    }
}
