package com.bim.utp.pe.mvvm.repository.services;

import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadDeposito;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Services {

    @GET("https://www.vaqante.com/ws_plancha_virtual/BIM/entidadFinanciera.php")
    Call<ResponseService<ArrayList<EntidadFinanciera>>> getEntidadFinanciera();

    @GET("https://www.vaqante.com/ws_plancha_virtual/BIM/negocio/listaReporteDeposito.php/{in_idUsuario}")
    Call<ResponseService<ArrayList<EntidadDeposito>>> getReporteDeposito(@Path("in_idUsuario") int in_idUsuario);


}
