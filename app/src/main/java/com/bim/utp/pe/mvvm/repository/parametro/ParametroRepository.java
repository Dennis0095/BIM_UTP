package com.bim.utp.pe.mvvm.repository.parametro;

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

public class ParametroRepository implements IParametro {
    private MutableLiveData<BaseResponse> entidad;
    private MutableLiveData<BaseResponse> deposito;

    private BaseResponse baseResponse;

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
    public void getReporteDepositos(int in_idUsuario) {
        baseResponse = new BaseResponse();
        deposito = new MutableLiveData<>();

        Call<ResponseService<ArrayList<EntidadDeposito>>> call = Util.services.getReporteDeposito(in_idUsuario);
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
                deposito.postValue(baseResponse);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<EntidadDeposito>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION + Constants.ERROR_LISTAR_ENTIDADES);
                deposito.postValue(baseResponse);
            }
        });
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerReporteDepositos() {
        return deposito;
    }

}
