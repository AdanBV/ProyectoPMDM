package com.example.proyectopocoyo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.db.StarFilmsDataBaseHelper;

public class MovieActivity extends AppCompatActivity {
    StarFilmsDataBaseHelper myDb;
    int numeroPeli;
    TextView idTitulo=findViewById(R.id.idTitulo);
    TextView txtTitulo=findViewById(R.id.txtTitulo);
    TextView txtDescription=findViewById(R.id.txtDescription);
    TextView txtDirector=findViewById(R.id.txtDirector);
    TextView txtValoracion=findViewById(R.id.txtValoracion);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        myDb = new StarFilmsDataBaseHelper(this);
        mostrarDatos();
        ImageButton imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(MovieActivity.this,
                        HomeActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        });

        Button añadirReseña=findViewById(R.id.añadirReseña);
        añadirReseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MovieActivity.this,
                        ReviewActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        });


    }
    private void mostrarDatos() {
        Cursor cursor = myDb.obtenerPelis(numeroPeli);

        if (cursor != null && cursor.moveToFirst()) {
            String titulo;
            String director;
            String descripcion;
            double puntaje;
            try {
                titulo = cursor.getString(cursor.getColumnIndexOrThrow("Movie_title"));
                director = cursor.getString(cursor.getColumnIndexOrThrow("Movie_director")); // Reemplaza 'director' con el nombre de la columna correspondiente
                descripcion = cursor.getString(cursor.getColumnIndexOrThrow("Movie_description")); // Reemplaza 'descripcion' con el nombre de la columna correspondiente
                puntaje = cursor.getDouble(cursor.getColumnIndexOrThrow("Movie_rating")); // Reemplaza 'puntaje' con el nombre de la columna correspondiente
            } catch (IllegalArgumentException e) {
                // Manejo de la excepción aquí
                e.printStackTrace();
                titulo = "Columna no encontrada";
                descripcion = "Columna no encontrada";
                director = "Columna no encontrada";
                puntaje = -1;
            }
            // Asignar los datos a los TextView
            idTitulo.setText(titulo);
            txtTitulo.setText(titulo);
            txtDescription.setText(descripcion);
            txtDirector.setText(director);
            txtValoracion.setText(String.valueOf(puntaje));
        }

        // Cierra el cursor después de usarlo
        if (cursor != null) {
            cursor.close();
        }
    }
}


