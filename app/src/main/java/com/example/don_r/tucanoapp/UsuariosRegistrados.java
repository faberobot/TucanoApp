package com.example.don_r.tucanoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TOSHIBA on 4/13/2017.
 */

public class UsuariosRegistrados extends SQLiteOpenHelper {

    String sqlCreateUsuarios = "CREATE TABLE Usuarios (" +
            "id             INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombre          TEXT,"+
            "correo          TEXT,"+
            "contrasena      TEXT,"+
            "progreso        TEXT)";

    public UsuariosRegistrados(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateUsuarios); // Se crea la tabla
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usuarios"); // Actualiza o crea la base de datos si no existe
        db.execSQL(sqlCreateUsuarios);
    }
}
