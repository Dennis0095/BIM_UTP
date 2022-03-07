package com.bim.utp.pe.mvvm.repository.transaccion.enviarDinero;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.EnviarDinero;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.ResponseGeneral;
import com.bim.utp.pe.util.Constants;
import com.bim.utp.pe.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnviarDineroRepository implements IEnviarDinero{

    private MutableLiveData<BaseResponse> envioDinero;

    private BaseResponse baseResponse;

    @Override
    public void setEnviarDinero(EnviarDinero enviarDinero) {
        baseResponse = new BaseResponse();
        envioDinero = new MutableLiveData<>();
        Call<ResponseService<ArrayList<ResponseGeneral>>> call = Util.services.enviarDinero(enviarDinero.getCelularDestino(),
                enviarDinero.getMonto(), enviarDinero.getMensaje(), enviarDinero.getContrasenia(), enviarDinero.getIdUsuario(), enviarDinero.getTipoTransaccion());
        call.enqueue(new Callback<ResponseService<ArrayList<ResponseGeneral>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<ResponseGeneral>>> call, Response<ResponseService<ArrayList<ResponseGeneral>>> response) {
                if(response.isSuccessful()){
                    baseResponse.setEstado(String.valueOf(response.code()));
                    baseResponse.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        baseResponse = Util.parsearError(response.errorBody(), baseResponse, String.valueOf(response.code()));
                    else
                        baseResponse.setMessage(Constants.ERROR_NO_IDENTIFICADO);

                }
                envioDinero.postValue(baseResponse);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<ResponseGeneral>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION + Constants.ERROR_LISTAR_ENTIDADES);
                envioDinero.postValue(baseResponse);
            }
        });
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerEnviarDinero() {
        return envioDinero;
    }
}
