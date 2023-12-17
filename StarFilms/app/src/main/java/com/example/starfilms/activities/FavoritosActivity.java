package com.example.starfilms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.starfilms.R;
import com.example.starfilms.db.DataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FavoritosActivity extends AppCompatActivity {

    String User;

    DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Intent intent = getIntent();
        User = intent.getStringExtra("Nombre");

        // Referencia a la barra de navegación inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        // Establecer el ítem seleccionado en la barra de navegación inferior como "Perfil"
        bottomNavigationView.setSelectedItemId(R.id.favourites);

        // Agregar un escuchador de eventos para la barra de navegación inferior
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Obtener el título del ítem seleccionado
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":
                        // Crear un Intent para abrir la actividad de inicio
                        Intent intent = new Intent(FavoritosActivity.this, HomeActivity.class);
                        intent.putExtra("Nombre",User);
                        // Iniciar la nueva actividad
                        startActivity(intent);
                        return true;

                    case "Favoritos":

                        return true;

                    case "Perfil":
                        Intent intent2 = new Intent(FavoritosActivity.this, ProfileActivity.class);
                        // Iniciar la nueva actividad
                        startActivity(intent2);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}