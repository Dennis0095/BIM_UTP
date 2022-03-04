package com.bim.utp.pe.mvvm.view.registro;

import androidx.appcompat.app.AppCompatActivity;
import com.bim.utp.pe.R;
import com.bim.utp.pe.preferences.UsuarioPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
public class RegisterActivity extends AppCompatActivity {
    UsuarioPreferences usuarioPreferences = UsuarioPreferences.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step1);
        LinearLayout boton = findViewById(R.id.boton1);
        TextView txtMovil = (TextView) findViewById(R.id.numeroUsuario);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuarioPreferences.guardarMovilUsuario(RegisterActivity.this,txtMovil.getText().toString());
                Intent intent = new Intent(RegisterActivity.this,RegisterActivity2.class);
                startActivity(intent);
            }
        });

    }
}