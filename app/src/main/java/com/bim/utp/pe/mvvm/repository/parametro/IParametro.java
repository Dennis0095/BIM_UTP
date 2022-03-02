package com.bim.utp.pe.mvvm.repository.parametro;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;

public interface IParametro {

    void getEntidadesFinancieras();

    MutableLiveData<BaseResponse> setListenerEntidadesFinancieras();

    void getOperadoresMoviles();

    MutableLiveData<BaseResponse> setListenerOperadoresMoviles();

}
