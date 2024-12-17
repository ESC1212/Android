package com.example.rec;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase banco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        banco = this.openOrCreateDatabase("JairAutomoveis", getBaseContext().MODE_PRIVATE,null);
        banco.execSQL("CREATE TABLE IF NOT EXISTS Carros (_id INTEGER PRIMARY KEY AUTOINCREMENT,modelo TEXT,marca TEXT, Categoria TEXT, Ano INTEGER)");
    }
    public void insereCarros() {
        ContentValues contentValues = new ContentValues();
//        contentValues.put("nome",etNome.getTexto().toString();
        this.banco.insert("Carros",null,contentValues );
    }
    public void showCarros() {
        Cursor cursor = banco.rawQuery("SELECT * FROM Carros ", null);
        cursor.moveToFirst();
        ArrayList<String> Carros = new ArrayList<>();
        do {
            @SuppressLint("Range") String s = cursor.getString( cursor.getColumnIndex("modelo"));
            Carros.add(s);
        }while (cursor.moveToNext()) ;
        ListView list;
//        list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Carros);
//        list.setAdapter(adapter);
    }
}