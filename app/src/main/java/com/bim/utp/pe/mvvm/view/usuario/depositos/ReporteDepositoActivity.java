package com.bim.utp.pe.mvvm.view.usuario.depositos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadDeposito;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.mvvm.viewmodel.transaccion.enviarDinero.EnviarDineroViewModel;
import com.bim.utp.pe.mvvm.viewmodel.usuario.UsuarioViewModel;
import com.bim.utp.pe.util.Constants;

import java.util.ArrayList;

public class ReporteDepositoActivity extends AppCompatActivity {

    private UsuarioViewModel viewModel;
    private RecyclerView rvDepositos;
    private EditText etCodigo;
    private AdapterDepositos adapterDepositos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_deposito);
        viewModel = ViewModelProviders.of(this).get(UsuarioViewModel.class);
        rvDepositos = findViewById(R.id.rvDepositos);
        etCodigo = findViewById(R.id.etCodigo);

        etCodigo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    if(etCodigo.getText().toString().length()>0){
                        getReporte(Integer.parseInt(etCodigo.getText().toString().trim()));
                    }
                }
                return false;
            }
        });

        rvDepositos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvDepositos.setLayoutManager(layoutManager);
    }


    private void getReporte(int idUsuario){
        viewModel.getReporteDepositos(idUsuario);
        viewModel.setListenerReporteDepositos().observe(this, new Observer<BaseResponse>() {
            @Override
            public void onChanged(BaseResponse baseResponse) {
                if(baseResponse.getEstado().equals(Constants.CODE_SUCCESSFULL)){
                    ArrayList<EntidadDeposito> list = (ArrayList<EntidadDeposito>) ((ResponseService) baseResponse.getData()).Response;
                    if(list!=null){
                        if(list.size()>0){
                            adapterDepositos = new AdapterDepositos(list, ReporteDepositoActivity.this);
                            rvDepositos.setAdapter(adapterDepositos);
                        }else
                            Toast.makeText(ReporteDepositoActivity.this, Constants.ERROR_LISTAR_DEPOSITOS, Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(ReporteDepositoActivity.this, Constants.ERROR_LISTAR_DEPOSITOS, Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(ReporteDepositoActivity.this, baseResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}