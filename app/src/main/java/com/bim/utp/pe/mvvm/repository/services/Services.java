package com.bim.utp.pe.mvvm.repository.services;

import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadDeposito;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.local.model.body.ResponseGeneral;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {

    @GET("entidadFinanciera.php")
    Call<ResponseService<ArrayList<EntidadFinanciera>>> getEntidadFinanciera();

    @GET("opeadorMovil.php")
    Call<ResponseService<ArrayList<OperadorMovil>>> getOperadoresMoviles();

   // @POST("registrarUsuario.php/")
    //Call<ResponseService<ArrayList<OperadorMovil>>> getOperadoresMoviles();

    @FormUrlEncoded
    @POST("enviar_dinero.php")
    Call<ResponseService<ArrayList<ResponseGeneral>>> enviarDinero(@Field("in_celular_destino") String celularDestino, @Field("in_monto") String in_monto,
                                                                   @Field("in_mensaje") String in_mensaje,
                                                                   @Field("in_contrasenia") String in_contrasenia, @Field("in_idUsuario") String in_idUsuario,
                                                                   @Field("in_tipoTransaccion") String in_tipoTransaccion);




    @GET("negocio/listaReporteDeposito.php/{in_idUsuario}")
    Call<ResponseService<ArrayList<EntidadDeposito>>> getReporteDeposito(@Query("in_idUsuario") int in_idUsuario);
}
