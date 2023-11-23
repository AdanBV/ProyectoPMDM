package com.example.proyectopocoyo.clases;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectopocoyo.R;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private List<Pelicula> listaPeliculas;
    private Context context;

    public PeliculaAdapter(List<Pelicula> listaPeliculas, Context context) {
        this.listaPeliculas = listaPeliculas;
        this.context = context;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = listaPeliculas.get(position);
        holder.imagenPelicula.setImageResource(pelicula.getImageResource());
        //holder.tituloPelicula.setText(pelicula.getTitulo());
        //holder.valoracionPelicula.setText(String.valueOf(pelicula.getValoracion()));
    }

    @Override
    public int getItemCount() {
        return listaPeliculas.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenPelicula;
        //TextView tituloPelicula;
        //TextView valoracionPelicula;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagenPelicula = itemView.findViewById(R.id.imagenPelicula);
            //tituloPelicula = itemView.findViewById(R.id.tituloPelicula);
            //valoracionPelicula = itemView.findViewById(R.id.valoracionPelicula);
        }
    }
}