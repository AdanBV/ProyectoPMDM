package com.example.proyectopocoyo.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proyectopocoyo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecer el diseño de la interfaz de usuario
        setContentView(R.layout.activity_profile);

        // Referencia al botón de cierre de sesión
        Button btn = findViewById(R.id.btnLogOut);

        // Configurar el clic del botón para cerrar la sesión
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad de inicio de sesión
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        // Referencia a la barra de navegación inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        // Establecer el ítem seleccionado en la barra de navegación inferior como "Perfil"
        bottomNavigationView.setSelectedItemId(R.id.perfil);

        // Agregar un escuchador de eventos para la barra de navegación inferior
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Obtener el título del ítem seleccionado
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":
                        // Crear un Intent para abrir la actividad de inicio
                        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        // Iniciar la nueva actividad
                        startActivity(intent);
                        return true;

                    case "Favoritos":
                        // Lógica para la opción "Favoritos"
                        return true;

                    case "Añadir":
                        // Lógica para la opción "Añadir"
                        return true;

                    case "Perfil":
                        // Lógica para la opción "Perfil"
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // No se ejecuta ninguna acción al presionar el botón de retroceso
    }

    private void showToast(String message) {
        // Método para mostrar mensajes de tostada
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
