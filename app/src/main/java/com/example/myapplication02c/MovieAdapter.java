package com.example.myapplication02c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    ArrayList<Movie> moviesInAdapter;
    OnItemClickListener listener;

    // interface = kontrak: "aku akan memberitahu activity saat item diklik"
    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public MovieAdapter(ArrayList<Movie> movies, OnItemClickListener listener) {
        moviesInAdapter = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // baca XML row_movie.xml → jadi objek View di memori (inflate)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        // tempel data ke-'position' ke view yang dipegang holder
        Movie movie = moviesInAdapter.get(position);
        holder.viewPoster.setBackgroundColor(movie.getPosterColor());
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvMovieGenre.setText(movie.getGenre());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesInAdapter.size();
    }

    // inner class: pegang referensi view dalam 1 row (inti "recycle")
    public class MovieViewHolder extends RecyclerView.ViewHolder {

        View viewPoster;
        TextView tvMovieTitle, tvMovieGenre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPoster = itemView.findViewById(R.id.viewPoster);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvMovieGenre = itemView.findViewById(R.id.tvMovieGenre);
        }
    }
}
