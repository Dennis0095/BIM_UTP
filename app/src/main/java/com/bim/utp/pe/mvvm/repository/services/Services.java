package com.bim.utp.pe.mvvm.repository.services;

import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;
import com.bim.utp.pe.local.model.body.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Services {

    @GET("entidadFinanciera.php")
    Call<ResponseService<ArrayList<EntidadFinanciera>>> getEntidadFinanciera();

    @GET("opeadorMovil.php")
    Call<ResponseService<ArrayList<OperadorMovil>>> getOperadoresMoviles();

    @POST("registrarUsuario.php")
    Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> Usuarioregister(@Body Usuario usuario);

}
