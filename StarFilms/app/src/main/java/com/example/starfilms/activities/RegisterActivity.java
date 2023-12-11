package com.example.starfilms.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starfilms.R;
import com.example.starfilms.db.DataBaseHelper;

public class RegisterActivity extends AppCompatActivity {

    // Elementos de la interfaz de usuario
    EditText editText_UserID, editText_Password, editText_RepeatPassword;
    Button button_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecer el diseño de la interfaz de usuario
        setContentView(R.layout.activity_register);

        // Obtener referencias a los elementos de la interfaz de usuario
        editText_UserID = findViewById(R.id.editText_UserID);
        editText_Password = findViewById(R.id.editText_Password);
        editText_RepeatPassword = findViewById(R.id.editText_RepeatPassword);
        button_Register = findViewById(R.id.button_Register);

        // Configurar el clic del botón para registrar un nuevo usuario
        button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ayudante de Base de Datos
                DataBaseHelper db = new DataBaseHelper(RegisterActivity.this);

                // Verificar si las contraseñas coinciden
                if (editText_Password.getText().toString().trim().equals(editText_RepeatPassword.getText().toString().trim())) {

                    // Intentar agregar un nuevo usuario a la base de datos
                    if (db.addUser(
                            editText_UserID.getText().toString().trim(),
                            null, // Puedes agregar otros campos si es necesario
                            null,
                            editText_Password.getText().toString().trim())) {

                        // Si se pudo crear el usuario, iniciar la actividad principal
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        String Nombre = editText_UserID.getText().toString();
                        intent.putExtra("Nombre",Nombre);
                        startActivity(intent);

                        // En caso de que no se pueda crear el usuario, no hacer nada en este bloque
                    } else {
                        // Puedes agregar lógica aquí si es necesario
                    }

                } else {
                    // Mostrar un mensaje de error si las contraseñas no coinciden
                    editText_RepeatPassword.setError("Contraseñas diferentes");
                    Toast.makeText(RegisterActivity.this,
                            "Failed to create user, passwords don't match",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}