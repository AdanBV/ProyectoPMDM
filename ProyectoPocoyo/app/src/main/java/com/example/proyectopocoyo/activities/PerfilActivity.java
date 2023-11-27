package com.example.proyectopocoyo.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proyectopocoyo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        // HAY QUE AÑADIR UN LISTENER PARA LA BARRA DE NAVEGACÓN INFERIOR

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":
                        Intent intent = new Intent(PerfilActivity.this, HomeActivity.class);
                        // Iniciar la nueva actividad
                        startActivity(intent);
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

                        return true;

                    default:
                        return false;
                }
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}