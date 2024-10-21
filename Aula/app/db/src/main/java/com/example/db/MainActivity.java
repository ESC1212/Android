package com.example.db;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase DB = openOrCreateDatabase("banco", getBaseContext().MODE_PRIVATE,null);
        DB.execSQL("CREATE TABLE pessoas (id INT PRIMARY KEY AUTOINCREMENT,nome varchar, email varchar,datan DATE)");
    }
}