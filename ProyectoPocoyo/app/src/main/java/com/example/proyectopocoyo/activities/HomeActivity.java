// PÁGINA PRINCIPAL DE LA APP (INICIO)

package com.example.proyectopocoyo.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.clases.MovieAdapter;
import com.example.proyectopocoyo.db.DataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    // Ayudante de Base De Datos
    DataBaseHelper dbHelper;

    // Datos de peliculas
    ArrayList<String> Movie_title, Movie_director, Movie_description, Movie_rating, Movie_image;

    // Adaptador para mostrar peliculas
    MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // VISTA XML QUE SE VA A MOSTRAR
        setContentView(R.layout.activity_home);

        final int[] posicion = new int[]{0, 1, 2, 3};

        // REFERENCIA A LA BARRA DE NAVEGACIÓN INFERIOR
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":
                        showToast("Clic en Inicio");
                        return true;

                    case "Favoritos":
                        showToast("Clic en Favoritos");
                        return true;

                    case "Añadir":
                        //Intent intent2 = new Intent(HomeActivity.this, ReviewActivity.class);
                        // Iniciar la nueva actividad
                        //startActivity(intent2);
                        return true;

                    case "Perfil":
                        Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                        // Iniciar la nueva actividad
                        startActivity(intent);
                        return true;

                    default:
                        return false;
                }
            }
        });

        // REFERENCIA AL RECYCLERVIEW
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // PRESENTACIÓN DE LOS ELEMENTOS DENTRO DEL RECYCLERVIEW
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // EVENTO CLICK SOBRE ELEMENTOS DE RECYCLERVIEW
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Intent intent2 = new Intent(HomeActivity.this, MovieActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent2);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Intent intent2 = new Intent(HomeActivity.this, MovieActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        }));

        // MOSTRAR PELICULAs
        dbHelper = new DataBaseHelper(HomeActivity.this);

        Movie_title = new ArrayList<>();
        Movie_director = new ArrayList<>();
        Movie_description = new ArrayList<>();
        Movie_rating = new ArrayList<>();
        Movie_image = new ArrayList<>();

        storeDBInfo();

        //
        movieAdapter = new MovieAdapter(this, Movie_image);
        recyclerView.setAdapter(movieAdapter);
    }
    private void storeDBInfo(){
        Cursor cursor = dbHelper.readDB();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                Movie_title.add(cursor.getString(0));
                Movie_director.add(cursor.getString(1));
                Movie_description.add(cursor.getString(2));
                Movie_rating.add(cursor.getString(3));
                Movie_image.add(cursor.getString(4));
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}