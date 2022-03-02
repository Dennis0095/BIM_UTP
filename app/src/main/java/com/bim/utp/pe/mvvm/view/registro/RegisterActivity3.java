package com.bim.utp.pe.mvvm.view.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bim.utp.pe.R;

public class RegisterActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step3);
        Button boton = findViewById(R.id.boton3);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity3.this,RegisterActivity4.class);
                startActivity(intent);
            }
        });

    }
}