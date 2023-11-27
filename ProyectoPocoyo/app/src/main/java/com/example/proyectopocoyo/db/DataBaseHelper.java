package com.example.proyectopocoyo.db;

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

    // CREACIÓN BD Y TABLAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SENTENCIA CREACIÓN TABLA (User)
        db.execSQL("CREATE TABLE User (User_id TEXT PRIMARY KEY NOT NULL, " +
                "User_name TEXT, " +
                "User_surname TEXT, " +
                "User_password TEXT NOT NULL, " +
                "CHECK (User_id != '' AND LENGTH(User_password) >= 8));");

        // SENTENCIA CREACIÓN TABLA (Movie)
        db.execSQL("CREATE TABLE Movie (Movie_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Movie_title TEXT NOT NULL, " +
                "Movie_director TEXT NOT NULL, " +
                "Movie_description TEXT NOT NULL, " +
                "Movie_rating REAL NOT NULL, " +
                "Movie_image TEXT NOT NULL," +
                "CHECK (Movie_rating >= 0.0 AND Movie_rating <= 10.0));");

        // SENTENCIA CREACIÓN TABLA (Review)
        db.execSQL("CREATE TABLE Review (Review_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Review_text TEXT, " +
                "Review_rating REAL NOT NULL, " +
                "User_id INTEGER NOT NULL, " +
                "Movie_id INTEGER NOT NULL, " +
                "CHECK(Review_rating >= 0.0 AND Review_rating <= 10.0), " +
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

        // DATOS TABLA (Movies) /* CHATGPT GENERATED */
        // Episode I
        cv.put("Movie_title", "Star Wars: Episode I");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "The Phantom Menace" + "\n" + "El maestro jedi Qui-Gon Jinn " +
                "y su aprendiz Obi-Wan Kenobi, escoltan y protegen a la Reina " +
                "Amidala con la esperanza de encontrar una salida pacífica.");
        cv.put("Movie_rating", 8.5);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode II
        cv.put("Movie_title", "Star Wars: Episode II");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "Attack of the Clones" + "\n" + "Diez años después " +
                "La galaxia se encuentra al borde de una guerra civil.");
        cv.put("Movie_rating", 8.0);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode III
        cv.put("Movie_title", "Star Wars: Episode III");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "Revenge of the Sith" + "\n" + "Una época en la que los Caballeros Jedi se han " +
                "esparcido por toda la galaxia, dirigiendo un ejército clon masivo para " +
                "enfrentar a los Separatistas Galácticos.");
        cv.put("Movie_rating", 8.2);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode IV
        cv.put("Movie_title", "Star Wars: Episode IV");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "A New Hope" + "\n" + "La trama describe la historia de un grupo de guerrilleros, " +
                "la Alianza Rebelde, cuyo objetivo es destruir la estación espacial " +
                "Estrella de la Muerte.");
        cv.put("Movie_rating", 9.0);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode V
        cv.put("Movie_title", "Star Wars: Episode V");
        cv.put("Movie_director", "Irvin Kershner");
        cv.put("Movie_description", "The Empire Strikes Back" + "\n" + "La ficción de la película se sitúa tres años después de la " +
                "destrucción de la estación espacial de combate conocida como la " +
                "Estrella de la Muerte.");
        cv.put("Movie_rating", 9.3);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VI
        cv.put("Movie_title", "Star Wars: Episode VI");
        cv.put("Movie_director", "Richard Marquand");
        cv.put("Movie_description", "Return of the Jedi" + "\n" + "Luke no sabe que el Imperio Galáctico ha iniciado secretamente la " +
                "construcción de una nueva estación espacial blindada, incluso más " +
                "poderosa que la primera y temida Estrella de la Muerte.");
        cv.put("Movie_rating", 8.8);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VII
        cv.put("Movie_title", "Star Wars: Episode VII");
        cv.put("Movie_director", "J.J. Abrams");
        cv.put("Movie_description", "The Force Awakens" + "\n" + "Cuando el desertor Finn llega a un planeta desierto conoce a Rey, " +
                "cuyo androide contiene un mapa secreto.");
        cv.put("Movie_rating", 8.0);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VIII
        cv.put("Movie_title", "Star Wars: Episode VIII");
        cv.put("Movie_director", "Rian Johnson");
        cv.put("Movie_description", "The Last Jedi" + "\n" + "La Resistencia encabezada por la general Leia Organa (Carrie Fisher) " +
                "ha logrado contener temporalmente a la siniestra Primera Orden, " +
                "un nuevo grupo militar nacido de las cenizas del Imperio Galáctico");
        cv.put("Movie_rating", 7.9);
        cv.put("Movie_image", "C:\\Users\\nel_a\\Documents\\RepositoriosGIT\\ProyectoPMDM\\ProyectoPocoyo\\app\\src\\main\\res\\drawable\\episodio1.jpg");
        db.insert("Movie", null, cv);
    }

    // INSERTAR NUEVO USUARIO EN TABLA (User)
    public boolean addUser(String userId, String userName, String userSurname, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Consulta si existe el usuario
        Cursor cursor = db.query("User", new String[]{"User_id"}, "User_id = ?",
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
                            "at least 8 digits lenght.", Toast.LENGTH_LONG).show();
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
    }

    public boolean addReview(int reviewId, String reviewText, float reviewRating, int userId, int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Review_id", reviewId);
        cv.put("Review_text", reviewText);
        cv.put("Review_rating", reviewRating);
        cv.put("User_id", userId);
        cv.put("Movie_id", movieId);

        long result = db.insert("Review", null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed to create review", Toast.LENGTH_LONG).show();
            return false;
        } else {
            Toast.makeText(context, "New review successfully created", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    public void addMovie(int Movie_id, String Movie_title, String Movie_director, String Movie_description, Double Movie_rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Movie_id", Movie_id);
        cv.put("Movie_title", Movie_title);
        cv.put("Movie_director", Movie_director);
        cv.put("Movie_description", Movie_description);
        cv.put("Movie_rating", Movie_rating);
    }

    public Cursor readDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM Movie", null);
        }
        return cursor;
    }

    public static Cursor obtenerPelis(@NonNull DataBaseHelper dbHelper, String title) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Movie WHERE Movie_title = '" + title + "';";
        return db.rawQuery(query, null);
    }
}
