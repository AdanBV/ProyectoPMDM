package com.example.starfilms.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;

// CLASE AYUDANTE PARA MANEJAR LA BD
public class DataBaseHelper extends SQLiteOpenHelper {
    private final Context context;

    // DB INFO
    private static final String DATABASE_NAME = "StarFilmsDB";
    private static final int DATABASE_VERSION = 1;

    // CONTRUCTOR DEL AYUDANTE
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static String[] obtenerReviews(DataBaseHelper myDb, String user) {
        return new String[0];
    }

    // CREACIÓN BD Y TABLAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SENTENCIA CREACIÓN TABLA (User)
        db.execSQL("CREATE TABLE User (User_id TEXT PRIMARY KEY NOT NULL, " +
                "User_name TEXT, " +
                "User_surname TEXT, " +
                "User_password TEXT NOT NULL, " +
                "CHECK (User_id != '' AND LENGTH(User_password) >= 5));");

        // SENTENCIA CREACIÓN TABLA (Movie)
        db.execSQL("CREATE TABLE Movie (Movie_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Movie_title TEXT NOT NULL, " +
                "Movie_director TEXT NOT NULL, " +
                "Movie_description TEXT NOT NULL, " +
                "Movie_rating REAL NOT NULL, " +
                "Movie_image TEXT NOT NULL," +
                "CHECK (Movie_rating >= 0.0 AND Movie_rating <= 5.0));");

        // SENTENCIA CREACIÓN TABLA (Review)
        db.execSQL("CREATE TABLE Review (Review_text TEXT, " +
                "Review_rating INTEGER NOT NULL, " +
                "User_id TEXT NOT NULL, " +
                "Movie_id INTEGER NOT NULL, " +
                "CHECK(Review_rating >= 0.0 AND Review_rating <= 5.0), " +
                "PRIMARY KEY(User_id, Movie_id), " +
                "FOREIGN KEY(User_id) REFERENCES User(User_id), " +
                "FOREIGN KEY(Movie_id) REFERENCES Movie(Movie_id));");

        // SENTENCIA CREACIÓN TABLA (Favourites)
        db.execSQL("CREATE TABLE Favourites (User_id TEXT NOT NULL, " +
                "Movie_id INTEGER NOT NULL, " +
                "PRIMARY KEY(User_id, Movie_id), " +
                "FOREIGN KEY(User_id) REFERENCES User(User_id), " +
                "FOREIGN KEY(Movie_id) REFERENCES Movie(Movie_id));");

