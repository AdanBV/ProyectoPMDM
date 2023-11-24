package com.example.proyectopocoyo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarFilmsDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StarFilmsDB";
    private static final int DATABASE_VERSION = 1;

    // Tabla Usuario
    private static final String CREATE_TABLE_USUARIO =
            "CREATE TABLE Usuario (idUsuario TEXT PRIMARY KEY, " +
                    "Nombre TEXT, " +
                    "Apellido TEXT, " +
                    "Contrasena TEXT);";

    // Tabla Pelicula
    private static final String CREATE_TABLE_PELICULA =
            "CREATE TABLE Pelicula (idPelicula INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Titulo TEXT, " +
                    "Director TEXT, " +
                    "Descripcion TEXT, " +
                    "ValoracionMedia REAL " +
                    "CHECK (ValoracionMedia >= 0.0 AND ValoracionMedia <= 10.0));";

    // Tabla Resena
    private static final String CREATE_TABLE_RESENA =
            "CREATE TABLE Resena (idResena INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Texto TEXT, " +
                    "Valoracion REAL, " +
                    "idUsuario INTEGER, " +
                    "idPelicula INTEGER, " +
                    "CHECK(Valoracion >= 0.0 AND Valoracion <= 10.0), " +
                    "FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario), " +
                    "FOREIGN KEY(idPelicula) REFERENCES Pelicula(idPelicula));";

    public StarFilmsDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_PELICULA);
        db.execSQL(CREATE_TABLE_RESENA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
