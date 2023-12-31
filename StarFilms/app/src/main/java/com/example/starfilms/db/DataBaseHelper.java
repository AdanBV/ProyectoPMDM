package com.example.starfilms.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

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

        // Star Wars

        // Episode I
        cv.put("Movie_title", "Star Wars: Episode I");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "The Phantom Menace:" + "\n" + "El maestro jedi Qui-Gon Jinn " +
                "y su aprendiz Obi-Wan Kenobi, escoltan y protegen a la Reina " +
                "Amidala con la esperanza de encontrar una salida pacífica.");
        cv.put("Movie_rating", 4.5);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode1.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode II
        cv.put("Movie_title", "Star Wars: Episode II");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "Attack of the Clones:" + "\n" + "Diez años después " +
                "La galaxia se encuentra al borde de una guerra civil.");
        cv.put("Movie_rating", 4.0);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode2.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode III
        cv.put("Movie_title", "Star Wars: Episode III");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "Revenge of the Sith:" + "\n" + "Una época en la que los Caballeros Jedi se han " +
                "esparcido por toda la galaxia, dirigiendo un ejército clon masivo para " +
                "enfrentar a los Separatistas Galácticos.");
        cv.put("Movie_rating", 4.2);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode3.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode IV
        cv.put("Movie_title", "Star Wars: Episode IV");
        cv.put("Movie_director", "George Lucas");
        cv.put("Movie_description", "A New Hope:" + "\n" + "La trama describe la historia de un grupo de guerrilleros, " +
                "la Alianza Rebelde, cuyo objetivo es destruir la estación espacial " +
                "Estrella de la Muerte.");
        cv.put("Movie_rating", 5.0);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode4.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode V
        cv.put("Movie_title", "Star Wars: Episode V");
        cv.put("Movie_director", "Irvin Kershner");
        cv.put("Movie_description", "The Empire Strikes Back:" + "\n" + "La ficción de la película se sitúa tres años después de la " +
                "destrucción de la estación espacial de combate conocida como la " +
                "Estrella de la Muerte.");
        cv.put("Movie_rating", 4.3);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode5.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VI
        cv.put("Movie_title", "Star Wars: Episode VI");
        cv.put("Movie_director", "Richard Marquand");
        cv.put("Movie_description", "Return of the Jedi:" + "\n" + "Luke no sabe que el Imperio Galáctico ha iniciado secretamente la " +
                "construcción de una nueva estación espacial blindada, incluso más " +
                "poderosa que la primera y temida Estrella de la Muerte.");
        cv.put("Movie_rating", 4.8);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode6.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VII
        cv.put("Movie_title", "Star Wars: Episode VII");
        cv.put("Movie_director", "J.J. Abrams");
        cv.put("Movie_description", "The Force Awakens:" + "\n" + "Cuando el desertor Finn llega a un planeta desierto conoce a Rey, " +
                "cuyo androide contiene un mapa secreto.");
        cv.put("Movie_rating", 4.0);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode7.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Episode VIII
        cv.put("Movie_title", "Star Wars: Episode VIII");
        cv.put("Movie_director", "Rian Johnson");
        cv.put("Movie_description", "The Last Jedi:" + "\n" + "La Resistencia encabezada por la general Leia Organa (Carrie Fisher) " +
                "ha logrado contener temporalmente a la siniestra Primera Orden, " +
                "un nuevo grupo militar nacido de las cenizas del Imperio Galáctico");
        cv.put("Movie_rating", 4.9);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/StarWars_episode8.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

        // Fast & Furious

        // Película 1
        cv.put("Movie_title", "Fast & Furious 1");
        cv.put("Movie_director", "Rob Cohen");
        cv.put("Movie_description", "The Fast and the Furious" + "\n" + "Brian O'Conner, un policía encubierto, se infiltra " +
                "en el mundo del street racing para atrapar a los ladrones de equipos electrónicos.");
        cv.put("Movie_rating", 4.5);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 2
        cv.put("Movie_title", "Fast & Furious 2");
        cv.put("Movie_director", "John Singleton");
        cv.put("Movie_description", "2 Fast 2 Furious" + "\n" + "Brian O'Conner y su exconvicto amigo Roman Pierce se asocian para llevar a cabo una misión " +
                "para la policía de Miami.");
        cv.put("Movie_rating", 4.2);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius2.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 3
        cv.put("Movie_title", "Fast & Furious 3");
        cv.put("Movie_director", "Justin Lin");
        cv.put("Movie_description", "Tokyo Drift" + "\n" + "Un joven rebelde de Los Ángeles es enviado a vivir con su padre en Tokio, donde se ve envuelto " +
                "en las carreras callejeras y la cultura del drift.");
        cv.put("Movie_rating", 4.0);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius3.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 4
        cv.put("Movie_title", "Fast & Furious 4");
        cv.put("Movie_director", "Justin Lin");
        cv.put("Movie_description", "Even Faster" + "\n" + "Dominic Toretto y Brian O'Conner vuelven a trabajar juntos para derrotar a un narcotraficante común.");
        cv.put("Movie_rating", 4.3);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius4.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 5
        cv.put("Movie_title", "Fast & Furious 5");
        cv.put("Movie_director", "Justin Lin");
        cv.put("Movie_description", "Fast Five" + "\n" + "Dominic Toretto y su equipo planean un último robo para comprar su libertad mientras son perseguidos " +
                "por un implacable agente federal.");
        cv.put("Movie_rating", 4.5);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius5.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 6
        cv.put("Movie_title", "Fast & Furious 6");
        cv.put("Movie_director", "Justin Lin");
        cv.put("Movie_description", "All Roads Lead To This" + "\n" + "Dominic Toretto y su equipo se asocian con el agente Hobbs para detener a un equipo rival de " +
                "conductores mercenarios.");
        cv.put("Movie_rating", 4.4);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius6.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 7
        cv.put("Movie_title", "Fast & Furious 7");
        cv.put("Movie_director", "James Wan");
        cv.put("Movie_description", "Vengeance Hits Home" + "\n" + "Dominic Toretto y su equipo se enfrentan a Deckard Shaw, un asesino buscando venganza por la muerte de su hermano.");
        cv.put("Movie_rating", 4.6);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius7.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 8
        cv.put("Movie_title", "Fast & Furious 8");
        cv.put("Movie_director", "F. Gary Gray");
        cv.put("Movie_description", "The Fate Of The Furious" + "\n" + "Dominic Toretto se ve obligado a traicionar a su familia después de ser seducido por una mujer misteriosa.");
        cv.put("Movie_rating", 4.2);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius8.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 9
        cv.put("Movie_title", "Fast & Furious 9");
        cv.put("Movie_director", "Justin Lin");
        cv.put("Movie_description", "The Fast Saga" + "\n" + "Dominic Toretto y su equipo enfrentan una nueva amenaza mientras descubren secretos del pasado de Dom.");
        cv.put("Movie_rating", 4.1);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius9.jpg?raw=true");
        db.insert("Movie", null, cv);

        cv.clear();

