package com.example.rec;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase banco;
    private ListView listView;
    private Button cadastrar;
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
        DB dbHelper = DB.getInstance(this);
        banco = dbHelper.getWritableDatabase();

        insertCarros();
        showCarros();

        cadastrar = findViewById(R.id.BtnCadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Cadastrar.class);
                startActivity(i);
            }
        });
    }
    public void insereCarros() {
        ContentValues contentValues = new ContentValues();
        this.banco.insert("Carros",null,contentValues );
    }
    public void showCarros() {
        Cursor cursor = banco.rawQuery("SELECT * FROM Carros", null);

        ArrayList<String> Carros = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String s = cursor.getString(cursor.getColumnIndex("modelo"));
                Carros.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ListView list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Carros);
        list.setAdapter(adapter);
    }
    public void insertCarros() {
        // Creating ContentValues object for the first car
        ContentValues car1 = new ContentValues();
        car1.put("modelo", "Model X");
        car1.put("marca", "Tesla");
        car1.put("Categoria", "SUV");
        car1.put("Ano", 2021);

        // Inserting the first car into the database
        this.banco.insert("Carros", null, car1);

        // Creating ContentValues object for the second car
        ContentValues car2 = new ContentValues();
        car2.put("modelo", "Civic");
        car2.put("marca", "Honda");
        car2.put("Categoria", "Sedan");
        car2.put("Ano", 2019);

        // Inserting the second car into the database
        this.banco.insert("Carros", null, car2);
    }
}