package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase DB;
    EditText Nome, Data, Email;
    Button Cadastrar;
    Button Clear;
    ListView list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nome = findViewById(R.id.txtNome);
        Email = findViewById(R.id.txtEmail);
        Data = findViewById(R.id.txtDataN);
        Cadastrar =findViewById(R.id.btnCadastrar);
        list = findViewById(R.id.listview);
        Clear = findViewById(R.id.btnClear);

        DB = openOrCreateDatabase("banco",MODE_PRIVATE, null);
        DB.execSQL("CREATE TABLE if not exists pessoas (id  INTEGER PRIMARY KEY autoincrement,nome varchar, email varchar, dtnsc date)");

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear();
            }
        });

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nome = Nome.getText().toString();
                String email = Email.getText().toString();
                String data = Data.getText().toString();

                ContentValues cv = new ContentValues();
                cv.put("nome",nome);
                cv.put("email",email);

                long status = DB.insert("pessoas",null,cv);

                if(status>0){
                    Toast.makeText(MainActivity.this, "certo", Toast.LENGTH_SHORT).show();
                    Clear();
                } else{
                    Toast.makeText(MainActivity.this, "Erro", Toast.LENGTH_SHORT).show();
                }
                LoadData();
            }
        });
        list.getOnItemClickListener();



        LoadData();
    }

    public void LoadData(){
        Cursor cursor = DB.rawQuery("SELECT * FROM pessoas",null);
        cursor.moveToFirst();
        ArrayList<String> nomes = new ArrayList<>();
        while(!cursor.isAfterLast())
        {
            nomes.add(cursor.getString(1));
            cursor.moveToNext();
        }
        ArrayAdapter<String> adap = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,nomes);
        list.setAdapter(adap);
    }
    public void Clear(){
        Nome = findViewById(R.id.txtNome);
        Email = findViewById(R.id.txtEmail);
        Data = findViewById(R.id.txtDataN);
        Nome.setText(null);
        Email.setText(null);
        Data.setText(null);
    }

}