package com.ua.project.android_homework_themes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ua.project.android_homework_themes.databinding.ActivityMainBinding;
import com.ua.project.android_homework_themes.settings.Settings;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        Settings.getInstance().loadSettings(MainActivity.this);
        setTheme(Settings.getInstance().getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setTitle("Application");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.lightThemeMenuItem) {
            applyThemeAndSave(Toast.makeText(MainActivity.this, "Light theme is applied!", Toast.LENGTH_SHORT), R.style.Theme_Light);
        }
        else if (itemId == R.id.darkThemeMenuItem) {
            applyThemeAndSave(Toast.makeText(MainActivity.this, "Dark theme is applied!", Toast.LENGTH_SHORT), R.style.Theme_Dark);
        }
        else if(itemId == R.id.oliveThemeMenuItem) {
            applyThemeAndSave(Toast.makeText(MainActivity.this, "Olive theme is applied!", Toast.LENGTH_SHORT), R.style.Theme_Olive);
        }
        else if(itemId == R.id.settingsMenuItem) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);

            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void applyThemeAndSave(@NonNull Toast toast, @NonNull Integer themeId) {
        Settings.getInstance().setThemeId(themeId);
        recreate();
        toast.show();

        Settings.getInstance().saveSettings(MainActivity.this);
    }
}