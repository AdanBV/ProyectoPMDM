package com.example.starfilms.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.util.Log;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.starfilms.R;
import com.example.starfilms.db.DataBaseHelper;

public class LoginActivity extends AppCompatActivity {

    // Referencias a los elementos de la interfaz de usuario
    Button button_LogIn, button_register;
    EditText editText_UserId, editText_Password;
    Switch sw;
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

        sw = findViewById(R.id.showPassword);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editText_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

                    // Aplicar la fuente al EditText
                    editText_Password.setTypeface(Typeface.DEFAULT);
                } else {
                    // Volver al tipo de entrada de contraseña oculta
                    editText_Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
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

        // Obtener el nombre de usuario y la contraseña de la interfaz de usuario
        String User = editText_UserId.getText().toString().trim();
        String Password = editText_Password.getText().toString().trim();

        // Comprobar si existe alguna entrada en la BD que coincida con los datos dados
        Cursor cursor = db.rawQuery("SELECT User_id, User_password FROM User WHERE User_Id = '"
                + User + "' AND User_password = '"
                + Password + "'", null);

        try {
            // Si se encontraron datos que coinciden
            if (cursor.moveToFirst()) {
                // Comprobar que la contraseña coincide con el usuario e iniciar sesión
                if (User.equals(cursor.getString(0)) && Password.equals(cursor.getString(1))) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    String Nombre = editText_UserId.getText().toString();
                    intent.putExtra("Nombre",Nombre);
                    startActivity(intent);
                }
            }
            // Si no se encontraron datos que coincidieran
            else {
                // Avisar de que los datos son incorrectos
                Toast toast = Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG);
                toast.show();
            }
        } catch (Exception e) {
            // Mostrar un mensaje de error en caso de excepción
            Toast toast = Toast.makeText(this, "Error al iniciar sesión. Inténtalo de nuevo. "
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