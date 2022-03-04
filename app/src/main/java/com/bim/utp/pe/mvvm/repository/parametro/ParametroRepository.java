package com.bim.utp.pe.mvvm.repository.parametro;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.util.Constants;
import com.bim.utp.pe.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParametroRepository implements IParametro {
    private MutableLiveData<BaseResponse> entidad;
    private MutableLiveData<BaseResponse> operadorMovil;

    private BaseResponse baseResponse;

//HOLA

    @Override
    public void getEntidadesFinancieras() {
        baseResponse = new BaseResponse();
        entidad = new MutableLiveData<>();
        Call<ResponseService<ArrayList<EntidadFinanciera>>> call = Util.services.getEntidadFinanciera();
        call.enqueue(new Callback<ResponseService<ArrayList<EntidadFinanciera>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<EntidadFinanciera>>> call, Response<ResponseService<ArrayList<EntidadFinanciera>>> response) {
                if(response.isSuccessful()){
                    baseResponse.setEstado(String.valueOf(response.code()));
                    baseResponse.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        baseResponse = Util.parsearError(response.errorBody(), baseResponse, String.valueOf(response.code()));
                    else
                        baseResponse.setMessage(Constants.ERROR_NO_IDENTIFICADO);

                }
                entidad.postValue(baseResponse);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<EntidadFinanciera>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION + Constants.ERROR_LISTAR_ENTIDADES);
                entidad.postValue(baseResponse);
            }
        });
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerEntidadesFinancieras() {
        return entidad;
    }

    @Override
    public void getOperadoresMoviles() {
        baseResponse = new BaseResponse();
        operadorMovil = new MutableLiveData<>();
        Call<ResponseService<ArrayList<OperadorMovil>>> call = Util.services.getOperadoresMoviles();
        call.enqueue(new Callback<ResponseService<ArrayList<OperadorMovil>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<OperadorMovil>>> call, Response<ResponseService<ArrayList<OperadorMovil>>> response) {
                if(response.isSuccessful()){
                    baseResponse.setEstado(String.valueOf(response.code()));
                    baseResponse.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        baseResponse = Util.parsearError(response.errorBody(), baseResponse, String.valueOf(response.code()));
                    else
                        baseResponse.setMessage(Constants.ERROR_NO_IDENTIFICADO);

                }
                operadorMovil.postValue(baseResponse);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<OperadorMovil>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION);
                operadorMovil.postValue(baseResponse);
            }
        });
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerOperadoresMoviles() {
        return operadorMovil;
    }

}
