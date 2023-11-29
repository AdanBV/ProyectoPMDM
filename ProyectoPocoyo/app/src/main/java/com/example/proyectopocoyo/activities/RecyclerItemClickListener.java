package com.example.proyectopocoyo.activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    // Interfaz para manejar eventos de clic
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onLongItemClick(View view, int position);
    }

    // Detector de gestos utilizado para identificar clics largos
    GestureDetector mGestureDetector;

    // Constructor que recibe un contexto, un RecyclerView y un listener de eventos
    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
        mListener = listener;

        // Configuración del detector de gestos
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // Al detectar un clic largo, se obtiene la vista y la posición del adaptador y se llama al método correspondiente del listener
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && mListener != null) {
                    mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    // Método que intercepta los eventos táctiles en el RecyclerView
    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        // Se obtiene la vista y la posición del adaptador y se llama al método correspondiente del listener al detectar un clic
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    // Método que maneja eventos táctiles en el RecyclerView
    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        // No se implementa en este caso
    }

    // Método para manejar solicitudes de interrupción de eventos táctiles
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // No se implementa en este caso
    }
}
