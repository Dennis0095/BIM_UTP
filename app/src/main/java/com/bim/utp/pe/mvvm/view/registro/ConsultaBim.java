package com.bim.utp.pe.mvvm.view.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bim.utp.pe.MainActivity;
import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.body.ResponseConsultaBim;
import com.bim.utp.pe.mvvm.view.registronegocio.RegistroNegocioActivity;
import com.bim.utp.pe.mvvm.viewmodel.parametro.ConsultaBimViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConsultaBim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_bim);
        ImageView backperfil = findViewById(R.id.backperfil);
        ResponseConsultaBim idusuario = new ResponseConsultaBim();
        idusuario.setIn_idUsuario(71);
        ConsultarPerfilBim(idusuario);

        backperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultaBim.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ConsultarPerfilBim(ResponseConsultaBim idUsuario){
        final ConsultaBimViewModel viewModel = ViewModelProviders.of(this).get(ConsultaBimViewModel.class);
        TextView txtperfilmovilusuario = (TextView)findViewById(R.id.celularUsuario);
        TextView txtbancousuario = (TextView)findViewById(R.id.bancoUsuario);
        TextView txtoperadorusuario = (TextView)findViewById(R.id.operadorUsuario);
        TextView txtcorreousuario = (TextView)findViewById(R.id.correousuario);
        viewModel.getConsultaUsuario(idUsuario);
        viewModel.setListenerConsultaUsuario().observe(this, baseResponse ->{
            viewModel.verifyResponse(baseResponse);
        } );
        viewModel.setConsultaBim().observe(this, new Observer<ArrayList<ResponseConsultaBim>>() {
            @Override
            public void onChanged(ArrayList<ResponseConsultaBim> responseConsultaBim) {
                List<String> list = new ArrayList<>();
                for (ResponseConsultaBim datosPerfil : responseConsultaBim) {
                    list.add(datosPerfil.getMovil());
                    list.add(datosPerfil.getCorreo());
                    list.add(datosPerfil.getOperadorDesc());
                    list.add(datosPerfil.getEntidadDesc());
                }
                txtperfilmovilusuario.setText(list.get(0));
                if(list.get(1)==null){
                    txtcorreousuario.setText("---");
                }else{
                    txtcorreousuario.setText(list.get(1));
                }
                txtbancousuario.setText(list.get(2).toUpperCase());
                txtoperadorusuario.setText(list.get(3).toUpperCase());

            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(ConsultaBim.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show());
    }
}