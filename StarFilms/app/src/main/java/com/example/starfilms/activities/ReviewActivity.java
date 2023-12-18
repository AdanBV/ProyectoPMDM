package com.example.starfilms.activities;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starfilms.R;
import com.example.starfilms.db.DataBaseHelper;

public class ReviewActivity extends AppCompatActivity {

    // Elementos de la interfaz de usuario
    EditText textReview;
    Button btnReview;
    TextView txtPuntuacion, titleId;
    String title, user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecer el diseño de la interfaz de usuario
        setContentView(R.layout.activity_review);

        // Obtener el Intent que inició esta actividad
        Intent intent = getIntent();
        title = intent.getStringExtra("Titulo");
        user = intent.getStringExtra("Nombre");

        titleId = findViewById(R.id.idTitulo);
        titleId.setText(title);

        // Configurar el botón de volver
        ImageButton btnVolver = findViewById(R.id.imageButton);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para volver a la actividad Home
                Intent intent2 = new Intent(ReviewActivity.this, MovieActivity.class);
                intent2.putExtra("Titulo",title);
                intent2.putExtra("Nombre",intent.getStringExtra("Nombre"));
                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        });

        // Referencias a elementos de la interfaz de usuario para la funcionalidad de reseñas
        txtPuntuacion = findViewById(R.id.txtPuntuacion);
        textReview = findViewById(R.id.txtTexto);
        btnReview = findViewById(R.id.btnReseña);


        // Configurar el clic del botón para agregar una reseña
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ayudante de Base de Datos
                DataBaseHelper db = new DataBaseHelper(ReviewActivity.this);

                // Cambia la línea para obtener el Cursor usando la instancia del DBHelper
                Cursor cursor = db.obtenerPelis(db, title);

                if (cursor != null && cursor.moveToFirst()) {
                    int movieId = cursor.getInt(cursor.getColumnIndexOrThrow("Movie_id"));

                    if(textReview.getText().toString().trim().equals("") || (Integer.valueOf(txtPuntuacion.getText().toString().trim()) < 0 || Integer.valueOf(txtPuntuacion.getText().toString().trim()) > 5)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ReviewActivity.this);
                        builder.setMessage("Por favor, complete todos los campos de la reseña correctamente")
                                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Aquí puedes realizar acciones adicionales si es necesario
                                        dialog.dismiss(); // Cierra el cuadro de diálogo
                                    }
                                });

                        // Crear y mostrar el cuadro de diálogo
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        String text = textReview.getText().toString().trim();
                        int i = Integer.valueOf(txtPuntuacion.getText().toString().trim());
                        // Intentar agregar una nueva reseña a la base de datos
                        db.addReview(
                                text,
                                i,
                                user,
                                movieId);
                    }

                }

                // Cerrar el cursor después de usarlo
                if (cursor != null) {
                    cursor.close();
                }
            }
        });

        // Configurar el botón para mostrar el menú de puntuación
        Button btnMostrarMenu = findViewById(R.id.btnMostrarMenu);
        btnMostrarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMenu(v);
            }
        });
    }

    // Método para mostrar el menú de puntuación
    public void mostrarMenu(View view) {
        txtPuntuacion = findViewById(R.id.txtPuntuacion);
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.review0) {
                    // Acción al hacer clic en la opción 1
                    txtPuntuacion.setText("0");
                    return true;
                } else if (item.getItemId() == R.id.review1) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("1");
                    return true;
                }
                else if (item.getItemId() == R.id.review2) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("2");
                    return true;
                }
                else if (item.getItemId() == R.id.review3) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("3");
                    return true;
                }
                else if (item.getItemId() == R.id.review4) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("4");
                    return true;
                }
                else if (item.getItemId() == R.id.review5) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("5");
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}