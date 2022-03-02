package com.bim.utp.pe.mvvm.view.registro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bim.utp.pe.R;

public class RegisterActivity4 extends AppCompatActivity {
    private LinearLayout boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step4);
        boton = findViewById(R.id.boton1);
    }
}