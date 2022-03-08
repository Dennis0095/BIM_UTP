package com.bim.utp.pe.mvvm.viewmodel.parametro;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.Negocio;
import com.bim.utp.pe.local.model.body.ResponseRegistroBimer;
import com.bim.utp.pe.mvvm.repository.Bimer.BimerRepository;
import com.bim.utp.pe.mvvm.repository.Bimer.IBimer;
import com.bim.utp.pe.mvvm.repository.Usuario.UsuarioRepository;
import com.bim.utp.pe.util.Constants;

import java.util.ArrayList;

public class BimerViewModel extends ViewModel implements IBimer {
    private MutableLiveData<BaseResponse> listenerBimerRegistro;
    private MutableLiveData<ArrayList<ResponseRegistroBimer>> bimerRegistro;

    private BimerRepository bimerRepository;
    private MutableLiveData<BaseResponse> error;


    public BimerViewModel() {
        error = new MutableLiveData<>();
        bimerRepository = new BimerRepository();
    }


    @Override
    public void insertarBimer(Negocio negocio) {
        bimerRegistro = new MutableLiveData<>();
        bimerRepository.insertarBimer(negocio);
        listenerBimerRegistro = bimerRepository.setListenerNegocioRegister();
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerNegocioRegister() {
        return listenerBimerRegistro;
    }

    public void verifyResponse(BaseResponse response){
        if(response.getEstado().equals(Constants.CODE_SUCCESSFULL)){
            bimerRegistro.postValue((ArrayList<ResponseRegistroBimer>) ((ResponseService) response.getData()).Response);
        }else{
            error.postValue(response);
        }
    }

    public MutableLiveData<BaseResponse> setError(){
        return error;
    }

    public MutableLiveData<ArrayList<ResponseRegistroBimer>> setRegistronegocio(){
        return bimerRegistro;
    }
}
