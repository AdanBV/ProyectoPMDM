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
import com.example.proyectopocoyo.db.StarFilmsDataBaseHelper;

public class LoginActivity extends AppCompatActivity {
    Button button_LogIn, button_register;
    EditText editText_UserId, editText_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StarFilmsDataBaseHelper db = new StarFilmsDataBaseHelper(LoginActivity.this);
        db.addPeli(0,"La amenaza fantasma","George Lucas",
                "La trama describe la historia del maestro jedi Qui-Gon Jinn y de su aprendiz Obi-Wan Kenobi, que escoltan y protegen a la Reina Amidala con la esperanza de encontrar una salida pacífica.",
                2.0);
        db.addPeli(1,"El ataque de los clones","George Lucas",
                "La trama se desarrolla diez años después de los sucesos ocurridos en la película anterior, La amenaza fantasma. La galaxia se encuentra al borde de una guerra civil.",
                1.7);
        db.addPeli(2,"La venganza de los Sith","George Lucas",
                "La trama describe una época en la que los Caballeros Jedi se han esparcido por toda la galaxia, dirigiendo un ejército clon masivo para enfrentar a los Separatistas Galácticos.",
                4.9);
        db.addPeli(3,"Una nueva esperanza","George Lucas",
                "La trama describe la historia de un grupo de guerrilleros, la Alianza Rebelde, cuyo objetivo es destruir la estación espacial Estrella de la Muerte.",
                3.8);
        db.addPeli(4,"El imperio contrataca","George Lucas",
                "La ficción de la película se sitúa tres años después de la destrucción de la estación espacial de combate conocida como la Estrella de la Muerte.",
                4.5);
        db.addPeli(5,"El retorno del Jedi","George Lucas",
                "Luke no sabe que el Imperio Galáctico ha iniciado secretamente la construcción de una nueva estación espacial blindada, incluso más poderosa que la primera y temida Estrella de la Muerte.",
                4.4);
        db.addPeli(6,"El despertar de la fuerza","J. J. Abrams",
                "Cuando el desertor Finn llega a un planeta desierto conoce a Rey, cuyo androide contiene un mapa secreto.",
                2.9);
        db.addPeli(7,"Los ultimos Jedi","Rian Johnson",
                "La Resistencia encabezada por la general Leia Organa (Carrie Fisher) ha logrado contener temporalmente a la siniestra Primera Orden, un nuevo grupo militar nacido de las cenizas del Imperio Galáctico",
                1.5);
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
        StarFilmsDataBaseHelper dbHelper = new StarFilmsDataBaseHelper(this);
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