package com.bim.utp.pe.mvvm.repository.transaccion.enviarDinero;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.EnviarDinero;

public interface IEnviarDinero {

    void setEnviarDinero(EnviarDinero enviarDinero);

    MutableLiveData<BaseResponse> setListenerEnviarDinero();
}
