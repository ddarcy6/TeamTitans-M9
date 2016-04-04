package com.teamtitans.buzzmovieselector;

import java.util.ArrayList;
import java.util.HashMap;

public class UserBase {

    private static UserBase userbase = new UserBase();

    private UserBase(){}

    public static UserBase getInstance() {
        return userbase;
    }

    HashMap<String, User> database = new HashMap<>();

    private String currentUserName;

    public boolean containsUsername(String username) {
        return database.containsKey(username);
    }

    public void addUser(String username, String password) {
        database.put(username, new User(password));
    }

    public void editName(String username, String input) {
        this.getUser(username).setName(input);
    }

    public User getUser(String username) {
        return database.get(username);
    }

    public void editYear(String username, int input) {
        this.getUser(username).setYear(input);
    }

    public void editMajor(String username, User.MajorDegree major) {
        this.getUser(username).setMajor(major);
    }

    public void setCurrentUserName(String userName) {
        this.currentUserName = userName;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }
}