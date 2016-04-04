package com.teamtitans.buzzmovieselector;

/**
 * Created by Alex on 2/15/2016
 */
public class User {

    public enum MajorDegree {
        CS, EE, ME, ISYE, MATH, PHYS, CHEM, CHEME
    }

    private String name;
    private int year;
    private String password;
    private String majorStr;
    private MajorDegree major;

    /**
     * returns user password
     * @param password string of user's password
     */
    public User(String password) {
        this.password = password;
    }

    /**
     * set user's (real) name
     * @param input string for name
     */
    public void setName(String input) {
        this.name = input;
    }

    /**
     * set major
     * @param input string for major
     */
    public void setMajorStr(String input) {
        majorStr = input;
    }

    /**
     * returns major
     * @return string for major
     */
    public String getMajorStr() {
        return majorStr;
    }

    /**
     * returns user's (real) name
     * @return string with user's name
     */
    public String getName() {
        return name;
    }

    /**
     * set graduation year
     * @param input int for user graduation year
     */
    public void setYear(int input) {
        this.year = input;
    }

    /**
     * return graduation year
     * @return int with user graduation year
     */
    public int getYear() {
        return year;
    }

    /**
     * set user password
     * @param input string for user password
     */
    public void setPassword(String input) {
        this.password = input;
    }

    /**
     * return user password
     * @return string with user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set user major
     * @param input Enum of major
     */
    public void setMajor(MajorDegree input) {
        this.major = input;
    }

    /**
     * return major
     * @return Enum of user major
     */
    public MajorDegree getMajor() {
        return major;
    }

}
