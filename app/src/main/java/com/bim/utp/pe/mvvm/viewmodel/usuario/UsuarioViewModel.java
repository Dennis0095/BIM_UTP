package com.bim.utp.pe.mvvm.viewmodel.usuario;

import androidx.lifecycle.MutableLiveData;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.mvvm.repository.parametro.ParametroRepository;
import com.bim.utp.pe.mvvm.repository.usuario.UsuarioRepository;

import java.util.ArrayList;

public class UsuarioViewModel {

    private MutableLiveData<BaseResponse> listenerReporteDeposito;
    private UsuarioRepository repository;

    public UsuarioViewModel(){
        repository = new UsuarioRepository();
    }

    public void getReporteDepositos(int in_idUsuario) {
        repository.getReporteDepositos(in_idUsuario);
        listenerReporteDeposito = repository.setListenerReporteDepositos();
    }

    public MutableLiveData<BaseResponse> setListenerReporteDepositos() {
        return listenerReporteDeposito;
    }
}
