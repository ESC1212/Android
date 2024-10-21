package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase DB;
    EditText txtNome,txtEmail,txtDataN;
    Button cadastrar;
    ListView listV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase DB = openOrCreateDatabase("banco", getBaseContext().MODE_PRIVATE,null);
        DB.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT,nome varchar, email varchar,datan DATE)");
        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtDataN = findViewById(R.id.txtDataN);
        cadastrar = findViewById(R.id.btnCadastrar);
        listV = findViewById(R.id.listV);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtNome.getText().toString();
                String email = txtEmail.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("nome", nome);
                cv.put("email", email);

                long status = DB.insert("pessoas", null,cv);
                if(status > 0){
                    Toast.makeText(getApplicationContext(),"usuario inserido",Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getApplicationContext(),"Erro",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void loadData(){
        DB.rawQuery("SELECT * FROM pessoas;",);
    }
}