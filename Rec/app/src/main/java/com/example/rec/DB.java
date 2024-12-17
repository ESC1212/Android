package com.example.rec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DB extends SQLiteOpenHelper {
    private static DB instance;

    // Private constructor to prevent direct instantiation
    private DB(Context context) {
        super(context, "JairAutomoveis.db", null, 1);
    }

    // Singleton instance getter
    public static synchronized DB getInstance(Context context) {
        if (instance == null) {
            instance = new DB(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL("CREATE TABLE IF NOT EXISTS Carros (_id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, marca TEXT, Categoria TEXT, Ano INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade if needed
        db.execSQL("DROP TABLE IF EXISTS Carros");
        onCreate(db);
    }
}
