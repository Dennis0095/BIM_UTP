package com.bim.utp.pe.mvvm.repository.ConsultaBim;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.ResponseConsultaBim;
import com.bim.utp.pe.util.Constants;
import com.bim.utp.pe.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsultaUsuarioRepository implements IConsultaUsuario {
  //xd
    private MutableLiveData<BaseResponse> consultaUsuario;

    private BaseResponse baseResponse;

    @Override
    public void getConsultaUsuario(ResponseConsultaBim Usuario) {
        baseResponse = new BaseResponse();
        consultaUsuario = new MutableLiveData<>();
        Call<ResponseService<ArrayList<ResponseConsultaBim>>> call = Util.services.ConsultaPerfilBim(Usuario.getIn_idUsuario());
        call.enqueue(new Callback<ResponseService<ArrayList<ResponseConsultaBim>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<ResponseConsultaBim>>> call, Response<ResponseService<ArrayList<ResponseConsultaBim>>> response) {
                if(response.isSuccessful()){
                    baseResponse.setEstado(String.valueOf(response.code()));
                    baseResponse.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        baseResponse = Util.parsearError(response.errorBody(), baseResponse, String.valueOf(response.code()));
                    else
                        baseResponse.setMessage(Constants.ERROR_NO_IDENTIFICADO);

                }
                consultaUsuario.postValue(baseResponse);
    }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<ResponseConsultaBim>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION);
                consultaUsuario.postValue(baseResponse);
                }
                 });
            }

            @Override
    public MutableLiveData<BaseResponse> setListenerConsultaUsuario() {
        return consultaUsuario;
    }
}
