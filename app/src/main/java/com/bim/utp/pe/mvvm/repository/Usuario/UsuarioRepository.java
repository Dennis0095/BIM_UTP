package com.bim.utp.pe.mvvm.repository.Usuario;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;
import com.bim.utp.pe.util.Constants;
import com.bim.utp.pe.util.Util;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository implements IUsuario{

    private MutableLiveData<ResponseRegistroUsuario> usuarioRegistro;
    private ResponseRegistroUsuario responseRegistroUsuario;

    @Override
    public void insertarUsuario(Usuario usuario) {
        responseRegistroUsuario = new ResponseRegistroUsuario();
        usuarioRegistro = new MutableLiveData<>();
        Log.d("LECTOR:", "MOVIL=" + usuario.getIn_movil());
        Log.d("LECTOR:", "DNI=" + usuario.getIn_dni());
        Log.d("LECTOR:", "CODIGO=" + usuario.getIn_codigo());
        Log.d("LECTOR:", "MONTO=" + usuario.getIn_monto());
        Log.d("LECTOR:", "CONTRASENIA=" + usuario.getIn_contrasenia());
        Log.d("LECTOR:", "TIPOUSUARIO=" + usuario.getIn_tipoUsuario_idTipoUsuario());
        Log.d("LECTOR:", "OPERADOR=" + usuario.getIn_operadorMovil_idOperador());
        Log.d("LECTOR:", "ENTIDAD=" + usuario.getIn_entidadFinanciera());

        Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> call = Util.services.Usuarioregister(usuario);
        call.enqueue(new Callback<ResponseService<ArrayList<ResponseRegistroUsuario>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> call, Response<ResponseService<ArrayList<ResponseRegistroUsuario>>> response) {
                if(response.isSuccessful()){
                    responseRegistroUsuario.setEstado(String.valueOf(response.code()));
                    responseRegistroUsuario.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        responseRegistroUsuario = Util.parsearError2(response.errorBody(), responseRegistroUsuario, String.valueOf(response.code()));
                    else
                        responseRegistroUsuario.setMensaje(Constants.ERROR_NO_IDENTIFICADO);
                }
                usuarioRegistro.postValue(responseRegistroUsuario);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> call, Throwable t) {
                responseRegistroUsuario.setEstado(Constants.CODE_ERROR_SERVER);
                responseRegistroUsuario.setMensaje(Constants.ERROR_COMUNICACION);
                usuarioRegistro.postValue(responseRegistroUsuario);
            }
        });
    }

    @Override
    public MutableLiveData<ResponseRegistroUsuario> setListenerUsuarioRegistro() {
        return usuarioRegistro;
    }
}
