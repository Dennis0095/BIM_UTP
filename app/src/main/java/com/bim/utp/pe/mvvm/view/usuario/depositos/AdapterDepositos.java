package com.bim.utp.pe.mvvm.view.usuario.depositos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.body.EntidadDeposito;


import java.util.ArrayList;
import java.util.ArrayList;

public class AdapterDepositos extends RecyclerView.Adapter<AdapterDepositos.ViewHolder>{

    private ArrayList<EntidadDeposito> aDeposito;
    private Context ctx;

    public AdapterDepositos(ArrayList<EntidadDeposito> aDeposito, Context ctx){
        this.aDeposito = aDeposito;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.lauyout_item_deposito, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final @NonNull ViewHolder v, final int i) {
        if (i%2==0){
            v.clDeposito.setBackgroundColor(ctx.getResources().getColor(R.color.colorDeposito));
        }else{
            v.clDeposito.setBackgroundColor(ctx.getResources().getColor(R.color.white));
        }
        final EntidadDeposito obj = aDeposito.get(i);
        v.tvCodigo.setText(obj.getIdDeposito());
        v.tvMonto.setText("S/ " + obj.getMonto());
        v.tvFecha.setText(obj.getFecha());
    }

    @Override
    public int getItemCount() {
        return aDeposito.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCodigo;
        private TextView tvMonto;
        private TextView tvFecha;
        private ConstraintLayout clDeposito;

        public ViewHolder(@NonNull View v) {
            super(v);
            tvFecha = v.findViewById(R.id.tvFecha);
            tvMonto = v.findViewById(R.id.tvMonto);
            tvCodigo = v.findViewById(R.id.tvCodigo);
            clDeposito = v.findViewById(R.id.clDeposito);
        }
    }
}

