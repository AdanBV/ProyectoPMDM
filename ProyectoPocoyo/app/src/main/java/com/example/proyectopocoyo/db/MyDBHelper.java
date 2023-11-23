package com.example.proyectopocoyo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StarFilmsDB";
    private static final int DATABASE_VERSION = 1;

    // Constructor
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crear la tabla Usuario
        db.execSQL("CREATE TABLE User(idUsuario TEXT, )";
        db.execSQL(User.INSERT_QUOTES_SCRIPT);

        //Crear la tabla Pelicula
        db.execSQL(Movie.CREATE_QUOTES_SCRIPT);
        db.execSQL(Movie.INSERT_QUOTES_SCRIPT);

        //Crear la tabla Reseñas
        db.execSQL(Review.CREATE_QUOTES_SCRIPT);
        db.execSQL(Review.INSERT_QUOTES_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
    }
}
