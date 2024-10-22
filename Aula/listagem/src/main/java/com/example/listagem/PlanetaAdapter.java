package com.example.listagem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlanetaAdapter extends ArrayAdapter<Planeta> {

    Context mContext;
    Integer mRes;
    List<Planeta> mListPlaneta;

    public PlanetaAdapter(@NonNull Context context, int resource, @NonNull List<Planeta> objects) {
        super(context, resource, objects);
        mContext =context;
        mRes=resource;
        mListPlaneta =objects;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View v = layoutInflater.inflate(mRes,parent,false);

        TextView tvnomeplaneta = v.findViewById(R.id.nome);
        ImageView imageview = v.findViewById(R.id.imageView);

        Planeta planeta = mListPlaneta.get(position);
        tvnomeplaneta.setText(planeta.nome);
        imageview.setImageResource(planeta.integer);

        return v;
    }
}