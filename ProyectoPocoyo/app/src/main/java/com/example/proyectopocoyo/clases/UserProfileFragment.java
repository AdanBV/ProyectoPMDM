package com.example.proyectopocoyo.clases;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectopocoyo.R;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño de fragmento para mostrar el perfil del usuario
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        // Puedes configurar aquí la interfaz de usuario y la lógica para mostrar el perfil del usuario
        return view;
    }
}
