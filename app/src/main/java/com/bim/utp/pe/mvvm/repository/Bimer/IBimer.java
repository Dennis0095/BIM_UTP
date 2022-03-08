package com.bim.utp.pe.mvvm.repository.Bimer;
import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.body.Negocio;

public interface IBimer {

    void insertarBimer(Negocio negocio);

    MutableLiveData<BaseResponse> setListenerNegocioRegister();

}
