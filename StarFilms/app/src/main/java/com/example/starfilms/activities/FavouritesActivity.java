package com.example.starfilms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.starfilms.R;
import com.example.starfilms.clases.AdapterFavourites;
import com.example.starfilms.db.DataBaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    String User;
    int pos;
    ListView lv;
    ArrayList<String> img, titulos;
    AdapterFavourites adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        Intent intent = getIntent();
        User = intent.getStringExtra("Nombre");

        img = Buscarimg(User);
        titulos = Buscartitulo(User);

        adapter = new AdapterFavourites(this, titulos,img);

        lv = findViewById(R.id.listaFavoritas);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavouritesActivity.this, MovieActivity.class);
                TextView txt = view.findViewById(R.id.txtPeli);
                intent.putExtra("Titulo",txt.getText().toString());
                intent.putExtra("Nombre",User);
                // Iniciar la nueva actividad
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;

                mostrarMenu(view);

                return true;
            }
        });

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

    private void eliminarElemento(int position) {
        if (position >= 0 && position < adapter.getCount()) {
            // Eliminar el elemento del adaptador
            img.remove(position);
            titulos.remove(position);

            // Notificar al adaptador que los datos han cambiado
            adapter.notifyDataSetChanged();
        }
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
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_delete, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.delete){
                    TextView txt = view.findViewById(R.id.txtPeli);
                    int id = Obtenerid(txt.getText().toString());

                    myDb.deleteFavourite(User,id);

                    eliminarElemento(pos);
                }
                return true;
            }
        });

        popupMenu.show();
    }

    public int Obtenerid(String title){
        int id = 0;

        Cursor cursor = myDb.obtenerPelis(myDb, title);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Obtener datos de las columnas correspondientes
                id = cursor.getInt(cursor.getColumnIndexOrThrow("Movie_id"));
            }
            // Cerrar el cursor después de usarlo
            cursor.close();
        }

        return id;
    }
}