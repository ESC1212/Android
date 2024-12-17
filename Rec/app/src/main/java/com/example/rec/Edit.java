package com.example.rec;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Edit extends AppCompatActivity {

    private Button editar;
    private EditText modelo;
    private EditText marca;
    private EditText ano;
    private EditText categoria;
    private SQLiteDatabase banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editar = findViewById(R.id.btnCadastrar);
        modelo = findViewById(R.id.editTextModelo);
        marca = findViewById(R.id.editTextMarca);
        ano = findViewById(R.id.editTextAno);
        categoria = findViewById(R.id.editTextCategoria);

//        Pegando o banco denovo
        DB db = DB.getInstance(this);
        banco = db.getWritableDatabase();

//        pegando os itens do bundle
        Bundle bun = getIntent().getExtras();

//        enviando o bundle como parametro para poder peencher os campos
        preencherCampos(bun);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                verifica se todos os campos foram preechidos
                if (modelo.getText().toString().isEmpty() || marca.getText().toString().isEmpty() || ano.getText().toString().isEmpty() || categoria.getText().toString().isEmpty()){
                    Toast.makeText(Edit.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
//                    manda o modelo do carro pro updade fazer um query, eu poderia ter feito ele mandar o id, mas dai eu teria que mudar muita coisa na tabela
//                    fazer como aquele aplicativo dos planes
//                    fiquei com um pouco de preguiça de fazer isso
                    String nome = bun.getString("modelo");
                    Update(nome);
                }

            }
        });
    }

//    preeche os campos com os valores corretos
    private void preencherCampos(Bundle bun) {
        Cursor cursor = banco.rawQuery("Select * from Carros where modelo = ? ",new String[]{String.valueOf(bun.getString("modelo"))});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String m = cursor.getString(cursor.getColumnIndex("modelo"));
            modelo.setText(m);
            @SuppressLint("Range") String mm = cursor.getString(cursor.getColumnIndex("marca"));
            marca.setText(mm);
            @SuppressLint("Range") String a = cursor.getString(cursor.getColumnIndex("Ano"));
            ano.setText(a);
            @SuppressLint("Range") String c = cursor.getString(cursor.getColumnIndex("Categoria"));
            categoria.setText(c);
        }
    }

//          quase a mesma coisa do insert
    private void Update(String nome){
        try{
            ContentValues cv = new ContentValues();
            cv.put("modelo",modelo.getText().toString());
            cv.put("marca",marca.getText().toString());
            cv.put("Ano",Integer.valueOf(ano.getText().toString()));
            cv.put("Categoria",categoria.getText().toString());
//            só muda que tem o where agora.
            banco.update("Carros", cv, "modelo = ?", new String[]{String.valueOf(nome)});

//            volta pra tela inicial
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
//            tecnicamente só é pra dar erro quando o ano é preenchido incorretamente
//            então foi assim que eu fiz
        } catch (Exception e){
            Toast.makeText(Edit.this, "Ano preechido incorretamente", Toast.LENGTH_SHORT).show();
        }
    }
}
