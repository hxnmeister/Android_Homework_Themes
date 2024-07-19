package com.ua.project.android_homework_themes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ua.project.android_homework_themes.databinding.ActivitySettingsBinding;
import com.ua.project.android_homework_themes.settings.Settings;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int themeId = Settings.getInstance().getThemeId();
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());

        setTheme(themeId);
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null) {
            actionBar.setTitle("Settings");
        }

        if(themeId == R.style.Theme_Light) {
            binding.lightThemeRadioButton.toggle();
        }
        else if(themeId == R.style.Theme_Dark) {
            binding.darkThemeRadioButton.toggle();
        }
        else if(themeId == R.style.Theme_Olive) {
            binding.oliveThemeRadioButton.toggle();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.applicationMenuItem) {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onThemeRadioButtonToggle(View view) {
        if(view instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) view;

            if(radioButton.getId() == R.id.lightThemeRadioButton) {
                Settings.getInstance().setThemeId(R.style.Theme_Light);
            }
            else if(radioButton.getId() == R.id.darkThemeRadioButton) {
                Settings.getInstance().setThemeId(R.style.Theme_Dark);
            }
            else if(radioButton.getId() == R.id.oliveThemeRadioButton) {
                Settings.getInstance().setThemeId(R.style.Theme_Olive);
            }

            Settings.getInstance().saveSettings(SettingsActivity.this);
            recreate();
        }
    }
}