package com.example.myapplication02c;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    Switch switchGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchGrid = findViewById(R.id.switchGrid);

        // isi posisi switch dari simpanan terakhir (default true = grid)
        SharedPreferences prefs = getSharedPreferences("cine_prefs", MODE_PRIVATE);
        switchGrid.setChecked(prefs.getBoolean("isGrid", true));

        switchGrid.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // setiap geser switch → langsung disimpan
            getSharedPreferences("cine_prefs", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isGrid", isChecked)
                    .apply();
        });
    }
}
