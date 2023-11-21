package com.example.proyectopocoyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva actividad
                Intent intent = new Intent(MainActivity2.this,
                        MainActivity.class);

                // Puedes agregar información adicional al intent si es necesario
                // intent.putExtra("clave", "valor");

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });
    }
}