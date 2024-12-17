package com.example.rec;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
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
//        chama o banco de dados
        DB dbHelper = DB.getInstance(this);
        banco = dbHelper.getWritableDatabase();

        listView = findViewById(R.id.listView);

//      função pra preencher a tabela
        preencherListView();

        cadastrar = findViewById(R.id.BtnCadastrar);
//--------------------------------------------------------------------------------------------------Delete--------------------------------------------
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                pega o item da posição selcionada
                String selected = (String) parent.getItemAtPosition(position);
//                joga esse item para uma função de confirmação
                showConfirmationDialog(selected);
                return true;
            }
        });
//        ------------------------------------------------------------------------------------------Update--------------------------------------------

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                Intent i = new Intent(getApplicationContext(), Edit.class);
//                faz um bundle com o modelo do carro para ser usado na proxima tela
                i.putExtra("modelo",selected);
                startActivity(i);
            }
        });
//        ------------------------------------------------------------------------------------------Insert--------------------------------------------
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cadastrar.class);
                startActivity(i);
            }
        });
    }

//    popula o list view com os dados
//        ------------------------------------------------------------------------------------------Select--------------------------------------------
    public void preencherListView() {
        Cursor cursor = banco.rawQuery("SELECT * FROM Carros", null);
//        array list do modelo dos carros
        ArrayList<String> Carros = new ArrayList<>();
//        verifica se tem carros
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String s = cursor.getString(cursor.getColumnIndex("modelo"));
                Carros.add(s);
            } while (cursor.moveToNext());
        }
        cursor.close();

//        bota os carros no listview
        ListView list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                Carros);
        list.setAdapter(adapter);
    }

//    ----------------------------------------------------------------------------------------------Delet(Continuação)---------------------------------
    private void showConfirmationDialog(String selected) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deletar") .setMessage("Você tem certeza que deseja deletar este carro?") .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                banco.delete("Carros","modelo = ?",new String[]{String.valueOf(selected)});
                preencherListView();
            }
        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
