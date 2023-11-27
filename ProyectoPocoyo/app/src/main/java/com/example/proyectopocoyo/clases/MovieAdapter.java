package com.example.proyectopocoyo.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectopocoyo.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PeliculaViewHolder> {
    private Context context;
    private final ArrayList<String> Movie_image, Movie_title;

    public MovieAdapter(Context context, ArrayList<String> Movie_image, ArrayList<String> Movie_title) {
        this.context = context;
        this.Movie_image = Movie_image;
        this.Movie_title = Movie_title;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        String url = Movie_image.get(position);
        //"http://www.impawards.com/1999/posters/star_wars_episode_one_the_phantom_menace_ver2.jpg";
        Glide.with(context).load(url).into(holder.imagenPelicula);
        holder.titleTextView.setText(Movie_title.get(position));
    }

    @Override
    public int getItemCount() {
        if (Movie_image != null){
            return Movie_image.size();
        }else{
            return 0;
        }
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPelicula;
        TextView titleTextView;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPelicula = itemView.findViewById(R.id.imagenPelicula);
            titleTextView = itemView.findViewById(R.id.movieTitle);
        }
    }
}