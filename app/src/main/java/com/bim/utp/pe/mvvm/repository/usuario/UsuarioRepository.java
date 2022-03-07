package com.bim.utp.pe.mvvm.repository.usuario;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadDeposito;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.util.Constants;
import com.bim.utp.pe.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {

    private MutableLiveData<BaseResponse> reporteDepositos;
    private BaseResponse baseResponse;

    public void getReporteDepositos(int idUsuario) {
        baseResponse = new BaseResponse();
        reporteDepositos = new MutableLiveData<>();
        Call<ResponseService<ArrayList<EntidadDeposito>>> call = Util.services.getReporteDeposito(idUsuario);
        call.enqueue(new Callback<ResponseService<ArrayList<EntidadDeposito>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<EntidadDeposito>>> call, Response<ResponseService<ArrayList<EntidadDeposito>>> response) {
                if(response.isSuccessful()){
                    baseResponse.setEstado(String.valueOf(response.code()));
                    baseResponse.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        baseResponse = Util.parsearError(response.errorBody(), baseResponse, String.valueOf(response.code()));
                    else
                        baseResponse.setMessage(Constants.ERROR_NO_IDENTIFICADO);

                }
                reporteDepositos.postValue(baseResponse);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<EntidadDeposito>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION + Constants.ERROR_LISTAR_DEPOSITOS);
                reporteDepositos.postValue(baseResponse);
            }
        });
    }

    public MutableLiveData<BaseResponse> setListenerReporteDepositos() {
        return reporteDepositos;
    }
}
