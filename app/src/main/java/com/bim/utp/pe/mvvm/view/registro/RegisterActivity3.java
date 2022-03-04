package com.bim.utp.pe.mvvm.view.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bim.utp.pe.R;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;
import com.bim.utp.pe.mvvm.viewmodel.parametro.ParametroViewModel;
import com.bim.utp.pe.mvvm.viewmodel.parametro.UsuarioViewModel;
import com.bim.utp.pe.preferences.UsuarioPreferences;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity3 extends AppCompatActivity {
    UsuarioViewModel viewModel;
    UsuarioPreferences usuarioPreferences = UsuarioPreferences.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step3);
        Button boton = findViewById(R.id.boton3);

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
                if(!txtContrasenia1.getText().toString().equals(txtContrasenia2.getText().toString())){
                    Toast.makeText(RegisterActivity3.this , "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }else if(!check.isChecked()){
                    Toast.makeText(RegisterActivity3.this , "Acepte los términos y condiciones", Toast.LENGTH_SHORT).show();
                }else{
                    usuarioPreferences.guardarContraseniaUsuario(RegisterActivity3.this,txtContrasenia1.getText().toString());
                    /*String dato1 = usuarioPreferences.recuperarMovilUsuario(RegisterActivity3.this);
                    String dato2 = usuarioPreferences.recuperarDNIUsuario(RegisterActivity3.this);
                    String dato3 = usuarioPreferences.recuperarcodDNIUsuario(RegisterActivity3.this);
                    int dato4 = usuarioPreferences.recuperarEntidadUsuario(RegisterActivity3.this);
                    int dato5 = usuarioPreferences.recuperarOperadorUsuario(RegisterActivity3.this);
                    String dato6 = usuarioPreferences.recuperarContraseniaUsuario(RegisterActivity3.this);
                    Toast.makeText(RegisterActivity3.this , "movil: "+dato1+
                            "DNI: "+dato2+
                            "codDNI: "+dato3+
                            "ENTIDAD: "+dato4+
                            "OPERADOR: "+dato5+
                            "CONTRASENIA: "+dato6, Toast.LENGTH_SHORT).show();*/
                    insertUsuario(usuario);
                }
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
                    //Log.d("DMA_LECTOR", " entidad = " + entidad.getDescripcion());
                    list.add(entidad.getEstado());
                }
            }
        });
        viewModel.setError().observe(this, baseResponse -> Toast.makeText(RegisterActivity3.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show());
}

}