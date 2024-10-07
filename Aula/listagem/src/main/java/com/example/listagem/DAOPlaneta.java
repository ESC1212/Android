package com.example.listagem;

import java.util.ArrayList;

public class DAOPlaneta {
    ArrayList <Planeta> planetas;

    public DAOPlaneta() {
        planetas = new ArrayList<>();
        planetas.add(new Planeta("Mercurio", R.drawable.mercury));
        planetas.add(new Planeta("Jupiter", R.drawable.jupter));
        planetas.add(new Planeta("Terra", R.drawable.earth));
        planetas.add(new Planeta("Marte", R.drawable.mars));
    }
    public ArrayList<Planeta> getPlanetas(){
        return planetas;
    }
}
