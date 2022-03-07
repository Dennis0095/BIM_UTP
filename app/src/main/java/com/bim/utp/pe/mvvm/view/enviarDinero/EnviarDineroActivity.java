package com.bim.utp.pe.mvvm.view.enviarDinero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bim.utp.pe.R;
import com.bim.utp.pe.databinding.ActivityEnviarDineroBinding;
import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.EnviarDinero;
import com.bim.utp.pe.local.model.body.ResponseGeneral;
import com.bim.utp.pe.mvvm.viewmodel.parametro.ParametroViewModel;
import com.bim.utp.pe.mvvm.viewmodel.transaccion.enviarDinero.EnviarDineroViewModel;

import java.util.ArrayList;

public class EnviarDineroActivity extends AppCompatActivity {

    private ActivityEnviarDineroBinding binding;
    private EnviarDinero enviarDinero;
    private EnviarDineroViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnviarDineroBinding.inflate(getLayoutInflater());
        View view_ = binding.getRoot();
        setContentView(view_);
        enviarDinero = new EnviarDinero();
        enviarDinero.setIdUsuario("18");
        enviarDinero.setTipoTransaccion("1");

        viewModel = ViewModelProviders.of(this).get(EnviarDineroViewModel.class);
        onclikButtons();
    }

    private void onclikButtons(){
        binding.tvContinuar.setOnClickListener(view -> {
            if(binding.etCelular.getText().toString().trim().length()<9){
                return;
            }

            if(binding.etMonto.getText().toString().trim().length()==0){
                return;
            }
            enviarDinero.setCelularDestino(binding.etCelular.getText().toString());
            enviarDinero.setMonto(binding.etMonto.getText().toString());
            enviarDinero.setMensaje(binding.etMotivo.getText().toString());
            showStep2();

            // STEP 2
            binding.tvMonto.setText("S/ " + enviarDinero.getMonto());
            binding.tvTelefonoDestino.setText(enviarDinero.getCelularDestino());

            binding.tieContrasenia.setText("");
        });

        binding.tvVolver.setOnClickListener(view -> {
            showStep1();
        });

        binding.tvContinuar2.setOnClickListener(view -> {
            if(binding.tieContrasenia.getText().toString().trim().length()==0){
                return;
            }
            enviarDinero.setContrasenia(binding.tieContrasenia.getText().toString());
            viewModel.setEnviarDinero(enviarDinero);
            viewModel.setListenerEnviarDinero().observe(this, new Observer<BaseResponse>() {
                @Override
                public void onChanged(BaseResponse baseResponse) {
                    viewModel.verifyResponse(baseResponse);
                }
            });
            viewModel.getResponseEnvioDinero().observe(this, new Observer<ArrayList<ResponseGeneral>>() {
                @Override
                public void onChanged(ArrayList<ResponseGeneral> responseGenerals) {
                    if(responseGenerals.size()>0){
                        ResponseGeneral response = responseGenerals.get(0);
                        if(response.getEstado().equals("1")){// SUCCESSFULL
                            String[] aMensaje = response.getMensaje().split("&");
                            Toast.makeText(EnviarDineroActivity.this, aMensaje[0], Toast.LENGTH_LONG).show();
                            binding.tvFecha2.setText(aMensaje[1]);
                            binding.tvMontoFinal.setText("S/ " + enviarDinero.getMonto());
                            binding.tvTelefonoDestino2.setText(enviarDinero.getCelularDestino());
                            showStep3();
                        }else{// ERROR
                            Toast.makeText(EnviarDineroActivity.this, response.getMensaje(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        });

        binding.tvFinalizan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void showStep1(){
        binding.tvUno.setBackground(getResources().getDrawable(R.drawable.circulo_active));
        binding.tvDos.setBackground(getResources().getDrawable(R.drawable.circulo_disabled));;
        binding.tvTres.setBackground(getResources().getDrawable(R.drawable.circulo_disabled));;
        binding.lineaStep2.setBackgroundColor(getResources().getColor(R.color.fondoCirculo));
        binding.lineaStep3.setBackgroundColor(getResources().getColor(R.color.fondoCirculo));

        binding.clStep3.setVisibility(View.GONE);
        binding.clStep2.setVisibility(View.GONE);
        binding.clStep1.setVisibility(View.VISIBLE);
    }
    private void showStep2(){
        binding.tvDos.setBackground(getResources().getDrawable(R.drawable.circulo_active));;
        binding.tvTres.setBackground(getResources().getDrawable(R.drawable.circulo_disabled));;
        binding.lineaStep2.setBackgroundColor(getResources().getColor(R.color.colorBtn2));
        binding.lineaStep3.setBackgroundColor(getResources().getColor(R.color.fondoCirculo));

        binding.clStep1.setVisibility(View.GONE);
        binding.clStep3.setVisibility(View.GONE);
        binding.clStep2.setVisibility(View.VISIBLE);
    }
    private void showStep3(){
        binding.tvTres.setBackground(getResources().getDrawable(R.drawable.circulo_active));;
        binding.lineaStep3.setBackgroundColor(getResources().getColor(R.color.colorBtn2));

        binding.clStep2.setVisibility(View.GONE);
        binding.clStep1.setVisibility(View.GONE);
        binding.clStep3.setVisibility(View.VISIBLE);
    }
}