package com.example.proyectopocoyo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.db.DataBaseHelper;

public class ReviewActivity extends AppCompatActivity {

    EditText reviewId,textReview,ratingReview,userId,movieId;
    Button btnReview;

    TextView txtPuntuacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ImageButton btnVolver = findViewById(R.id.imageButton);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(ReviewActivity.this,
                        HomeActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent2);
            }
        });

        reviewId = findViewById(R.id.txtIdReview);
        textReview = findViewById(R.id.txtTexto);
        userId = findViewById(R.id.txtIdUser);
        movieId = findViewById(R.id.txtIdMovie);
        btnReview = findViewById(R.id.btnReseña);
        btnReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DataBaseHelper db = new DataBaseHelper(ReviewActivity.this);
                db.addReview(Integer.valueOf(reviewId.getText().toString().trim()),
                        textReview.getText().toString().trim(),
                        Float.valueOf(ratingReview.getText().toString().trim()),
                        Integer.valueOf(userId.getText().toString().trim()),
                        Integer.valueOf(movieId.getText().toString().trim()));
            }
        });

        Button btnMostrarMenu = findViewById(R.id.btnMostrarMenu);

        btnMostrarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarMenu(v);
            }
        });


    }
    public void mostrarMenu(View view) {
        txtPuntuacion=findViewById(R.id.txtPuntuacion);
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.review0) {
                    // Acción al hacer clic en la opción 1
                    txtPuntuacion.setText("0/5");
                    return true;
                } else if (item.getItemId() == R.id.review1) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("1/5");
                    return true;
                }
                else if (item.getItemId() == R.id.review2) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("2/5");
                    return true;
                }
                else if (item.getItemId() == R.id.review3) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("3/5");
                    return true;
                }
                else if (item.getItemId() == R.id.review4) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("4/5");
                    return true;
                }
                else if (item.getItemId() == R.id.review5) {
                    // Acción al hacer clic en la opción 2
                    txtPuntuacion.setText("5/5");
                    return true;
                }
                return false;
            }
        });

        popupMenu.show();
    }
}