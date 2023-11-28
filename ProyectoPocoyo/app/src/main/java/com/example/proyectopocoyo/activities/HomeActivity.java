// PÁGINA PRINCIPAL DE LA APP (INICIO)

package com.example.proyectopocoyo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

        // REFERENCIA A LA BARRA DE NAVEGACIÓN INFERIOR
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":

                        return true;

                    case "Favoritos":

                        return true;

                    case "Añadir":

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
        movieAdapter = new MovieAdapter(this, Movie_image, Movie_title);
        recyclerView.setAdapter(movieAdapter);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Si el usuario hace clic en "Sí", cierra la sesión y vuelve a la pantalla de inicio de sesión
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Cierra la actividad actual
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Si el usuario hace clic en "No", cierra el cuadro de diálogo
                        dialog.dismiss();
                    }
                });

        // Crea y muestra el cuadro de diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void storeDBInfo(){
        Cursor cursor = dbHelper.readDB();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()){
                Movie_title.add(cursor.getString(1));
                Movie_director.add(cursor.getString(2));
                Movie_description.add(cursor.getString(3));
                Movie_rating.add(cursor.getString(4));
                Movie_image.add(cursor.getString(5));
            }
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}