package com.bim.utp.pe.mvvm.repository.Bimer;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.Negocio;
import com.bim.utp.pe.local.model.body.ResponseRegistroBimer;
import com.bim.utp.pe.util.Constants;
import com.bim.utp.pe.util.Util;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BimerRepository implements IBimer {
    private MutableLiveData<BaseResponse> bimerRegistro;
    private BaseResponse baseResponse;

    @Override
    public void insertarBimer(Negocio negocio) {
        baseResponse = new BaseResponse();
        bimerRegistro = new MutableLiveData<>();

        Call<ResponseService<ArrayList<ResponseRegistroBimer>>> call = Util.services.BimerRegister(negocio.getIn_ruc() , negocio.getIn_nombreNeg(), negocio.getIn_direccion(), negocio.getIn_numeroContacto(),
                negocio.getIn_contrasenia(), String.valueOf(negocio.getIn_idUsuario()));
        call.enqueue(new Callback<ResponseService<ArrayList<ResponseRegistroBimer>>>() {
            @Override
            public void onResponse(Call<ResponseService<ArrayList<ResponseRegistroBimer>>> call, Response<ResponseService<ArrayList<ResponseRegistroBimer>>> response) {
                if(response.isSuccessful()){
                    baseResponse.setEstado(String.valueOf(response.code()));
                    baseResponse.setData(response.body());
                }else{
                    if(response.errorBody() != null)
                        baseResponse = Util.parsearError(response.errorBody(), baseResponse, String.valueOf(response.code()));
                    else
                        baseResponse.setMessage(Constants.ERROR_NO_IDENTIFICADO);
                }
                bimerRegistro.postValue(baseResponse);
            }

            @Override
            public void onFailure(Call<ResponseService<ArrayList<ResponseRegistroBimer>>> call, Throwable t) {
                baseResponse.setEstado(Constants.CODE_ERROR_SERVER);
                baseResponse.setMessage(Constants.ERROR_COMUNICACION);
                bimerRegistro.postValue(baseResponse);
            }
        });
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerNegocioRegister() {
        return bimerRegistro;
    }
}
