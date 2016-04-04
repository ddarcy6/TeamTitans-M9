package com.teamtitans.buzzmovieselector;

import java.util.HashMap;

/**
 * Created by Alex on 2/15/2016
 */
public class UserBase {
    private static UserBase ourInstance = new UserBase();
    private HashMap<String, User> userbase = new HashMap<>();
    private String currentUserName;

    public static UserBase getInstance() {
        return ourInstance;
    }

    /**
     * Constructor for Userbase
     */
    private UserBase() {
        userbase.put("reed", new User("pass"));
        User reed = userbase.get("reed");
        reed.setMajor(User.MajorDegree.CS);

        userbase.put("deer", new User("pass"));
        User deer = userbase.get("deer");
        deer.setMajor(User.MajorDegree.CHEM);
    }

    /**
     * checks if user exists
     * @param username username string
     * @return true if exists, false otherwise
     */
    public boolean containsUsername(String username) {
        return userbase.containsKey(username);
    }

    /**
     * adds user to system
     * @param username string of username
     * @param password string of password
     */
    public void addUser(String username, String password) {
        userbase.put(username, new User(password));
    }

    /**
     * change user's username
     * @param username current username as String
     * @param input new username as String
     */
    public void editName(String username, String input) {
        User theUser = userbase.get(username);
        theUser.setName(input);
        currentUserName = input;
    }

    /**
     * return user object
     * @param username string for user's username
     * @return User object
     */
    public User getUser(String username) {
        return userbase.get(username);
    }

    /**
     * Set a new graduation year for user
     * @param username user's username as String
     * @param input int for new graduation year
     */
    public void editYear(String username, int input) {
        this.getUser(username).setYear(input);
    }

    /**
     * Set new major for user
     * @param username user's username as String
     * @param major MajorDegree Enum for new major
     */
    public void editMajor(String username, User.MajorDegree major) {
        this.getUser(username).setMajor(major);
    }

    /**
     * Set new username for user
     * @param userName user's new username as String
     */
    public void setCurrentUserName(String userName) {
        this.currentUserName = userName;
    }

    /**
     * get current user's username
     * @return user's username as String
     */
    public String getCurrentUserName() {
        return currentUserName;
    }
}
