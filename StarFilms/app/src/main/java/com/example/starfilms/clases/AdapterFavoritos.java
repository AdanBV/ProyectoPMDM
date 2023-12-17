package com.example.starfilms.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.starfilms.R;

import java.util.ArrayList;

public class AdapterFavoritos extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> titulo;
    private final ArrayList<String> img;
    public AdapterFavoritos(Context context, ArrayList<String> title, ArrayList<String> image) {
        super(context, R.layout.item_favorito,title);

        this.context = context;
        this.titulo = title;
        this.img = image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) view = LayoutInflater.from(getContext()).inflate(R.layout.item_favorito, parent, false);

        // Obtener referencias a las vistas en el diseño
        TextView txtPeli = view.findViewById(R.id.txtPeli);
        ImageView imgPeli = view.findViewById(R.id.imgPeli);

        // Establecer los valores de las vistas con los datos del elemento
        txtPeli.setText(titulo.get(position));
        Glide.with(context).load(img.get(position)).into(imgPeli);

        return view;
    }

}