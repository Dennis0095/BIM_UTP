package com.bim.utp.pe.mvvm.view.registro;

import androidx.appcompat.app.AppCompatActivity;
import com.bim.utp.pe.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step1);
        LinearLayout boton = findViewById(R.id.boton1);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,RegisterActivity2.class);
                startActivity(intent);
            }
        });

    }
}