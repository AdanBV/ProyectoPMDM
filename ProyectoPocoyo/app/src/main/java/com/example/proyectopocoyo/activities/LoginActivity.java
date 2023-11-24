package com.example.proyectopocoyo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyectopocoyo.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button_register = findViewById(R.id.button_Register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva actividad
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);

                // Puedes agregar información adicional al intent si es necesario
                // intent.putExtra("clave", "valor");

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        Button button_LogIn = findViewById(R.id.button_LogIn);
        button_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Añadir campo a Tabla Usuario de la BD


                // Crear un Intent para abrir la nueva actividad
                Intent intent2 = new Intent(LoginActivity.this,
                        HomeActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        });
    }
}