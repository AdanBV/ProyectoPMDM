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

    EditText editText_UserID, editText_Password, editText_RepeatPassword;
    Button button_Register;

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
                DataBaseHelper db = new DataBaseHelper(RegisterActivity.this);

                // Si las contraseñas coinciden
                if (editText_Password.getText().toString().trim().equals
                        (editText_RepeatPassword.getText().toString().trim())) {

                    // Si se puede crear usuario
                    if (db.addUser(editText_UserID.getText().toString().trim(),
                            null,
                            null,
                            editText_Password.getText().toString().trim())) {

                        // Si consigue crearlo iniciamos la actividad principal
                        Intent intent = new Intent(RegisterActivity.this,
                                HomeActivity.class);
                        startActivity(intent);

                        // Si no puede crearlo no hacemos nada.
                    } else {}

                    // Si no coinciden entonces se avisa
                } else {
                    editText_RepeatPassword.setError("Contraseñas diferentes");
                    Toast.makeText(RegisterActivity.this,
                            "Failed to create user, passwords don't match",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}