package com.example.starfilms.activities;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.starfilms.R;
import com.example.starfilms.clases.AdapterReviewPeli;
import com.example.starfilms.db.DataBaseHelper;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    // Ayudante de Base De Datos
    DataBaseHelper myDb;

    // Variables para el manejo de favoritos
    String title, user;
    int movieid;
    boolean favorito = false;

    // Elementos de la interfaz de usuario
    ImageButton imgBtn;
    ImageView imgPeli;
    TextView idTitulo, txtTitulo, txtDescription, txtDirector, txtValoracion;
    ListView lv;

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
        lv = findViewById(R.id.lvReview);

        // Inicializar el ayudante de Base De Datos
        myDb = new DataBaseHelper(this);



        // Título de prueba (puede ser reemplazado por el título real obtenido del Intent)
        Intent intent4 = getIntent();
        title = intent4.getStringExtra("Titulo");
        user = intent4.getStringExtra("Nombre");

        // Mostrar datos de la película en la interfaz de usuario
        mostrarDatos(title);

        ComprobarFav(user);

        ArrayList<String> titulos = obtenerReviwParaTuLista(title);
        ArrayList<String> usuarios = obtenerUsuariosParaTuLista(title);

        AdapterReviewPeli adapter = new AdapterReviewPeli(this, usuarios, titulos);

        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        // Configurar el botón de favoritos
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Alternar el estado de favorito
                if (!favorito) {
                    imgBtn.setImageResource(android.R.drawable.btn_star_big_on);
                    favorito = true;
                    movieid = Buscarid(title);
                    myDb.addFav(user, movieid);
                } else {
                    imgBtn.setImageResource(android.R.drawable.btn_star_big_off);
                    favorito = false;
                    movieid = Buscarid(title);
                    myDb.deleteFavourite(user, movieid);
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
                intent2.putExtra("Titulo",title);
                intent2.putExtra("Nombre",user);
                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        });
    }

    private void ComprobarFav(String user){
        int id = BuscarPeli(user);

        String tit = BuscarTitulo(id);

        if (title != null && title.equals(tit)) {
            imgBtn.setImageResource(android.R.drawable.btn_star_big_on);
            favorito = true;
        } else {
            imgBtn.setImageResource(android.R.drawable.btn_star_big_off);
            favorito = false;
        }
    }

    private String BuscarTitulo(int id){
        String tit = null;

        // Utiliza myDb.obtenerPelis en lugar de DataBaseHelper.obtenerPelis
        Cursor cursor = myDb.obtenerPeliId(myDb, id);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                tit = cursor.getString(cursor.getColumnIndexOrThrow("Movie_title"));
            }
            // Cerrar el cursor después de usarlo
            cursor.close();
        }

        return tit;
    }

    private int BuscarPeli(String user){
        int movieId = 0;

        // Utiliza myDb.obtenerPelis en lugar de DataBaseHelper.obtenerPelis
        Cursor cursor = myDb.obtenerFav(myDb, user);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                movieId = cursor.getInt(cursor.getColumnIndexOrThrow("Movie_id"));
            }
            // Cerrar el cursor después de usarlo
            cursor.close();
        }

        return movieId;
    }

    private ArrayList<String> obtenerUsuariosParaTuLista(String movie) {
        ArrayList<String> miLista = new ArrayList<>();

        int movieId = Buscarid(movie);
        String usuario;

        Cursor c = DataBaseHelper.obtenerReview(myDb, movieId);

        if (c != null) {
            while (c.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                usuario = c.getString(c.getColumnIndexOrThrow("User_id"));

                // Agregar el usuario a la lista
                miLista.add(usuario);
            }

            // Cerrar el cursor después de usarlo
            c.close();
        }

        return miLista;
    }
    private ArrayList<String> obtenerReviwParaTuLista(String movie) {
        ArrayList<String> miLista = new ArrayList<>();

        int movieId = Buscarid(movie);
        String review;

        Cursor c = DataBaseHelper.obtenerReview(myDb, movieId);

        if (c != null) {
            while (c.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                review = c.getString(c.getColumnIndexOrThrow("Review_text"));

                // Agregar el usuario a la lista
                miLista.add(review);
            }

            // Cerrar el cursor después de usarlo
            c.close();
        }

        return miLista;
    }

    private int Buscarid(String movie){
        int movieId = 1;

        // Utiliza myDb.obtenerPelis en lugar de DataBaseHelper.obtenerPelis
        Cursor cursor = myDb.obtenerPelis(myDb, movie);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Obtener datos de las columnas correspondientes
                movieId = cursor.getInt(cursor.getColumnIndexOrThrow("Movie_id"));
            }
            // Cerrar el cursor después de usarlo
            cursor.close();
        }

        return movieId;
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent2 = new Intent(MovieActivity.this, HomeActivity.class);
        // Obtener el título de la película y pasar a la siguiente actividad
        intent2.putExtra("Nombre",user);
        // Iniciar la nueva actividad
        startActivity(intent2);
    }
}