package com.bim.utp.pe.mvvm.viewmodel.parametro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.EntidadDeposito;
import com.bim.utp.pe.mvvm.repository.parametro.IParametro;
import com.bim.utp.pe.mvvm.repository.parametro.ParametroRepository;
import com.bim.utp.pe.util.Constants;

import java.util.ArrayList;

public class ParametroViewModel extends ViewModel implements IParametro {
    private MutableLiveData<BaseResponse> listenerEntidad;
    private MutableLiveData<ArrayList<EntidadFinanciera>> entidadesFinancieras;

    private MutableLiveData<BaseResponse> listenerDeposito;
    private MutableLiveData<ArrayList<EntidadDeposito>> reporteDepositos;

    private ParametroRepository parametroRepository;
    private MutableLiveData<BaseResponse> error;

    public ParametroViewModel() {
        error = new MutableLiveData<>();
        parametroRepository = new ParametroRepository();
    }
    // comentario
    // comentario OScar
    // comentario Piere

    //ENTIDADES FINANCIERAS
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


    //REPORTE DEPOSITO
    @Override
    public void getReporteDepositos(int in_idUsuario) {
        reporteDepositos = new MutableLiveData<>();
        parametroRepository.getReporteDepositos(in_idUsuario);
        listenerDeposito = parametroRepository.setListenerReporteDepositos();
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerReporteDepositos() {
        return listenerDeposito;
    }

    public void verifyResponse2(BaseResponse response){
        if(response.getEstado().equals(Constants.CODE_SUCCESSFULL)){
            reporteDepositos.postValue((ArrayList<EntidadDeposito>) ((ResponseService) response.getData()).Response);
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

    public MutableLiveData<ArrayList<EntidadDeposito>> setReporteDeposito(){
        return reporteDepositos;
    }




}
