package com.strikkeapp.strikkeapp.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginData extends DataHandler {

    public List<String> getAllUsernames(String input) {
        ArrayList<String> usernames = new ArrayList<>();
        try (CallableStatement cs = makeCall("{CALL users_getAllUsernames(?)}")){
            cs.setString(1,input);
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                usernames.add(username);
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve all usernames from database - " + e.getMessage());
        }
        return usernames;
    }

    public String getHashedPassword(String usernameToFind) {
        String hashedPassword = null;
        try (CallableStatement cs = makeCall("{CALL users_getHashedPassword(?)}")){
            cs.setString(1, usernameToFind);
            ResultSet resultSet = cs.executeQuery();

            while (resultSet.next()) {
                hashedPassword = resultSet.getString("hashedPassword");
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve hashed password from database - " + e.getMessage());
        }
        return hashedPassword;
    }

    public String getUserIDByName(String username) {
        String userID = null;
        try (CallableStatement cs = makeCall("{CALL users_getUserIDByName(?)}")){
            cs.setString(1, username);
            ResultSet resultset = cs.executeQuery();

            while (resultset.next()) {
                userID = resultset.getString("userID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userID;
    }

}
