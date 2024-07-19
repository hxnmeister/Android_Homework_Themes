package com.ua.project.android_homework_themes.settings;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.ua.project.android_homework_themes.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Settings {
    private static Settings settings;
    private String fileName = "settings";
    private int themeId = R.style.Theme_Light;

    private Settings() {

    }

    public static Settings getInstance() {
        if(settings == null) {
            settings = new Settings();
        }

        return settings;
    }

    public int getThemeId() {
        return this.themeId;
    }

    public void setThemeId(int value) {
        this.themeId = value;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String value) {
        this.fileName = value;
    }

    public void saveSettings(@NonNull Context context) {
        try(FileOutputStream fos = context.openFileOutput(this.fileName, Context.MODE_PRIVATE)) {
            String json = new Gson().toJson(getInstance());

            fos.write(json.getBytes());
        }
        catch (IOException e) {
            Log.e("TAG", "saveSettings: ", e);
        }
    }

    public void loadSettings(@NonNull Context context) {
        try(FileInputStream fis = context.openFileInput(this.fileName)) {
            Scanner scanner = new Scanner(fis);
            String json = scanner.nextLine();

            settings = new Gson().fromJson(json, Settings.class);
        }
        catch (IOException e) {
            Log.e("TAG", "loadSettings: ", e);
        }
    }
}
