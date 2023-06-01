package com.example.sabadopizzas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sabadopizzas.R;

import java.util.ArrayList;

public class AdapterSabores extends BaseAdapter {
    private Context context;
    private ArrayList<String> lista;

    public AdapterSabores(Context context, ArrayList<String> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_lista_sabores, parent, false);
        }

        String sabor = lista.get(position);
        TextView tvSabor = convertView.findViewById(R.id.tvSabor);

        tvSabor.setText(sabor);
        return convertView;

    }
}