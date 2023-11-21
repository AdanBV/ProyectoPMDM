package com.example.proyectopocoyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Txt = findViewById(R.id.txtCrear);
        Txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva actividad
                Intent intent = new Intent(Login1.this,
                        MainActivity2.class);

                // Puedes agregar información adicional al intent si es necesario
                // intent.putExtra("clave", "valor");

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva actividad
                Intent intent = new Intent(Login1.this,
                        MainActivity.class);

                // Puedes agregar información adicional al intent si es necesario
                // intent.putExtra("clave", "valor");

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });
    }
}