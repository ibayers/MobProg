package com.example.myapplication02c;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();

            // validasi input: jangan biarkan lewat kalau kosong
            if (username.isEmpty()) {
                Toast.makeText(this, "Username masih kosong", Toast.LENGTH_SHORT).show();
                return; // hentikan alur, jangan pindah activity
            }

            // EXPLICIT INTENT: tujuan disebut eksplisit → MainActivity.class
            Intent niatPindah = new Intent(LoginActivity.this, MainActivity.class);
            niatPindah.putExtra("keyUsername", username);
            startActivity(niatPindah);
        });
    }
}
