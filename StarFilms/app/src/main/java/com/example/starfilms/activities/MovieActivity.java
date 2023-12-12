package com.example.starfilms.activities;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.starfilms.R;
import com.example.starfilms.db.DataBaseHelper;

public class MovieActivity extends AppCompatActivity {

    // Ayudante de Base De Datos
    DataBaseHelper myDb;

    // Variables para el manejo de favoritos
    String title, user;
    boolean favorito = false;

    // Elementos de la interfaz de usuario
    ImageButton imgBtn;
    ImageView imgPeli;
    TextView idTitulo, txtTitulo, txtDescription, txtDirector, txtValoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        // Referencias a elementos de la interfaz de usuario
        idTitulo = findViewById(R.id.idTitulo);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtDescription = findViewById(R.id.txtDescription);
        txtDirector = findViewById(R.id.txtDirector);
        txtValoracion = findViewById(R.id.txtValoracion);
        imgBtn = findViewById(R.id.imageButton);
        imgPeli = findViewById(R.id.imageView3);

        // Título de prueba (puede ser reemplazado por el título real obtenido del Intent)
        Intent intent4 = getIntent();
        title = intent4.getStringExtra("titulo");
        user = intent4.getStringExtra("Nombre");

        // Inicializar el ayudante de Base De Datos
        myDb = new DataBaseHelper(this);

        // Mostrar datos de la película en la interfaz de usuario
        mostrarDatos(title);

        // Configurar el botón de favoritos
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Alternar el estado de favorito
                if (!favorito) {
                    imgBtn.setImageResource(android.R.drawable.btn_star_big_on);
                    favorito = true;
                } else {
                    imgBtn.setImageResource(android.R.drawable.btn_star_big_off);
                    favorito = false;
                }
            }
        });

        // Configurar el botón para añadir una reseña
        Button añadirReseña = findViewById(R.id.añadirReseña);
        añadirReseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad de reseña
                Intent intent2 = new Intent(MovieActivity.this, ReviewActivity.class);
                // Obtener el título de la película y pasar a la siguiente actividad
                intent2.putExtra("titulo",title);
                intent2.putExtra("Nombre",user);
                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        });
    }

    // Método para mostrar los datos de la película en la interfaz de usuario
    private void mostrarDatos(String movie) {
        // Obtener información de la película desde la Base De Datos
        Cursor cursor = DataBaseHelper.obtenerPelis(myDb, movie);

        // Verificar si se obtuvieron datos y mover el cursor a la primera fila
        if (cursor != null && cursor.moveToFirst()) {
            String titulo, director, descripcion, img;
            double puntaje;

            // Obtener datos de las columnas correspondientes
            titulo = cursor.getString(cursor.getColumnIndexOrThrow("Movie_title"));
            director = cursor.getString(cursor.getColumnIndexOrThrow("Movie_director"));
            descripcion = cursor.getString(cursor.getColumnIndexOrThrow("Movie_description"));
            puntaje = cursor.getDouble(cursor.getColumnIndexOrThrow("Movie_rating"));
            img = cursor.getString(cursor.getColumnIndexOrThrow("Movie_image"));
            // Asignar los datos a los TextView en la interfaz de usuario
            idTitulo.setText(titulo);
            txtTitulo.setText(titulo);
            txtDescription.setText(descripcion);
            txtDirector.setText(director);
            txtValoracion.setText("Valoración Media: " + String.valueOf(puntaje));
            Glide.with(this).load(img).into(imgPeli);
        }

        // Cerrar el cursor después de usarlo
        if (cursor != null) {
            cursor.close();
        }
    }
}