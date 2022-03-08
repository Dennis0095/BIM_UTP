package com.bim.utp.pe.mvvm.view.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bim.utp.pe.MainActivity;
import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.body.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step4);
        TextView seguirdspregistro = (TextView) findViewById(R.id.seguirdspregistro);
        seguirdspregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity4.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}