// Película 10
        cv.put("Movie_title", "Fast & Furious 10");
        cv.put("Movie_director", "Louis Leterrier");
        cv.put("Movie_description", "The End Of The Road Begins" + "\n" + "Dom Toretto y su familia enfrentan a un enemigo letal del pasado sediento de venganza." +
                " La trama se centra en su lucha contra este adversario para proteger a la familia y lo que Dom valora.");
        cv.put("Movie_rating", 4.8);
        cv.put("Movie_image", "https://github.com/AdanBV/ProyectoPMDM/blob/master/Imagenes/FastandFurius10.jpg?raw=true");
        db.insert("Movie", null, cv);
    }

    // INSERTAR NUEVO USUARIO EN TABLA (User) ~ "Registrarse"
    public boolean addUser(String userId, String userName, String userSurname, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Cursor cursor = null;

        try {
            // Consulta si existe el usuario
            cursor = db.query("User", new String[]{"User_id"}, "User_id = ?", new String[]{userId}, null, null, null);

            if (cursor.moveToFirst()) {
                // Si el usuario ya existe se avisa
                Toast.makeText(context, "Usuario en uso.", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(context, "La contraseña tiene que tener 5 digitos como mínimo.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "La contraseña tiene que tener 5 digitos como mínimo.", Toast.LENGTH_LONG).show();
                    }
                    return false;

                } else {
                    // Si puede crear el usuario se indica
                    Toast.makeText(context, "Nuevo usuario registrado.", Toast.LENGTH_LONG).show();
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

        if (reviewExists(userId, movieId)) {
            // Si existe, actualiza la reseña
            return updateReview(reviewText, reviewRating, userId, movieId);
        } else {
            // Si no existe, crea una nueva reseña
            ContentValues cv = new ContentValues();

            cv.put("Review_text", reviewText);
            cv.put("Review_rating", reviewRating);
            cv.put("User_id", userId);
            cv.put("Movie_id", movieId);

            long result = db.insert("Review", null, cv);

            if (result == -1) {
                // Error de creación
                Toast.makeText(context, "Fallo al crear la review.", Toast.LENGTH_LONG).show();
                return false;
            } else {
                // Éxito de creación
                Toast.makeText(context, "Nueva review creada", Toast.LENGTH_LONG).show();
                return true;
            }
        }
    }

    // Método para verificar si la reseña ya existe
    private boolean reviewExists(String userId, int movieId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Review WHERE User_id = ? AND Movie_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userId, String.valueOf(movieId)});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    private boolean updateReview(String reviewText, int reviewRating, String userId, int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Review_text", reviewText);
        cv.put("Review_rating", reviewRating);

        int result = db.update("Review", cv, "User_id = ? AND Movie_id = ?", new String[]{userId, String.valueOf(movieId)});

        if (result > 0) {
            // Éxito de actualización
            Toast.makeText(context, "Review actualizada", Toast.LENGTH_LONG).show();
            return true;
        } else {
            // Error de actualización
            Toast.makeText(context, "Fallo al actualizar la review", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean addFav(String userid, int movieid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Intenta crear la reseña
        cv.put("User_id", userid);
        cv.put("Movie_id", movieid);


        long result = db.insert("Favourites", null, cv);

        if (result == -1) {
            // Error de creación
            Toast.makeText(context, "Fallo al aladir a Favoritos.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // Exito de creación
            Toast.makeText(context, "Añadia a Favoritos.", Toast.LENGTH_LONG).show();
            return true;
        }
    }

    public void deleteFavourite(String userId, int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = "User_id = ? AND Movie_id = ?";
        String[] whereArgs = {userId, String.valueOf(movieId)};

        // Utilizamos el método delete para borrar la fila que cumple con la condición
        db.delete("Favourites", whereClause, whereArgs);

        // Cerrar la conexión de la base de datos después de usarla
        db.close();
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

    public static Cursor obtenerReviews(@NonNull DataBaseHelper myDb, String user) {
        SQLiteDatabase db = myDb.getReadableDatabase();
        String query = "SELECT * FROM Review WHERE User_id = '" + user + "';";
        return db.rawQuery(query, null);
    }

    public static Cursor obtenerReviewporID(@NonNull DataBaseHelper dbHelper, ArrayList<Integer> ids) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Construir la cláusula WHERE con los IDs de la lista
        StringBuilder whereClause = new StringBuilder("Movie_id IN (");
        for (int i = 0; i < ids.size(); i++) {
            whereClause.append(ids.get(i));
            if (i < ids.size() - 1) {
                whereClause.append(", ");
            }
        }
        whereClause.append(")");

        // Construir la consulta SQL con la cláusula WHERE dinámica
        String query = "SELECT * FROM Movie WHERE " + whereClause.toString() + ";";

        // Ejecutar la consulta y devolver el cursor
        return db.rawQuery(query, null);
    }

    public static Cursor obtenerFav(@NonNull DataBaseHelper myDb, String user) {
        SQLiteDatabase db = myDb.getReadableDatabase();
        String query = "SELECT * FROM Favourites WHERE User_id = '" + user + "';";
        return db.rawQuery(query, null);
    }

    public static Cursor obtenerPeliId(@NonNull DataBaseHelper dbHelper, int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Movie WHERE Movie_id = " + id + ";";
        return db.rawQuery(query, null);
    }

    public static final String TABLE_USER = "User";
    public static final String COLUMN_USER_ID = "User_id";
    public static final String COLUMN_USER_NAME = "User_name";
    public static final String COLUMN_USER_SURNAME = "User_surname";

    // Constructor y métodos onCreate y onUpgrade

    // Método para actualizar el nombre y el apellido de un usuario
    public void updateUser(String userId, String newName, String newSurname) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = "UPDATE " + TABLE_USER + " SET " + COLUMN_USER_NAME + " = '" + newName + "'," +
                " " + COLUMN_USER_SURNAME + " = '" + newSurname + "' " + "WHERE " + COLUMN_USER_ID + " = '" + userId + "'";
        db.execSQL(updateQuery);
        db.close();
    }

    public Cursor getUserById(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_ID + " = '" + userId + "'";
        return db.rawQuery(selectQuery, null);
    }
}