        // AGREGAR DATOS A TABLAS
        initialData(db);
    }

    // MODIFICACIÓN ESTRUCTURA BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Movie");
        db.execSQL("DROP TABLE IF EXISTS Review");
        db.execSQL("DROP TABLE IF EXISTS Favourites");
        onCreate(db);
    }

    // INSERTAR DATOS INICIALES EN BD
    private void initialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();

        // DATOS TABLA (User)
        cv.put("User_id", "admin");
        cv.put("User_name", "Administrador");
        cv.put("User_surname", "Principal");
        cv.put("User_password", "admin");
        db.insert("User", null, cv);

        cv.clear();

        // DATOS TABLA (Movies) /* PARCIALMENTE GENERADO CON CHATGPT */
        // Episode I
        cv.put("Movie_title", "Star Wars: Episode I");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "The Phantom Menace" + "\n" + "El maestro jedi Qui-Gon Jinn " +
                "y su aprendiz Obi-Wan Kenobi, escoltan y protegen a la Reina " +
                "Amidala con la esperanza de encontrar una salida pacífica.");
        cv.put("Movie_rating", 4.5);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode1.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode II
        cv.put("Movie_title", "Star Wars: Episode II");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "Attack of the Clones" + "\n" + "Diez años después " +
                "La galaxia se encuentra al borde de una guerra civil.");
        cv.put("Movie_rating", 4.0);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode2.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode III
        cv.put("Movie_title", "Star Wars: Episode III");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "Revenge of the Sith" + "\n" + "Una época en la que los Caballeros Jedi se han " +
                "esparcido por toda la galaxia, dirigiendo un ejército clon masivo para " +
                "enfrentar a los Separatistas Galácticos.");
        cv.put("Movie_rating", 4.2);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode3.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode IV
        cv.put("Movie_title", "Star Wars: Episode IV");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "A New Hope" + "\n" + "La trama describe la historia de un grupo de guerrilleros, " +
                "la Alianza Rebelde, cuyo objetivo es destruir la estación espacial " +
                "Estrella de la Muerte.");
        cv.put("Movie_rating", 5.0);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode4.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode V
        cv.put("Movie_title", "Star Wars: Episode V");
        cv.put("Movie_director", "Irvin Kershner");
        cv.put("Movie_description", "The Empire Strikes Back" + "\n" + "La ficción de la película se sitúa tres años después de la " +
                "destrucción de la estación espacial de combate conocida como la " +
                "Estrella de la Muerte.");
        cv.put("Movie_rating", 4.3);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode5.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VI
        cv.put("Movie_title", "Star Wars: Episode VI");
        cv.put("Movie_director", "Richard Marquand");
        cv.put("Movie_description", "Return of the Jedi" + "\n" + "Luke no sabe que el Imperio Galáctico ha iniciado secretamente la " +
                "construcción de una nueva estación espacial blindada, incluso más " +
                "poderosa que la primera y temida Estrella de la Muerte.");
        cv.put("Movie_rating", 4.8);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode6.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VII
        cv.put("Movie_title", "Star Wars: Episode VII");
        cv.put("Movie_director", "J.J. Abrams");
        cv.put("Movie_description", "The Force Awakens" + "\n" + "Cuando el desertor Finn llega a un planeta desierto conoce a Rey, " +
                "cuyo androide contiene un mapa secreto.");
        cv.put("Movie_rating", 4.0);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode7.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VIII
        cv.put("Movie_title", "Star Wars: Episode VIII");
        cv.put("Movie_director", "Rian Johnson");
        cv.put("Movie_description", "The Last Jedi" + "\n" + "La Resistencia encabezada por la general Leia Organa (Carrie Fisher) " +
                "ha logrado contener temporalmente a la siniestra Primera Orden, " +
                "un nuevo grupo militar nacido de las cenizas del Imperio Galáctico");
        cv.put("Movie_rating", 4.9);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode8.jpg?raw=true");
        db.insert("Movie", null, cv);
    }

    // INSERTAR NUEVO USUARIO EN TABLA (User) ~ "Registrarse"
    public boolean addUser(String userId, String userName, String userSurname, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = null;

        try {
            // Consulta si existe el usuario
            cursor = db.query("User", new String[]{"User_id"}, "User_id = ?",
                    new String[]{userId}, null, null, null);

            if (cursor.moveToFirst()) {
                // Si el usuario ya existe se avisa
                Toast.makeText(context, "User already in use", Toast.LENGTH_LONG).show();
                return false;
            } else {
                // Si no existe intenta crear el usuario
                cv.put("User_id", userId);
                cv.put("User_name", userName);
                cv.put("User_surname", userSurname);
                cv.put("User_password", userPassword);

                long result = db.insert("User", null, cv);

                if (result == -1) {
                    // Si no puede crear el usuario se aivsa
                    if (userPassword.equals("")) {
                        Toast.makeText(context, "Failed to create user, password should be " +
                                "at least 5 digits lenght.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Failed to create user", Toast.LENGTH_LONG).show();
                    }
                    return false;

                } else {
                    // Si puede crear el usuario se indica
                    Toast.makeText(context, "New user successfully registered", Toast.LENGTH_LONG).show();
                    return true;
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    // INSERTAR NUEVA RESEÑA EN LA TABLA (Review)
    public boolean addReview(String reviewText, int reviewRating, String userId, int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Intenta crear la reseña
        cv.put("Review_text", reviewText);
        cv.put("Review_rating", reviewRating);
        cv.put("User_id", userId);
        cv.put("Movie_id", movieId);

        long result = db.insert("Review", null, cv);

        if (result == -1) {
            // Error de creación
            Toast.makeText(context, "Failed to create review", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // Exito de creación
            Toast.makeText(context, "New review successfully created", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    // Obtiene todas las peliculas almacenadas en la BD
    public Cursor readAllMoviesFromDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM Movie", null);
        }
        return cursor;
    }

    // Obtiene todos los datos de una pelicula a partir del titulo de la pelicula
    public static Cursor obtenerPelis(@NonNull DataBaseHelper dbHelper, String title) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Movie WHERE Movie_title = '" + title + "';";
        return db.rawQuery(query, null);
    }

    // Obtiene la reseña de una pelicula a partir del identificador de la pelicula
    public static Cursor obtenerReview(@NonNull DataBaseHelper dbHelper, int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Review WHERE Movie_id = " + id + ";";
        return db.rawQuery(query, null);
    }
}
