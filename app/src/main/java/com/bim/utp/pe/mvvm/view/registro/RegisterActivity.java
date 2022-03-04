package com.bim.utp.pe.mvvm.view.registro;

import androidx.appcompat.app.AppCompatActivity;
import com.bim.utp.pe.R;
import com.bim.utp.pe.preferences.UsuarioPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends AppCompatActivity {
    UsuarioPreferences usuarioPreferences = UsuarioPreferences.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step1);
        LinearLayout boton = findViewById(R.id.boton1);
        TextView txtMovil = (TextView) findViewById(R.id.numeroUsuario);
        String movilguardado = usuarioPreferences.recuperarMovilUsuario(RegisterActivity.this);
        txtMovil.setText(movilguardado);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMovil.getText().toString().length()==0){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Complete el campo requerido")
                            .setConfirmText("Ok")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();
                }else if(txtMovil.getText().length()!= 9){
                    new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Ingrese un número móvil válido")
                            .setConfirmText("Ok")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();
                }else{
                    usuarioPreferences.guardarMovilUsuario(RegisterActivity.this,txtMovil.getText().toString());
                    Intent intent = new Intent(RegisterActivity.this,RegisterActivity2.class);
                    startActivity(intent);
                }
            }
        });

    }
}