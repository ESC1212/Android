package com.example.rec;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastrar extends AppCompatActivity {
    private Button cadastrar;
    private EditText modelo;
    private EditText marca;
    private EditText ano;
    private EditText categoria;
    private SQLiteDatabase banco;

    private void Incert(){
        DB dbHelper = DB.getInstance(this);
        banco = dbHelper.getWritableDatabase();
        cadastrar = findViewById(R.id.BtnCadastrar);
        modelo = findViewById(R.id.editTextModelo);
        marca = findViewById(R.id.editTextMarca);
        ano = findViewById(R.id.editTextAno);
        categoria = findViewById(R.id.editTextCategoria);
        ContentValues cv = new ContentValues();
        cv.put("modelo",modelo.getText().toString());
        cv.put("marca",marca.getText().toString());
        cv.put("Ano",Integer.valueOf(ano.getText().toString()));
        cv.put("Categoria",categoria.getText().toString());
        banco.insert("Carros",null, cv);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
