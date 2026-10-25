package com.example.myapplication02c;

public class Movie {

    String title;
    String genre;
    int posterColor; // warna blok "poster" (pengganti gambar)

    public Movie(String title, String genre, int posterColor) {
        this.title = title;
        this.genre = genre;
        this.posterColor = posterColor;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPosterColor() {
        return posterColor;
    }
}
