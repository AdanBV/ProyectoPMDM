package com.example.proyectopocoyo.db;

public class UserDataSource {
    //Meta información de la base de datos
    public static final String USER_TABLE_NAME = "User";
    public static final String STRING_TYPE = "text";
    public static final String INT_TYPE = "integer";

    //Campos de la tabla User
    public static class ColumnasUsuarios {
        public static final String ID_USER = "ID_USER";
        public static final String NOMBRE = "NOMBRE";
        public static final String AUTHOR_QUOTES = "author";
    }
    public static class ColumnApellido {
        //public static final String ID_QUOTES = BaseColumns._ID;
        public static final String BODY_QUOTES = "body";
        public static final String AUTHOR_QUOTES = "author";
    }
}
