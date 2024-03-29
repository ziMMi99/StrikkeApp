package com.strikkeapp.strikkeapp.dbo;

public class User {

    private String userID;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;

    /**
 * Creates a new user with the given details.
 *
 * @param username the username of the user
 * @param password the password of the user
 * @param email the email of the user
 * @param firstname the firstname of the user
 * @param lastname the lastname of the user
 */
public User(String username, String password, String email, String firstname, String lastname) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
}

    /**
     * Constructor for User class. Used when making a user for checking which user is logged in.
     * @param userID is the ID of the user
     * @param username is the username of the user
     */
    public User(String userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


    //Getters
    public String getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
