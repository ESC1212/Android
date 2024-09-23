package com.example.aula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bun = getIntent().getExtras();
        float peso = bun.getFloat("peso");
        float altura = bun.getFloat("altura");
        float imc = peso*(altura * altura);

        TextView txti = findViewById(R.id.IMCtxt);
        txti.setText("IMC: "+String.valueOf(imc));
        TextView msg = findViewById(R.id.Mensagen);

        if (imc < 18.5){
            msg.setText("Você está abaixo do peso, coma mais.");
        } else if (imc >= 18.5 && imc < 25){
            msg.setText("Você tem um peso normal, parabéns.");
        } else if (imc >= 25 && imc < 30) {
            msg.setText("Você está sobre o peso, melhore.");
        } else if (imc >= 30 && imc < 35){
            msg.setText(":(");
        } else if (imc >= 35 && imc <40){
            msg.setText(":(^2");
        } else if (imc >= 40){
            msg.setText(":(^3");
        }
    }
}