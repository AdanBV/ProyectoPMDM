package com.example.proyectopocoyo.clases;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.activities.MovieActivity;
import com.example.proyectopocoyo.db.DataBaseHelper;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PeliculaViewHolder> {
    private Context context;
    DataBaseHelper dbHelper = new DataBaseHelper(context);
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
        Glide.with(context).load(url).into(holder.imagenPelicula);
        holder.titleTextView.setText(Movie_title.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    String movieTitle = Movie_title.get(clickedPosition);

                    // Iniciar la nueva Activity y pasar los detalles de la película
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movie", movieTitle);
                    context.startActivity(intent);
                }
            }
        });
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