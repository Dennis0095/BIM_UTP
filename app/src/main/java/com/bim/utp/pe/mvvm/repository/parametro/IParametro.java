package com.bim.utp.pe.mvvm.repository.parametro;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;

import java.util.ArrayList;

public interface IParametro {

    void getEntidadesFinancieras();

    MutableLiveData<BaseResponse> setListenerEntidadesFinancieras();

}
