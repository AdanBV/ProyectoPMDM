package com.example.proyectopocoyo;

public class Pelicula {
    private int imageResource;  // ID de la imagen de la película
    private String titulo;       // Título de la película
    private float valoracion;       // Valoración de la película (0-5)

    public Pelicula(int imageResource, String titulo, float valoracion) {
        this.imageResource = imageResource;
        this.titulo = titulo;
        this.valoracion = valoracion;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitulo() {
        return titulo;
    }

    public float getValoracion() {
        return valoracion;
    }
}