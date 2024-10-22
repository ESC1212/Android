package com.example.listagem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] nomes = new String[] {"Cadu", "Douglas", "Jesus"};
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listview);
        DAOPlaneta pdao = new DAOPlaneta();
        PlanetaAdapter padap = new PlanetaAdapter(this,R.layout.item_planeta,pdao.getPlanetas());

        listview.setAdapter(padap);
    }
}