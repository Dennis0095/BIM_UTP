package com.bim.utp.pe.mvvm.viewmodel.transaccion.enviarDinero;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.EnviarDinero;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.local.model.body.ResponseGeneral;
import com.bim.utp.pe.mvvm.repository.parametro.ParametroRepository;
import com.bim.utp.pe.mvvm.repository.transaccion.enviarDinero.EnviarDineroRepository;
import com.bim.utp.pe.util.Constants;

import java.util.ArrayList;

public class EnviarDineroViewModel extends ViewModel {


    private MutableLiveData<BaseResponse> listenerEnvioDinero;
    private MutableLiveData<ArrayList<ResponseGeneral>> responseEnvioDinero;

    private EnviarDineroRepository repository;
    private MutableLiveData<BaseResponse> error;

    public EnviarDineroViewModel() {
        error = new MutableLiveData<>();
        repository = new EnviarDineroRepository();
    }

    public void setEnviarDinero(EnviarDinero enviarDinero){
        responseEnvioDinero = new MutableLiveData<>();
        repository.setEnviarDinero(enviarDinero);
        listenerEnvioDinero = repository.setListenerEnviarDinero();
    }

    public MutableLiveData<BaseResponse> setListenerEnviarDinero() {
        return listenerEnvioDinero;
    }

    public void verifyResponse(BaseResponse response){
        if(response.getEstado().equals(Constants.CODE_SUCCESSFULL)){
            responseEnvioDinero.postValue((ArrayList<ResponseGeneral>) ((ResponseService) response.getData()).Response);
        }else{
            error.postValue(response);
        }
    }

    public MutableLiveData<ArrayList<ResponseGeneral>> getResponseEnvioDinero() {
        return responseEnvioDinero;
    }
}
