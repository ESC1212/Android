package com.example.aula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.buttonact);
        EditText edip = findViewById(R.id.Peso);
        EditText edia = findViewById(R.id.Altura);
        TextView erro = findViewById(R.id.text3000);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity2.class);

                try{
                    float peso = Float.parseFloat(String.valueOf(edip.getText()));
                    float altura =Float.parseFloat(String.valueOf(edia.getText()));
                    i.putExtra("peso", peso);
                    i.putExtra("altura", altura);
                    startActivity(i);
                } catch (Exception e){
                    erro.setText("Erro, tente denovo :(");
                }
            }
        });
    }
}