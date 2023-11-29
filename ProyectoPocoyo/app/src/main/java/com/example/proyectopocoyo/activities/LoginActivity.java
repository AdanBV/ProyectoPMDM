package com.example.proyectopocoyo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.db.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {
    // Referencias a los elementos de la interfaz de usuario
    Button button_LogIn, button_register;
    EditText editText_UserId, editText_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Establecer el diseño de la interfaz de usuario
        setContentView(R.layout.activity_login);

        // Obtener referencias a los elementos de la interfaz de usuario
        editText_UserId = findViewById(R.id.editText_UserID);
        editText_Password = findViewById(R.id.editText_Password);

        // Configurar el botón de registro para abrir la actividad de registro
        button_register = findViewById(R.id.button_Register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la nueva actividad de registro
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        // Configurar el botón de inicio de sesión para llamar al método IniciarSesion
        button_LogIn = findViewById(R.id.button_LogIn);
        button_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar Sesión
                IniciarSesion(v);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Alerta al presionar el botón de retroceso
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que quieres cerrar la aplicación?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Si el usuario hace clic en "Sí", cierra la aplicación
                        finishAffinity();
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

    public void IniciarSesion(View v) {

        // Ayudante de Base de Datos
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        // Base de Datos para lectura
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
            // Mostrar un mensaje de error en caso de excepción
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