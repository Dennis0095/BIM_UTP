package com.bim.utp.pe.mvvm.viewmodel.parametro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.Negocio;
import com.bim.utp.pe.local.model.body.ResponseConsultaBim;
import com.bim.utp.pe.mvvm.repository.Bimer.BimerRepository;
import com.bim.utp.pe.mvvm.repository.Bimer.IBimer;
import com.bim.utp.pe.mvvm.repository.ConsultaBim.ConsultaUsuarioRepository;
import com.bim.utp.pe.mvvm.repository.ConsultaBim.IConsultaUsuario;
import com.bim.utp.pe.util.Constants;

import java.util.ArrayList;

public class ConsultaBimViewModel extends ViewModel implements IConsultaUsuario {
    private MutableLiveData<BaseResponse> listenerConsultaBim;
    private MutableLiveData<ArrayList<ResponseConsultaBim>> consultaBim;

    private ConsultaUsuarioRepository consultaUsuarioRepository;
    private MutableLiveData<BaseResponse> error;


    public ConsultaBimViewModel() {
        error = new MutableLiveData<>();
        consultaUsuarioRepository = new ConsultaUsuarioRepository();
    }


    @Override
    public void getConsultaUsuario(ResponseConsultaBim idUsuario) {
        consultaBim = new MutableLiveData<>();
        consultaUsuarioRepository.getConsultaUsuario(idUsuario);
        listenerConsultaBim = consultaUsuarioRepository.setListenerConsultaUsuario();
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerConsultaUsuario() {
        return listenerConsultaBim;
    }

    public void verifyResponse(BaseResponse response){
        if(response.getEstado().equals(Constants.CODE_SUCCESSFULL)){
            consultaBim.postValue((ArrayList<ResponseConsultaBim>) ((ResponseService) response.getData()).Response);
        }else{
            error.postValue(response);
        }
    }

    public MutableLiveData<ArrayList<ResponseConsultaBim>> setConsultaBim(){
        return consultaBim;
    }

    public MutableLiveData<BaseResponse> setError(){
        return error;
    }
}


