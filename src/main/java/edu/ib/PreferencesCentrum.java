package edu.ib;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PreferencesCentrum {

    public static final String CONFIG_FILE = "configC.txt";

    private String username;
    private String password;

    public PreferencesCentrum() {
        this.username = "centrumkrwiodawstwa";
        setPassword("krew1234");
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
            PreferencesCentrum preferencesCentrum = new PreferencesCentrum();
            Gson gson = new Gson();
            fileWriter = new FileWriter(CONFIG_FILE);
            gson.toJson(preferencesCentrum, fileWriter);
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

    public static PreferencesCentrum getPreferences() {
        Gson gson = new Gson();
        PreferencesCentrum preferencesCentrum = new PreferencesCentrum();
        try {
            preferencesCentrum = gson.fromJson(new FileReader(CONFIG_FILE), PreferencesCentrum.class);
        } catch (FileNotFoundException e) {
            initConfig();
            e.printStackTrace();
        }
        return preferencesCentrum;
    }
}
