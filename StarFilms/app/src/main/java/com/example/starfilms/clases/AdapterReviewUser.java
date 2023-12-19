package com.example.starfilms.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.starfilms.R;

import java.util.ArrayList;

public class AdapterReviewUser extends ArrayAdapter<String> {

    private final ArrayList<String> movieTitle;
    private final ArrayList<String> review;
    public AdapterReviewUser(Context context, ArrayList<String> Movie_title, ArrayList<String> Review) {
        super(context, R.layout.activity_adapter_review_user,Movie_title);

        this.movieTitle = Movie_title;
        this.review = Review;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) view = LayoutInflater.from(getContext()).inflate(R.layout.activity_adapter_review_user, parent, false);

        // Obtener referencias a las vistas en el diseño
        TextView txtRev = view.findViewById(R.id.txtTitRev);
        TextView txtPeli = view.findViewById(R.id.txtTitPeli);

        // Establecer los valores de las vistas con los datos del elemento
        txtPeli.setText(movieTitle.get(position));
        txtRev.setText((review.get(position)));

        return view;
    }
}
