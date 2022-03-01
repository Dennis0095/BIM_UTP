package com.bim.utp.pe.mvvm.repository.services;

import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Services {

    @POST("entidadFinanciera.php")
    Call<ResponseService<ArrayList<EntidadFinanciera>>> getEntidadFinanciera();
}
