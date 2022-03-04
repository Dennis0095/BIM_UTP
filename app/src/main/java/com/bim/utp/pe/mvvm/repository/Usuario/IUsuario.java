package com.bim.utp.pe.mvvm.repository.Usuario;
import androidx.lifecycle.MutableLiveData;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;

public interface IUsuario {

    void insertarUsuario(Usuario usuario);

    MutableLiveData<ResponseRegistroUsuario> setListenerUsuarioRegistro();

}
