package com.example.starfilms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.starfilms.R;
import com.example.starfilms.clases.AdapterFavoritos;
import com.example.starfilms.db.DataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    String User;
    ListView lv;
    DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Intent intent = getIntent();
        User = intent.getStringExtra("Nombre");

        ArrayList<String> img = Buscarimg(User);
        ArrayList<String> titulos = Buscartitulo(User);

        AdapterFavoritos adapter = new AdapterFavoritos(this, titulos,img);

        lv = findViewById(R.id.listaFavoritas);

        lv.setAdapter(adapter);

        // Referencia a la barra de navegación inferior
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        // Establecer el ítem seleccionado en la barra de navegación inferior como "Perfil"
        bottomNavigationView.setSelectedItemId(R.id.favourites);

        // Agregar un escuchador de eventos para la barra de navegación inferior
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Obtener el título del ítem seleccionado
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":
                        // Crear un Intent para abrir la actividad de inicio
                        Intent intent = new Intent(FavouritesActivity.this, HomeActivity.class);
                        intent.putExtra("Nombre",User);
                        // Iniciar la nueva actividad
                        startActivity(intent);
                        return true;

                    case "Favoritos":

                        return true;

                    case "Perfil":
                        Intent intent2 = new Intent(FavouritesActivity.this, ProfileActivity.class);
                        intent2.putExtra("Nombre",User);
                        // Iniciar la nueva actividad
                        startActivity(intent2);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private ArrayList<Integer> Buscarid(String user){
        ArrayList<Integer> movieId = new ArrayList<>();

        int id = 0;
        myDb = new DataBaseHelper(FavouritesActivity.this);

        // Utiliza myDb.obtenerPelis en lugar de DataBaseHelper.obtenerPelis
        Cursor cursor = myDb.obtenerFav(myDb, user);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                id = cursor.getInt(cursor.getColumnIndexOrThrow("Movie_id"));

                movieId.add(id);
            }
            // Cerrar el cursor después de usarlo
            cursor.close();
        }

        return movieId;
    }

    private ArrayList<String> Buscarimg(String user){

        myDb = new DataBaseHelper(FavouritesActivity.this);

        ArrayList<Integer> movieId = Buscarid(user);

        ArrayList<String> miLista = new ArrayList<>();
        String img;

        // Utiliza myDb.obtenerPelis en lugar de DataBaseHelper.obtenerPelis
        Cursor c = myDb.obtenerReviewporID(myDb, movieId);

        if (c != null) {
            while (c.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                img = c.getString(c.getColumnIndexOrThrow("Movie_image"));

                // Agregar el usuario a la lista
                miLista.add(img);
            }

            // Cerrar el cursor después de usarlo
            c.close();
        }

        return miLista;
    }

    private ArrayList<String> Buscartitulo(String user){

        myDb = new DataBaseHelper(FavouritesActivity.this);

        ArrayList<Integer> movieId = Buscarid(user);

        ArrayList<String> miLista = new ArrayList<>();
        String tit;

        // Utiliza myDb.obtenerPelis en lugar de DataBaseHelper.obtenerPelis
        Cursor c = myDb.obtenerReviewporID(myDb, movieId);

        if (c != null) {
            while (c.moveToNext()) {
                // Obtener datos de las columnas correspondientes
                tit = c.getString(c.getColumnIndexOrThrow("Movie_title"));

                // Agregar el usuario a la lista
                miLista.add(tit);
            }

            // Cerrar el cursor después de usarlo
            c.close();
        }

        return miLista;
    }
    // Método para mostrar el menú para eliminar
    public void mostrarMenu(View view) {
        txtPuntuacion = findViewById(R.id.txtPuntuacion);
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItem(MenuItem item) {

            }
        });

        popupMenu.show();
    }
}