package com.example.proyectopocoyo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class StarFilmsDataBaseHelper extends SQLiteOpenHelper {

    // DB INFO
    private final Context context;
    private static final String DATABASE_NAME = "StarFilmsDB";
    private static final int DATABASE_VERSION = 1;

    // TABLA USUARIO
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE User (User_id TEXT PRIMARY KEY, " +
                    "User_name TEXT, " +
                    "User_surname TEXT, " +
                    "User_password TEXT);";

    // TABLA PELICULA
    private static final String CREATE_TABLE_MOVIE =
            "CREATE TABLE Movie (Movie_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Movie_title TEXT, " +
                    "Movie_director TEXT, " +
                    "Movie_description TEXT, " +
                    "Movie_rating REAL " +
                    "CHECK (Movie_rating >= 0.0 AND Movie_rating <= 10.0));";

    // TABLA RESENA
    private static final String CREATE_TABLE_REVIEW =
            "CREATE TABLE Review (Review_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Review_text TEXT, " +
                    "Review_rating REAL, " +
                    "User_id INTEGER, " +
                    "Movie_id INTEGER, " +
                    "CHECK(Review_rating >= 0.0 AND Review_rating <= 10.0), " +
                    "FOREIGN KEY(User_id) REFERENCES User(User_id), " +
                    "FOREIGN KEY(Movie_id) REFERENCES Movie(Movie_id));";

    public StarFilmsDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_MOVIE);
        db.execSQL(CREATE_TABLE_REVIEW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    public void addUser(String userId, String userName, String userSurname, String userPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("User_id", userId);
        cv.put("User_name", userName);
        cv.put("User_surname", userSurname);
        cv.put("User_password", userPassword);

        long result = db.insert("User", null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed to create user", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "New user successfully registered", Toast.LENGTH_LONG).show();
        }
    }

    public void addReview(int reviewId, String reviewText, float reviewRating, int userId, int movieId) {
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
        } else {
            Toast.makeText(context, "New review successfully created", Toast.LENGTH_LONG).show();
        }
    }
    public void addPeli(int Movie_id, String Movie_title, String Movie_director, String Movie_description, Double Movie_rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Movie_id", Movie_id);
        cv.put("Movie_title", Movie_title);
        cv.put("Movie_director", Movie_director);
        cv.put("Movie_description", Movie_description);
        cv.put("Movie_rating", Movie_rating);

        long result = db.insert("Pelicula", null, cv);

        if (result == -1) {
            Toast.makeText(context, "Failed to create movie", Toast.LENGTH_LONG).show();
        }
    }

    public Cursor obtenerPelis() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Movie";
        return db.rawQuery(query, null);
    }
}
