
// PÁGINA PRINCIPAL DE LA APP (INICIO)

package com.example.proyectopocoyo.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectopocoyo.clases.Pelicula;
import com.example.proyectopocoyo.clases.PeliculaAdapter;
import com.example.proyectopocoyo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final int[] posicion = new int[]{0,1,2,3};
        // VISTA XML QUE SE VA A MOSTRAR
        setContentView(R.layout.activity_home);

        // REFERENCIA A LA BARRA DE NAVEGACIÓN INFERIOR
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar);

        // HAY QUE AÑADIR UN LISTENER PARA LA BARRA DE NAVEGACÓN INFERIOR

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                String t = (String) menuItem.getTitle();
                switch (t) {
                    case "Inicio":
                        showToast("Clic en Inicio");
                        return true;

                    case "Favoritos":
                        showToast("Clic en Favoritos");
                        return true;

                    case "Añadir":
                        Intent intent2 = new Intent(HomeActivity.this, ReviewActivity.class);
                        // Iniciar la nueva actividad
                        startActivity(intent2);
                        return true;

                    case "Perfil":
                        Intent intent = new Intent(HomeActivity.this, PerfilActivity.class);
                        // Iniciar la nueva actividad
                        startActivity(intent);
                        return true;

                    default:
                        return false;
                }
            }
        });



        // LISTA DE PELICULAS PARA PROBAR
        List<Pelicula> movieList = new ArrayList<>();

        movieList.add(new Pelicula(R.drawable.episodio1, "La amenaza fantasma", 1.5f));
        movieList.add(new Pelicula(R.drawable.episodio2, "El ataque de los clones", 2.0f));
        movieList.add(new Pelicula(R.drawable.episodio3, "La venganza de los Sith", 2.5f));
        movieList.add(new Pelicula(R.drawable.episodio4, "Una nueva esperanza", 3.0f));
        movieList.add(new Pelicula(R.drawable.episodio5, "El Imperio contraataca", 3.5f));
        movieList.add(new Pelicula(R.drawable.episodio6, "El retorno de los Jedi", 4.0f));
        movieList.add(new Pelicula(R.drawable.episodio7, "El despertar de la fuerza", 4.5f));
        movieList.add(new Pelicula(R.drawable.episodio8, "Los últimos Jedi", 5.0f));

        // REFERENCIA AL RECYCLERVIEW
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // PRESENTACIÓN DE LOS ELEMENTOS DENTRO DEL RECYCLERVIEW
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // ADAPTADOR PARA MOSTRAR LA LISTA DE PELICULAS CORRECTAMENTE
        PeliculaAdapter adapter = new PeliculaAdapter(movieList, this);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent2 = new Intent(HomeActivity.this,
                                MovieActivity.class);

                        // Iniciar la nueva actividad
                        startActivity(intent2);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Intent intent2 = new Intent(HomeActivity.this,
                                MovieActivity.class);

                        // Iniciar la nueva actividad
                        startActivity(intent2);
                    }
                })
        );
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}