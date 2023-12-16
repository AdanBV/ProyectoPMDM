package com.example.starfilms.clases;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.starfilms.R;
import com.example.starfilms.activities.MovieActivity;

import java.util.ArrayList;
import java.util.List;

public class AdapterReviewPeli extends ArrayAdapter<String> {

    private Context context;
    private final ArrayList<String> user;
    private final String movieTitle;
    public AdapterReviewPeli(Context context, ArrayList<String> User, String Movie_title) {
        super(context,0);

        this.context = context;
        this.movieTitle = Movie_title;
        this.user = User;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Reutilizar la vista si está disponible, de lo contrario, inflarla
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_review_peli, parent, false);
        }

        // Obtener referencias a las vistas en el diseño
        TextView txtTitRev = convertView.findViewById(R.id.txtTitRev);
        TextView txtUser = convertView.findViewById(R.id.txtUser);

        // Establecer los valores de las vistas con los datos del elemento
        txtTitRev.setText(movieTitle);
        txtUser.setText(user.get(position));

        return convertView;
    }

}