package com.example.db;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase DB;
    EditText txtNome,txtEmail,txtDataN;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase DB = openOrCreateDatabase("banco", getBaseContext().MODE_PRIVATE,null);
        DB.execSQL("CREATE TABLE pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar, email varchar,datan DATE)");
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtDataN = findViewById(R.id.txtDataN);
    }
}