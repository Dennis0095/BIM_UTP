package com.bim.utp.pe.mvvm.viewmodel.parametro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bim.utp.pe.local.model.BaseResponse;
import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;
import com.bim.utp.pe.mvvm.repository.Usuario.IUsuario;
import com.bim.utp.pe.mvvm.repository.Usuario.UsuarioRepository;
import com.bim.utp.pe.util.Constants;

import java.util.ArrayList;

import retrofit2.Response;

public class UsuarioViewModel extends ViewModel implements IUsuario {
    private MutableLiveData<BaseResponse> listenerUsuarioRegistro;
    private MutableLiveData<ArrayList<ResponseRegistroUsuario>> usuarioregistro;

    private UsuarioRepository usuarioRepository;
    private MutableLiveData<BaseResponse> error;

    public UsuarioViewModel() {
        error = new MutableLiveData<>();
        usuarioRepository = new UsuarioRepository();
    }


    @Override
    public void insertarUsuario(Usuario usuario) {
        usuarioregistro = new MutableLiveData<>();
        usuarioRepository.insertarUsuario(usuario);
        listenerUsuarioRegistro = usuarioRepository.setListenerUsuarioRegistro();
    }

    @Override
    public MutableLiveData<BaseResponse> setListenerUsuarioRegistro() {
        return listenerUsuarioRegistro;
    }

    public void verifyResponse(BaseResponse response){
        if(response.getEstado().equals(Constants.CODE_SUCCESSFULL)){
            usuarioregistro.postValue((ArrayList<ResponseRegistroUsuario>) ((ResponseService) response.getData()).Response);
        }else{
            error.postValue(response);
        }
    }

    public MutableLiveData<BaseResponse> setError(){
        return error;
    }

    public MutableLiveData<ArrayList<ResponseRegistroUsuario>> setRegistroUsuario(){
        return usuarioregistro;
    }
}
