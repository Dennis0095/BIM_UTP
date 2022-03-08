package com.bim.utp.pe.mvvm.view.registronegocio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bim.utp.pe.MainActivity;
import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.body.Negocio;
import com.bim.utp.pe.local.model.body.ResponseRegistroBimer;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;
import com.bim.utp.pe.mvvm.view.registro.RegisterActivity;
import com.bim.utp.pe.mvvm.view.registro.RegisterActivity3;
import com.bim.utp.pe.mvvm.view.registro.RegisterActivity4;
import com.bim.utp.pe.mvvm.viewmodel.parametro.BimerViewModel;
import com.bim.utp.pe.mvvm.viewmodel.parametro.UsuarioViewModel;
import com.bim.utp.pe.preferences.UsuarioPreferences;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegistroNegocioActivity extends AppCompatActivity {
    BimerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_negocio);
        ImageView backnegocio = findViewById(R.id.backnegocio);
        Button registroBimer = findViewById(R.id.botonRegistroBimer);
        EditText rucNegocio = findViewById(R.id.rucNegocio);
        EditText txtNombreNegocio = findViewById(R.id.nombreNegocio);
        EditText txtDireccion = findViewById(R.id.direccionNegocio);
        EditText txtNumContac = findViewById(R.id.numeroNegocio);
        EditText txtcontraseniaBimer = findViewById(R.id.contraseniaBimer);
        viewModel = ViewModelProviders.of(this).get(BimerViewModel.class);

        backnegocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroNegocioActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        registroBimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Negocio negocio = new Negocio();
            negocio.setIn_ruc(rucNegocio.getText().toString());
            negocio.setIn_nombreNeg(txtNombreNegocio.getText().toString());
            negocio.setIn_contrasenia(txtcontraseniaBimer.getText().toString());
            negocio.setIn_direccion(txtDireccion.getText().toString());
            negocio.setIn_numeroContacto(txtNumContac.getText().toString());
            // AQUI PONES EL ID DEL USUARIO QUE QUIERES VOLVER BIMER
            negocio.setIn_idUsuario(71);

            if (rucNegocio.getText().toString().length() == 0 || txtNombreNegocio.getText().toString().length() == 0 || txtcontraseniaBimer.getText().toString().length() == 0 ||
                    txtDireccion.getText().toString().length() == 0 || txtNumContac.getText().toString().length() == 0 ){
                new SweetAlertDialog(RegistroNegocioActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Complete todos los campos")
                        .setConfirmText("Ok")
                        .showCancelButton(true)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();
            }else if(rucNegocio.getText().toString().length() != 11){
                new SweetAlertDialog(RegistroNegocioActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Ingrese un RUC correcto porfavor")
                        .setConfirmText("Ok")
                        .showCancelButton(true)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();
            }else if(txtNumContac.getText().toString().length() != 9){
                new SweetAlertDialog(RegistroNegocioActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Ingrese número móvil correcto")
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
                insertarNegocio(negocio);
            }
            }
        });
    }


    public void insertarNegocio(Negocio negocio){
        viewModel.insertarBimer(negocio);
        viewModel.setListenerNegocioRegister().observe(this, baseResponse ->{
        viewModel.verifyResponse(baseResponse);
        } );
        viewModel.setRegistronegocio().observe(this, new Observer<ArrayList<ResponseRegistroBimer>>() {
            @Override
            public void onChanged(ArrayList<ResponseRegistroBimer> responseRegistroUsuarios) {
                List<String> list = new ArrayList<>();
                for (ResponseRegistroBimer negocio : responseRegistroUsuarios) {
                    list.add(negocio.getEstado());
                    list.add(negocio.getMensaje());
                }
                if(Integer.parseInt(list.get(0))==1){
                    Intent intent = new Intent(RegistroNegocioActivity.this, RegistroNegocioActivity2.class);
                    startActivity(intent);
                }else{
                    new SweetAlertDialog(RegistroNegocioActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Contraseña incorrecta")
                            .setConfirmText("Ok")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();
                }
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(RegistroNegocioActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show());
    }
}