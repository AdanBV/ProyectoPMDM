package com.example.starfilms.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.starfilms.R;

import java.util.ArrayList;

public class AdapterReviewMovie extends ArrayAdapter<String> {

    private final ArrayList<String> user;
    private final ArrayList<String> movieTitle;
    public AdapterReviewMovie(Context context, ArrayList<String> User, ArrayList<String> Movie_title) {
        super(context,R.layout.activity_adapter_review_peli,User);

        this.movieTitle = Movie_title;
        this.user = User;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) view = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_review_peli, parent, false);
        
        // Obtener referencias a las vistas en el diseño
        TextView txtTitRev = view.findViewById(R.id.txtTitRev);
        TextView txtUser = view.findViewById(R.id.txtUser);

        // Establecer los valores de las vistas con los datos del elemento
        txtTitRev.setText(movieTitle.get(position));
        txtUser.setText(user.get(position));

        return view;
    }

}