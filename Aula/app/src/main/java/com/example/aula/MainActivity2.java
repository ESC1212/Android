package com.example.aula;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bun = getIntent().getExtras();
        float peso = bun.getFloat("peso");
        float altura = bun.getFloat("altura");
        float imc = peso/(altura * altura);

        TextView txti = findViewById(R.id.IMCtxt);
        txti.setText("IMC: "+String.format("%.2f",imc));
        TextView msg = findViewById(R.id.Mensagen);
        ImageView perfil = findViewById(R.id.perfilPeso);

        if (imc < 18.5){
            msg.setText("Você está abaixo do peso, coma mais.");
            perfil.setImageResource(R.drawable.abaixopeso);
        } else if (imc >= 18.5 && imc < 25){
            msg.setText("Você tem um peso normal, parabéns.");
            perfil.setImageResource(R.drawable.normal);
        } else if (imc >= 25 && imc < 30) {
            msg.setText("Você está sobre o peso, melhore.");
            perfil.setImageResource(R.drawable.sobrepeso);
        } else if (imc >= 30 && imc < 35){
            perfil.setImageResource(R.drawable.obesidade1);
            msg.setText(":(");
        } else if (imc >= 35 && imc <40){
            perfil.setImageResource(R.drawable.obesidade2);
            msg.setText(":(^2");
        } else if (imc >= 40){
            perfil.setImageResource(R.drawable.obesidade3);
            msg.setText(":(^3");
        }
    }
}