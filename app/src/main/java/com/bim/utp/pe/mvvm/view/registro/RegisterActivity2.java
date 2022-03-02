package com.bim.utp.pe.mvvm.view.registro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;

import com.bim.utp.pe.MainActivity;
import com.bim.utp.pe.R;
import com.bim.utp.pe.databinding.ActivityMainBinding;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.mvvm.viewmodel.parametro.ParametroViewModel;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity2 extends AppCompatActivity {
    ParametroViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step2);
        Button boton = findViewById(R.id.boton2);
        viewModel = ViewModelProviders.of(this).get(ParametroViewModel.class);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity2.this,RegisterActivity3.class);
                startActivity(intent);
            }
        });
        getEntidades();
        get0peradores();
    }

    public void getEntidades(){
        Spinner spinnere = findViewById(R.id.spinnerEntidades);
        viewModel.getEntidadesFinancieras();
        viewModel.setListenerEntidadesFinancieras().observe(this, baseResponse -> viewModel.verifyResponse(baseResponse));
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