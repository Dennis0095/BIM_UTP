package com.bim.utp.pe.mvvm.view.registro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.mvvm.viewmodel.parametro.ParametroViewModel;
import com.bim.utp.pe.preferences.UsuarioPreferences;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity2 extends AppCompatActivity {
    ParametroViewModel viewModel;
    UsuarioPreferences usuarioPreferences = UsuarioPreferences.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step2);
        Button boton = findViewById(R.id.boton2);
        ImageView imagenretroceso = findViewById(R.id.retroceso1);

        viewModel = ViewModelProviders.of(this).get(ParametroViewModel.class);
        TextView txtDNI = (TextView) findViewById(R.id.dniUsuario);
        TextView txtCodDNI = (TextView) findViewById(R.id.codDNIUsuario);
        Spinner spinnerOperador = (Spinner) findViewById(R.id.spinnerOperadores);
        Spinner spinnerEntidad = (Spinner) findViewById(R.id.spinnerEntidades);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtDNI.getText().toString().length()== 0 || txtCodDNI.getText().toString().length()==0){
                    new SweetAlertDialog(RegisterActivity2.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Complete los campos requeridos")
                            .setConfirmText("Ok")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();
                }else if ( txtDNI.getText().toString().length()!=8){
                        new SweetAlertDialog(RegisterActivity2.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Ingrese un DNI correcto")
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
                    usuarioPreferences.guardarDNIUsuario(RegisterActivity2.this,txtDNI.getText().toString());
                    usuarioPreferences.guardarcodDNIUsuario(RegisterActivity2.this,txtCodDNI.getText().toString());
                    usuarioPreferences.guardarOperadorUsuario(RegisterActivity2.this,spinnerOperador.getSelectedItemPosition()+1);
                    usuarioPreferences.guardarEntidadUsuario(RegisterActivity2.this,spinnerEntidad.getSelectedItemPosition()+1);
                    Intent intent = new Intent(RegisterActivity2.this,RegisterActivity3.class);
                    startActivity(intent);
                }
            }
        });
        getEntidades();
        imagenretroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity2.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getEntidades(){
        Spinner spinnere = findViewById(R.id.spinnerEntidades);
        viewModel.getEntidadesFinancieras();
        viewModel.setListenerEntidadesFinancieras().observe(this, baseResponse ->{
            viewModel.verifyResponse(baseResponse);
            get0peradores();
        } );
        viewModel.setEntidadFinanciera().observe(this, new Observer<ArrayList<EntidadFinanciera>>() {
            @Override
            public void onChanged(ArrayList<EntidadFinanciera> entidadFinancieras) {
                List<String> list = new ArrayList<>();
                for(EntidadFinanciera entidad: entidadFinancieras){
                    //Log.d("DMA_LECTOR", " entidad = " + entidad.getDescripcion());
                    list.add(entidad.getDescripcion());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterActivity2.this, android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnere.setAdapter(dataAdapter);
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(RegisterActivity2.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show());
    }

    public void get0peradores(){
        Spinner spinnero = findViewById(R.id.spinnerOperadores);
        viewModel.getOperadoresMoviles();
        viewModel.setListenerOperadoresMoviles().observe(this, baseResponse -> viewModel.verifyResponse2(baseResponse));
        viewModel.setOperadorMovil().observe(this, new Observer<ArrayList<OperadorMovil>>() {
            @Override
            public void onChanged(ArrayList<OperadorMovil> operadoresMoviles) {
                List<String> list = new ArrayList<>();
                for(OperadorMovil operador: operadoresMoviles){
                    list.add(operador.getDescripcion());
                    //Log.d("DMA_LECTOR", " operador = " + operador.getDescripcion());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterActivity2.this, android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnero.setAdapter(dataAdapter);
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(RegisterActivity2.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show());
    }
}