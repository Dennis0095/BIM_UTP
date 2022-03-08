package com.bim.utp.pe.mvvm.repository.services;

import com.bim.utp.pe.local.model.ResponseService;
import com.bim.utp.pe.local.model.body.EntidadFinanciera;
import com.bim.utp.pe.local.model.body.OperadorMovil;
import com.bim.utp.pe.local.model.body.ResponseConsultaBim;
import com.bim.utp.pe.local.model.body.ResponseRegistroBimer;
import com.bim.utp.pe.local.model.body.ResponseRegistroUsuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Services {

    @GET("entidadFinanciera.php")
    Call<ResponseService<ArrayList<EntidadFinanciera>>> getEntidadFinanciera();

    @GET("opeadorMovil.php")
    Call<ResponseService<ArrayList<OperadorMovil>>> getOperadoresMoviles();

    @FormUrlEncoded
    @POST("registrarUsuario.php")
    Call<ResponseService<ArrayList<ResponseRegistroUsuario>>> Usuarioregister2(@Field("in_movil") String in_movil, @Field("in_contrasenia") String in_contrasenia, @Field("in_dni") String in_dni,
                                                                                @Field("in_codigo") String in_codigo, @Field("in_monto") String in_monto,
                                                                               @Field("in_operadorMovil_idOperador") String in_operadorMovil_idOperador, @Field("in_tipoUsuario_idTipoUsuario") String in_tipoUsuario_idTipoUsuario,
                                                                               @Field("in_entidadFinanciera") String in_entidadFinanciera);
    @FormUrlEncoded
    @POST("servicios/insertarNegocio.php")
    Call<ResponseService<ArrayList<ResponseRegistroBimer>>> BimerRegister(@Field("in_ruc") String in_ruc, @Field("in_nombreNeg") String in_nombreNeg, @Field("in_direccion") String in_direccion,
                                                                             @Field("in_numeroContacto") String in_numeroContacto, @Field("in_contrasenia") String in_contrasenia,
                                                                             @Field("in_idUsuario") String in_idUsuario);
    @FormUrlEncoded
    @POST("usuario/consultaPerfil.php")
    Call<ResponseService<ArrayList<ResponseConsultaBim>>> ConsultaPerfilBim(@Field("in_idUsuario") int in_idUsuario);
}
