package com.strikkeapp.strikkeapp.data;

import com.strikkeapp.strikkeapp.dbo.User;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterData extends DataHandler {

    public ArrayList<String> getAllUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        try (CallableStatement cs = makeCall("{CALL users_getAllUsernames}")){
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

    public void userRegistration(User user) {
        try (CallableStatement cs = makeCall("{CALL users_addUser(?, ?, ?, ?, ?)}")) {
            cs.setString(1, user.getUsername());
            cs.setString(2, user.getPassword());
            cs.setString(3, user.getEmail());
            cs.setString(4, user.getFirstname());
            cs.setString(5, user.getLastname());

            cs.execute();

        } catch (SQLException e) {
            System.out.println("Could not insert into database - " + e.getMessage());
        }
    }
}
