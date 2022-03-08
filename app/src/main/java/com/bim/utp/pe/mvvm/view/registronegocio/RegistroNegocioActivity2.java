package com.bim.utp.pe.mvvm.view.registronegocio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bim.utp.pe.MainActivity;
import com.bim.utp.pe.R;

public class RegistroNegocioActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_negocio2);
        TextView continuarRegistro = findViewById(R.id.continuarRegistroBimer);

        continuarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroNegocioActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}