package com.example.starfilms.clases;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starfilms.R;
import com.example.starfilms.activities.MovieActivity;
import com.example.starfilms.db.DataBaseHelper;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.PeliculaViewHolder> {
    private Context context;
    private final ArrayList<String> Movie_image, Movie_title;

    // Constructor que recibe el contexto y las listas de imágenes y títulos de películas
    public MovieAdapter(Context context, ArrayList<String> Movie_image, ArrayList<String> Movie_title) {
        this.context = context;
        this.Movie_image = Movie_image;
        this.Movie_title = Movie_title;
    }

    // Método llamado cuando se necesita crear un nuevo ViewHolder
    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de elemento de película y devolver un nuevo ViewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new PeliculaViewHolder(view);
    }

    // Método llamado para vincular datos a un ViewHolder en una posición específica
    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        // Obtener la URL de la imagen y el título de la película en la posición dada
        String url = Movie_image.get(position);
        String title = Movie_title.get(position);

        // Utilizar Glide para cargar la imagen desde la URL en el ImageView
        Glide.with(context).load(url).into(holder.imagenPelicula);

        // Establecer el título de la película en el TextView
        holder.titleTextView.setText(title);

        // Configurar un clic en el elemento de película
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (clickedPosition != RecyclerView.NO_POSITION) {
                    // Obtener el título de la película en la posición clicada
                    String movieTitle = Movie_title.get(clickedPosition);

                    // Iniciar la nueva Activity y pasar los detalles de la película
                    Intent intent = new Intent(context, MovieActivity.class);
                    intent.putExtra("movie", movieTitle);
                    context.startActivity(intent);
                }
            }
        });
    }

    // Método que devuelve la cantidad total de elementos que debe manejar el adaptador
    @Override
    public int getItemCount() {
        // Devolver la cantidad de elementos en la lista de imágenes (o 0 si es nula)
        return Movie_image != null ? Movie_image.size() : 0;
    }

    // Clase interna que representa el ViewHolder para los elementos de película
    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPelicula;
        TextView titleTextView;

        // Constructor que toma una vista como parámetro
        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            // Obtener referencias a los elementos de la interfaz de usuario en el diseño de elemento de película
            imagenPelicula = itemView.findViewById(R.id.imagenPelicula);
            titleTextView = itemView.findViewById(R.id.movieTitle);
        }
    }
}