package com.example.proyectopocoyo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.proyectopocoyo.R;
import com.example.proyectopocoyo.db.StarFilmsDataBaseHelper;

public class ReviewActivity extends AppCompatActivity {

    EditText reviewId,textReview,ratingReview,userId,movieId;
    Button btnReview;
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
        ratingReview = findViewById(R.id.txtRating);
        userId = findViewById(R.id.txtIdUser);
        movieId = findViewById(R.id.txtIdMovie);
        btnReview = findViewById(R.id.btnReseña);
        btnReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                StarFilmsDataBaseHelper db = new StarFilmsDataBaseHelper(ReviewActivity.this);
                db.addReview(Integer.valueOf(reviewId.getText().toString().trim()),
                        textReview.getText().toString().trim(),
                        Float.valueOf(ratingReview.getText().toString().trim()),
                        Integer.valueOf(userId.getText().toString().trim()),
                        Integer.valueOf(movieId.getText().toString().trim()));
            }
        });
    }
}