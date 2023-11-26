package com.example.proyectopocoyo.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.db.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {
    Button button_LogIn, button_register;
    EditText editText_UserId, editText_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_UserId = findViewById(R.id.editText_UserID);
        editText_Password = findViewById(R.id.editText_Password);

        button_register = findViewById(R.id.button_Register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva actividad
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        button_LogIn = findViewById(R.id.button_LogIn);
        button_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar Sesion
                IniciarSesion(v);
            }
        });
    }

    public void IniciarSesion(View v) {
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Usuario y Contraseña de la activity
        String User = editText_UserId.getText().toString();
        String Password = editText_Password.getText().toString();

        // Comprobamos si existe alguna entrada en la BD que concuerde con los datos dados
        Cursor cursor = db.rawQuery("SELECT User_id, User_password FROM User WHERE User_Id = '"
                + User + "' AND User_password = '"
                + Password + "'", null);

        try {
            // Si se pudo encontrar datos que concuerdan
            if (cursor.moveToFirst()) {
                // Comprobamos que se corresponda la contraseña con el usuario e iniciamos sesion
                if (User.equals(cursor.getString(0)) && Password.equals(cursor.getString(1))) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
            }
            // Si no se encontraron datos que concordasen
            else {
                // Avisamos de que los Datos son incorrectos
                Toast toast = Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Error al iniciar sesión. Intentalo de nuevo."
                    + e.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        } finally {
            // Cerrar el cursor para liberar recursos
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}