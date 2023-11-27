package com.example.proyectopocoyo.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectopocoyo.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PeliculaViewHolder> {
    private Context context;
    private final ArrayList<String> Movie_image;

    public MovieAdapter(Context context, ArrayList<String> Movie_image) {
        this.context = context;
        this.Movie_image = Movie_image;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Movie_image.get(position);

        Glide.with(context).load(Movie_image.get(position)).into(holder.imagenPelicula);
    }

    @Override
    public int getItemCount() {
        return Movie_image.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPelicula;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPelicula = itemView.findViewById(R.id.imagenPelicula);
        }
    }
}