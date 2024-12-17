package com.example.rec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// classe pra poder chamar o banco em multiplas telas diferentes
public class DB extends SQLiteOpenHelper {
    private static DB instance;

//    inicia o banco
    private DB(Context context) {
        super(context, "JairAutomoveis.db", null, 1);
    }

//    para criar o bando em outras telas
    public static synchronized DB getInstance(Context context) {
        if (instance == null) {
            instance = new DB(context.getApplicationContext());
        }
        return instance;
    }

//    cria a unica tabela no banco
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Carros (_id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, marca TEXT, Categoria TEXT, Ano INTEGER)");
    }

//    precisava ter essa função, se não dava erro.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Carros");
        onCreate(db);
    }
}
