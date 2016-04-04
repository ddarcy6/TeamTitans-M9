package com.teamtitans.buzzmovieselector;

import android.app.Application;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Base64InputStream;
import android.util.Base64OutputStream;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Alex on 2/15/2016
 */
public class UserBase extends Activity{
    private static UserBase ourInstance = getInstance();
    private HashMap<String, User> userbase = getMap();
    private List<User> userList = new ArrayList<User>();
    private String currentUserName;
    private String fileDirectory = getDirectory();
    //private SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

    private String getDirectory() {
        RegistrationActivity context = new RegistrationActivity();
        return context.getDirectory();
    }

    private HashMap<String, User> getMap() {
        HashMap<String, User> map = new HashMap<String, User>();
        loadData();
        if (userList != null) {
            for (User user : userList) {
                map.put(user.getName(), user);
            }
        }
        return map;
    }

    private List<User> getUserList() {
        List<User> list = new ArrayList<User>(userbase.values());
        return list;
    }

    public static UserBase getInstance(){
        if (ourInstance == null)
            ourInstance = new UserBase();
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

    public void saveData()  {
        //File file = getFilesDir(MODE_PRIVATE);
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileDirectory + "data.bin"))) {
                out.writeObject(getUserList());
            }
        } catch (IOException ex) {
            Logger.getLogger(UserBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData()  {
        try {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileDirectory + "data.bin"))) {
                List<User> pls = (List<User>) in.readObject();
                userList = pls;
            }
        } catch (IOException ex) {
            Logger.getLogger(UserBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public void saveData(HashMap<String, User> instance) {
        SharedPreferences.Editor ed = mPrefs.edit();
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutput;
        try {
            objectOutput = new ObjectOutputStream(arrayOutputStream);
            objectOutput.writeObject(instance);
            byte[] data = arrayOutputStream.toByteArray();
            objectOutput.close();
            arrayOutputStream.close();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Base64OutputStream b64 = new Base64OutputStream(out, Base64.DEFAULT);
            b64.write(data);
            b64.close();
            out.close();
            ed.putString("appUserBase.data", new String(out.toByteArray()));
            ed.apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object loadData(){
        byte[] bytes = mPrefs.getString("appUserBase.data", "{}").getBytes();
        if (bytes.length == 0) {
            return null;
        }
        try {
            ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
            Base64InputStream base64InputStream = new Base64InputStream(byteArray, Base64.DEFAULT);
            ObjectInputStream in;
            in = new ObjectInputStream(base64InputStream);
            return in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    /*public void saveData(HashMap<String, User> instance){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(instance);
        prefsEditor.putString("HashMap", json);
        prefsEditor.apply();
    }

    public HashMap<String, User> loadData(){
        Gson gson = new Gson();
        String json = mPrefs.getString("HashMap", "");
        return gson.fromJson(json, new TypeToken<HashMap<String, User>>(){}.getType());
    }*/

    /*public void saveData(HashMap<String, User> instance){
        try {
            FileOutputStream outFile = openFileOutput("appUserBase.data", Context.MODE_PRIVATE);
            ObjectOutput out = new ObjectOutputStream(outFile);
            out.writeObject(instance);
            out.close();
        } catch (Exception e) {e.printStackTrace();}
    }

    public HashMap<String, User> loadData(){
        ObjectInput in;
        HashMap<String, User> ub=null;
        try {
            in = new ObjectInputStream(new FileInputStream("appUserBase.data"));
            ub = (HashMap<String, User>) in.readObject();
            in.close();
        } catch (Exception e) {e.printStackTrace();}
        if (ub == null) {
            ub = new HashMap<>();
        }
        return ub;
    }*/

    /**
     * checks if user exists
     * @param username username string
     * @return true if exists, false otherwise
     */
    public boolean containsUsername(String username) {
        return userbase != null && userbase.containsKey(username);
    }

    /**
     * adds user to system
     * @param username string of username
     * @param password string of password
     */
    public void addUser(String username, String password) {
        userbase.put(username, new User(password));
        saveData();
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
        saveData();
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
        saveData();
    }

    /**
     * Set new major for user
     * @param username user's username as String
     * @param major MajorDegree Enum for new major
     */
    public void editMajor(String username, User.MajorDegree major) {
        this.getUser(username).setMajor(major);
        saveData();
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
