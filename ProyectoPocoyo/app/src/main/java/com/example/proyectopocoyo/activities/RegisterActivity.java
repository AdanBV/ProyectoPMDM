package com.example.proyectopocoyo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.db.StarFilmsDataBaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText editText_UserID, editText_Password, editText_RepeatPassword;
    Button  button_Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_UserID = findViewById(R.id.editText_UserID);
        editText_Password = findViewById(R.id.editText_Password);
        editText_RepeatPassword = findViewById(R.id.editText_RepeatPassword);
        button_Register = findViewById(R.id.button_Register);
        button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agregar usuario a BD
                StarFilmsDataBaseHelper db = new StarFilmsDataBaseHelper(RegisterActivity.this);
                if(editText_Password.getText().toString().trim().equals(editText_RepeatPassword.getText().toString().trim())){
                    db.addUser(editText_UserID.getText().toString().trim(),
                            null,
                            null,
                            editText_Password.getText().toString().trim());

                    // Crear un Intent para abrir la nueva actividad
                    Intent intent = new Intent(RegisterActivity.this,
                            HomeActivity.class);

                    // Iniciar la nueva actividad
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Failed to create user, passwords don't match", Toast.LENGTH_LONG).show();
                }



            }
        });
    }
}