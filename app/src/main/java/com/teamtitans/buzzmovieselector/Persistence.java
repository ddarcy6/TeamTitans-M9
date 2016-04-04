package com.teamtitans.buzzmovieselector;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import java.io.Serializable;
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

/**
 * Created by Alex on 2/15/2016
 */
public class Persistence extends Activity{

    private SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);

    public void saveData(HashMap<String, User> instance){
        Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(instance);
        prefsEditor.putString("HashMap", json);
        prefsEditor.commit();
    }

    public HashMap<String, User> loadData(){
        Gson gson = new Gson();
        String json = mPrefs.getString("HashMap", "");
        return gson.fromJson(json, HashMap.class);
    }

}
