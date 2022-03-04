package com.bim.utp.pe.mvvm.view.registro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;
import com.bim.utp.pe.mvvm.viewmodel.parametro.UsuarioViewModel;
import com.bim.utp.pe.preferences.UsuarioPreferences;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity3 extends AppCompatActivity {
    UsuarioViewModel viewModel;
    UsuarioPreferences usuarioPreferences = UsuarioPreferences.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step3);
        Button boton = findViewById(R.id.boton3);
        ImageView imagenretroceso = findViewById(R.id.retroceso2);


        TextView txtContrasenia1 = (TextView) findViewById(R.id.contraseniaUsuario);
        TextView txtContrasenia2 = (TextView) findViewById(R.id.contrasenia2Usuario);
        CheckBox check = (CheckBox) findViewById(R.id.check);

        viewModel = ViewModelProviders.of(this).get(UsuarioViewModel.class);

        Usuario usuario = new Usuario();
        usuario.setIn_movil(usuarioPreferences.recuperarMovilUsuario(RegisterActivity3.this));
        usuario.setIn_dni(usuarioPreferences.recuperarDNIUsuario(RegisterActivity3.this));
        usuario.setIn_codigo(usuarioPreferences.recuperarcodDNIUsuario(RegisterActivity3.this));
        usuario.setIn_contrasenia(usuarioPreferences.recuperarContraseniaUsuario(RegisterActivity3.this));
        usuario.setIn_tipoUsuario_idTipoUsuario(1);
        usuario.setIn_monto("100.00");
        usuario.setIn_operadorMovil_idOperador(usuarioPreferences.recuperarOperadorUsuario(RegisterActivity3.this));
        usuario.setIn_entidadFinanciera(usuarioPreferences.recuperarEntidadUsuario(RegisterActivity3.this));

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContrasenia1.getText().toString().length()==0 || txtContrasenia2.getText().toString().length()==0 ){
                    new SweetAlertDialog(RegisterActivity3.this, SweetAlertDialog.ERROR_TYPE)
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
                } else if(!txtContrasenia1.getText().toString().equals(txtContrasenia2.getText().toString())){
                    new SweetAlertDialog(RegisterActivity3.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Las contraseñas no coinciden")
                            .setConfirmText("Ok")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.cancel();
                                }
                            })
                            .show();
                }else if(!check.isChecked()){
                    new SweetAlertDialog(RegisterActivity3.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Acepte los términos y condiciones")
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
                    usuarioPreferences.guardarContraseniaUsuario(RegisterActivity3.this,txtContrasenia1.getText().toString());
                    insertUsuario(usuario);
                }
            }
        });

        imagenretroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity3.this,RegisterActivity2.class);
                startActivity(intent);
            }
        });
    }

    public void insertUsuario(Usuario usuario){
        viewModel.insertarUsuario(usuario);
        viewModel.setListenerUsuarioRegistro().observe(this, baseResponse ->{
            viewModel.verifyResponse(baseResponse);
        } );
        viewModel.setRegistroUsuario().observe(this, new Observer<ArrayList<ResponseRegistroUsuario>>() {
            @Override
            public void onChanged(ArrayList<ResponseRegistroUsuario> responseRegistroUsuarios) {
                List<String> list = new ArrayList<>();
                for (ResponseRegistroUsuario entidad : responseRegistroUsuarios) {
                    list.add(entidad.getEstado());
                    list.add(entidad.getMensaje());
                }
                if(Integer.parseInt(list.get(0))==1){
                    Intent intent = new Intent(RegisterActivity3.this,RegisterActivity4.class);
                    startActivity(intent);
                    //usuarioPreferences.limpiardatosUsuario(RegisterActivity3.this);
                }else{
                    new SweetAlertDialog(RegisterActivity3.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(list.get(1))
                            .setConfirmText("Ok")
                            .showCancelButton(true)
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                   Intent intent = new Intent(RegisterActivity3.this,RegisterActivity.class);
                                   startActivity(intent);
                                }
                            })
                            .show();
                }
                }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(RegisterActivity3.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show());
}

}