package edu.ib;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PreferencesRegBank {

    public static final String CONFIG_FILE = "configR.txt";

    private String username;
    private String password;

    public PreferencesRegBank() {
        this.username = "regbank";
        setPassword("bank1234");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha1Hex(password);
    }

    public static void initConfig() {
        FileWriter fileWriter = null;
        try {
            PreferencesRegBank preferencesRegBank = new PreferencesRegBank();
            Gson gson = new Gson();
            fileWriter = new FileWriter(CONFIG_FILE);
            gson.toJson(preferencesRegBank, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static PreferencesRegBank getPreferences() {
        Gson gson = new Gson();
        PreferencesRegBank preferencesRegBank = new PreferencesRegBank();
        try {
            preferencesRegBank = gson.fromJson(new FileReader(CONFIG_FILE), PreferencesRegBank.class);
        } catch (FileNotFoundException e) {
            initConfig();
            e.printStackTrace();
        }
        return preferencesRegBank;
    }
}

