package com.example.rec;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cadastrar extends AppCompatActivity {

    private Button cadastrar;
    private EditText modelo;
    private EditText marca;
    private EditText ano;
    private EditText categoria;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        cadastrar = findViewById(R.id.btnCadastrar);
        modelo = findViewById(R.id.editTextModelo);
        marca = findViewById(R.id.editTextMarca);
        ano = findViewById(R.id.editTextAno);
        categoria = findViewById(R.id.editTextCategoria);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                verifica se tudo foi preechido
                if (modelo.getText().toString().isEmpty() || marca.getText().toString().isEmpty() || ano.getText().toString().isEmpty() || categoria.getText().toString().isEmpty()){
                    Toast.makeText(Cadastrar.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    Incert();
                }

            }
        });
    }

    private void Incert(){
//        chama o banco
        DB db = DB.getInstance(this);
        banco = db.getWritableDatabase();

        try{
            ContentValues cv = new ContentValues();
            cv.put("modelo",modelo.getText().toString());
            cv.put("marca",marca.getText().toString());
            cv.put("Ano",Integer.valueOf(ano.getText().toString()));
            cv.put("Categoria",categoria.getText().toString());
            banco.insert("Carros",null, cv);
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } catch (Exception e){
            Toast.makeText(Cadastrar.this, "Ano preechido incorretamente", Toast.LENGTH_SHORT).show();
        }
    }
}
