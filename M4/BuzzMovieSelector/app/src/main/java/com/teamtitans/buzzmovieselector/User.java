package com.teamtitans.buzzmovieselector;

public class User {

    public enum MajorDegree {
        CS, INTA, CHBE, ME, AE, CM, MSE, NRE, BA, ARCH, ID, BME, CE, COMPE, EE,
            ECON, EIA, BIO, PHYS, MATH, EAS, PSYCH, CHEM
    }

    private int year;
    private String name;
    private MajorDegree major;
    private String password;

    public User(String password) {
        this.password = password;
    }

    public void setMajor(MajorDegree input) {
        this.major = input;
    }

    public void setYear(int input) {
        this.year = input;
    }

    public void setName(String input) {
        this.name = input;
    }

    public MajorDegree getMajor() {
        return this.major;
    }

    public String getName() {
        return this.name;
    }

    public int getYear() {
        return this.year;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String input) {
        this.password = input;
    }

}