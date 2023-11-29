package com.example.starfilms.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starfilms.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context context;

    // Constructor que recibe el contexto
    public ImageAdapter(Context context) {
        this.context = context;
    }

    // Método llamado cuando se necesita crear un nuevo ViewHolder
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de elemento de imagen y devolver un nuevo ViewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    // Método llamado para vincular datos a un ViewHolder en una posición específica
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        // Aquí configura las imágenes en función de la posición
        // Puedes cargar las imágenes desde tu recurso drawable o una URL externa

        if (position % 2 == 0) {
            // Si la posición es par, ajusta el relleno y establece una imagen específica
            holder.imageView.setPadding(20, 20, 20, 20);
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        } else {
            // Si la posición es impar, establece otra imagen
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        }
    }

    // Método que devuelve la cantidad total de elementos que debe manejar el adaptador
    @Override
    public int getItemCount() {
        // Devuelve un valor alto o infinito para lograr el desplazamiento infinito
        return Integer.MAX_VALUE;
    }

    // Clase interna que representa el ViewHolder para las imágenes
    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        // Constructor que toma una vista como parámetro
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            // Obtiene una referencia al ImageView en el diseño de elemento de imagen
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}