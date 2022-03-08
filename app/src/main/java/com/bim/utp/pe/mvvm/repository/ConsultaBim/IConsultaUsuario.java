package com.bim.utp.pe.mvvm.repository.ConsultaBim;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.body.ResponseConsultaBim;

public interface IConsultaUsuario {
 //ss
    void getConsultaUsuario(ResponseConsultaBim idUsuario);

    MutableLiveData<BaseResponse> setListenerConsultaUsuario();
}
