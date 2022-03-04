package com.bim.utp.pe.mvvm.repository.Usuario;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;
import com.bim.utp.pe.util.Constants;
import com.bim.utp.pe.util.Util;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class UsuarioRepository implements IUsuario{

    private MutableLiveData<BaseResponse> usuarioRegistro;

    private BaseResponse baseResponse;


    @Override
    public void insertarUsuario(Usuario usuario) {
        baseResponse = new BaseResponse();
        usuarioRegistro = new MutableLiveData<>();
        Log.d("LECTOR:", "MOVIL=" + usuario.getIn_movil());
        Log.d("LECTOR:", "DNI=" + usuario.getIn_dni());
        Log.d("LECTOR:", "CODIGO=" + usuario.getIn_codigo());
        Log.d("LECTOR:", "MONTO=" + usuario.getIn_monto());
        Log.d("LECTOR:", "CONTRASENIA=" + usuario.getIn_contrasenia());
        Log.d("LECTOR:", "TIPOUSUARIO=" + usuario.getIn_tipoUsuario_idTipoUsuario());
        Log.d("LECTOR:", "OPERADOR=" + usuario.getIn_operadorMovil_idOperador());
        Log.d("LECTOR:", "ENTIDAD=" + usuario.getIn_entidadFinanciera());

        Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> call = Util.services.Usuarioregister2(usuario.getIn_movil(), usuario.getIn_contrasenia(), usuario.getIn_dni(), usuario.getIn_codigo(),
                usuario.getIn_monto(), String.valueOf(usuario.getIn_operadorMovil_idOperador()), String.valueOf(usuario.getIn_tipoUsuario_idTipoUsuario()), String.valueOf(usuario.getIn_entidadFinanciera()));

        call.enqueue(new Callback<ResponseService<ArrayList<ResponseRegistroUsuario>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> call, Response<ResponseService<ArrayList<ResponseRegistroUsuario>>> response) {
                if(response.isSuccessful()){
                    baseResponse.setEstado(String.valueOf(response.code()));
                    baseResponse.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        baseResponse = Util.parsearError(response.errorBody(), baseResponse, String.valueOf(response.code()));
                    else
                        baseResponse.setMessage(Constants.ERROR_NO_IDENTIFICADO);
                }
                usuarioRegistro.postValue(baseResponse);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION);
                usuarioRegistro.postValue(baseResponse);
            }
        });
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerUsuarioRegistro() {
        return usuarioRegistro;
    }
}
