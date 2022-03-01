package com.bim.utp.pe.mvvm.viewmodel.parametro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.mvvm.repository.parametro.IParametro;
import com.bim.utp.pe.mvvm.repository.parametro.ParametroRepository;
import com.bim.utp.pe.util.Constants;

import java.util.ArrayList;

public class ParametroViewModel extends ViewModel implements IParametro {
    private MutableLiveData<BaseResponse> listenerEntidad;
    private MutableLiveData<ArrayList<EntidadFinanciera>> entidadesFinancieras;
    private ParametroRepository parametroRepository;
    private MutableLiveData<BaseResponse> error;

    public ParametroViewModel() {
        error = new MutableLiveData<>();
        parametroRepository = new ParametroRepository();
    }
    // comentario
    // comentario OScar
    // comentario Piere
    // comentario Janet

    @Override
    public void getEntidadesFinancieras() {
        entidadesFinancieras = new MutableLiveData<>();
        parametroRepository.getEntidadesFinancieras();
        listenerEntidad = parametroRepository.setListenerEntidadesFinancieras();
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerEntidadesFinancieras() {
        return listenerEntidad;
    }

    public void verifyResponse(BaseResponse response){
        if(response.getEstado().equals(Constants.CODE_SUCCESSFULL)){
            entidadesFinancieras.postValue((ArrayList<EntidadFinanciera>) ((ResponseService) response.getData()).Response);
        }else{
            error.postValue(response);
        }
    }

    public MutableLiveData<BaseResponse> setError(){
        return error;
    }

    public MutableLiveData<ArrayList<EntidadFinanciera>> setEntidadFinanciera(){
        return entidadesFinancieras;
    }
}
