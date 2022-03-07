package com.bim.utp.pe.local.model.body;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("idUsuario")
    private String idOperador;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("in_movil")
    private String in_movil;

    @SerializedName("in_contrasenia")
    private String in_contrasenia;

    @SerializedName(" in_dni")
    private String  in_dni;

    @SerializedName(" in_codigo")
    private String  in_codigo;

    @SerializedName(" in_monto")
    private String  in_monto;

    @SerializedName(" in_operadorMovil_idOperador")
    private int  in_operadorMovil_idOperador;

    @SerializedName(" in_tipoUsuario_idTipoUsuario")
    private int   in_tipoUsuario_idTipoUsuario;

    @SerializedName(" in_entidadFinanciera")
    private int   in_entidadFinanciera;

    public String getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIn_movil() {
        return in_movil;
    }

    public void setIn_movil(String in_movil) {
        this.in_movil = in_movil;
    }

    public String getIn_contrasenia() {
        return in_contrasenia;
    }

    public void setIn_contrasenia(String in_contrasenia) {
        this.in_contrasenia = in_contrasenia;
    }

    public String getIn_dni() {
        return in_dni;
    }

    public void setIn_dni(String in_dni) {
        this.in_dni = in_dni;
    }

    public String getIn_codigo() {
        return in_codigo;
    }

    public void setIn_codigo(String in_codigo) {
        this.in_codigo = in_codigo;
    }

    public String getIn_monto() {
        return in_monto;
    }

    public void setIn_monto(String in_monto) {
        this.in_monto = in_monto;
    }

    public int getIn_operadorMovil_idOperador() {
        return in_operadorMovil_idOperador;
    }

    public void setIn_operadorMovil_idOperador(int in_operadorMovil_idOperador) {
        this.in_operadorMovil_idOperador = in_operadorMovil_idOperador;
    }

    public int getIn_tipoUsuario_idTipoUsuario() {
        return in_tipoUsuario_idTipoUsuario;
    }

    public void setIn_tipoUsuario_idTipoUsuario(int in_tipoUsuario_idTipoUsuario) {
        this.in_tipoUsuario_idTipoUsuario = in_tipoUsuario_idTipoUsuario;
    }

    public int getIn_entidadFinanciera() {
        return in_entidadFinanciera;
    }

    public void setIn_entidadFinanciera(int in_entidadFinanciera) {
        this.in_entidadFinanciera = in_entidadFinanciera;
    }
}
