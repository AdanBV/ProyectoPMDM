package com.example.starfilms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starfilms.R;
import com.example.starfilms.clases.AdapterReviwUser;
import com.example.starfilms.db.DataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    String User;
    DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establecer el diseño de la interfaz de usuario
        setContentView(R.layout.activity_profile);

        // Referencia al botón de cierre de sesión
        Button btn = findViewById(R.id.btnLogOut);
        Intent intent = getIntent();
        User = intent.getStringExtra("Nombre");

        TextView txt_user = findViewById(R.id.txtIdUsuario);
        txt_user.setText(User);

        ArrayList<String> Titles = BuscarTitulos(User);
        ArrayList<String> reviews = BuscarReviews(User);

        ListView listView = findViewById(R.id.listaReviewsUsuario);

        AdapterReviwUser adapter = new AdapterReviwUser(this, Titles, reviews);

        listView.setAdapter(adapter);

        // Configurar el clic del botón para cerrar la sesión
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad de inicio de sesión
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);

                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        // Referencia a la barra de navegación inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        // Establecer el ítem seleccionado en la barra de navegación inferior como "Perfil"
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // Agregar un escuchador de eventos para la barra de navegación inferior
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Obtener el título del ítem seleccionado
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":
                        // Crear un Intent para abrir la actividad de inicio
                        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                        intent.putExtra("Nombre",User);
                        // Iniciar la nueva actividad
                        startActivity(intent);
                        return true;

                    case "Favoritos":
                        Intent intent2 = new Intent(ProfileActivity.this, FavoritosActivity.class);
                        intent2.putExtra("Nombre",User);
                        // Iniciar la nueva actividad
                        startActivity(intent2);
                        return true;

                    case "Perfil":
                        // Lógica para la opción "Perfil"
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
    private ArrayList<String> BuscarReviews(String user) {

        ArrayList<String> review = new ArrayList<>();

        String rev;

        myDb = new DataBaseHelper(ProfileActivity.this);

        Cursor c = DataBaseHelper.obtenerReviews(myDb, user);

        if (c != null) {
            while (c.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                rev = c.getString(c.getColumnIndexOrThrow("Review_text"));

                // Agregar el usuario a la lista
                review.add(rev);
            }

            // Cerrar el cursor después de usarlo
            c.close();
        }
        return review;
    }

    private ArrayList<Integer>Buscarid(String user){

        myDb = new DataBaseHelper(ProfileActivity.this);

        ArrayList<Integer> movieId = new ArrayList<>();
        int id = 0;

        // Utiliza myDb.obtenerPelis en lugar de DataBaseHelper.obtenerPelis
        Cursor c = myDb.obtenerReviews(myDb, user);

        if (c != null) {
            while (c.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                id = c.getInt(c.getColumnIndexOrThrow("Movie_id"));

                // Agregar el usuario a la lista
                movieId.add(id);
            }

            // Cerrar el cursor después de usarlo
            c.close();
        }

        return movieId;
    }

    private ArrayList<String> BuscarTitulos(String user) {

        ArrayList<Integer> movieId = Buscarid(user);

        ArrayList<String> review = new ArrayList<>();

        String rev;

        myDb = new DataBaseHelper(ProfileActivity.this);

        Cursor c = myDb.obtenerReviewporID(myDb, movieId);

        if (c != null) {
            while (c.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                rev = c.getString(c.getColumnIndexOrThrow("Movie_title"));

                // Agregar el usuario a la lista
                review.add(rev);
            }

            // Cerrar el cursor después de usarlo
            c.close();
        }
        return review;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // No se ejecuta ninguna acción al presionar el botón de retroceso
    }

    private void showToast(String message) {
        // Método para mostrar mensajes de toast
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}