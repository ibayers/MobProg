package com.example.myapplication02c;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMovies;
    ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // terima kiriman dari LoginActivity (sesi 2)
        String username = getIntent().getStringExtra("keyUsername");
        if (username != null) {
            Toast.makeText(this, "Selamat datang, " + username, Toast.LENGTH_LONG).show();
        }

        // data film
        movies.add(new Movie("Inception", "Sci-Fi", Color.parseColor("#e50914")));
        movies.add(new Movie("Interstellar", "Sci-Fi", Color.parseColor("#1f4037")));
        movies.add(new Movie("The Dark Knight", "Action", Color.parseColor("#0f4c81")));
        movies.add(new Movie("Parasite", "Thriller", Color.parseColor("#6a040f")));
        movies.add(new Movie("Dune", "Sci-Fi", Color.parseColor("#b08968")));
        movies.add(new Movie("Whiplash", "Drama", Color.parseColor("#e09f3e")));
        movies.add(new Movie("Joker", "Drama", Color.parseColor("#4a4e69")));
        movies.add(new Movie("Upin & Ipin", "Animasi", Color.parseColor("#2a9d8f")));
        movies.add(new Movie("Laskar Pelangi", "Drama", Color.parseColor("#457b9d")));
        movies.add(new Movie("Ada Apa dengan Cinta", "Romance", Color.parseColor("#c9184a")));
        movies.add(new Movie("Pengabdi Setan", "Horror", Color.parseColor("#3c096c")));
        movies.add(new Movie("Marlina Si Pembunuh", "Thriller", Color.parseColor("#606c38")));

        rvMovies = findViewById(R.id.rvMovies);

        // LayoutManager sengaja TIDAK di-set di sini → pindah ke onResume (sesi 6)
        rvMovies.setAdapter(new MovieAdapter(movies, clickedMovie -> {
            Intent niatDetail = new Intent(MainActivity.this, DetailActivity.class);
            niatDetail.putExtra("keyTitle", clickedMovie.getTitle());
            niatDetail.putExtra("keyGenre", clickedMovie.getGenre());
            niatDetail.putExtra("keyColor", clickedMovie.getPosterColor());
            startActivity(niatDetail);
        }));
    }

    // ===== SESI 6: MENU =====

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; // true = menu tampil
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_help) {
            Toast.makeText(this, "CinePocket: app latihan UTS", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item); // item tak dikenal → serahkan ke induk
    }

    // ===== SESI 6 + SESI 3: baca setting saat kembali =====

    @Override
    protected void onResume() {
        super.onResume();
        // dipanggil SETIAP kali layar ini jadi aktif lagi —
        // termasuk setelah balik dari Settings (onCreate TIDAK jalan lagi saat itu)
        SharedPreferences prefs = getSharedPreferences("cine_prefs", MODE_PRIVATE);
        boolean isGrid = prefs.getBoolean("isGrid", true);
        rvMovies.setLayoutManager(isGrid
                ? new GridLayoutManager(this, 2)
                : new LinearLayoutManager(this));
    }
}
