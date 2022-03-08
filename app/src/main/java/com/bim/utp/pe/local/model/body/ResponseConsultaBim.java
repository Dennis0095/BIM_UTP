package com.bim.utp.pe.local.model.body;

import com.google.gson.annotations.SerializedName;

public class ResponseConsultaBim {

    @SerializedName("in_idUsuario")
    private int in_idUsuario;
    @SerializedName("movil")
    private String movil;
    @SerializedName("correo")
    private String correo;
    @SerializedName("dni")
    private String dni;
    @SerializedName("operadorDesc")
    private String operadorDesc;
    @SerializedName("entidadDesc")
    private String entidadDesc;

    public int getIn_idUsuario() {
        return in_idUsuario;
    }

    public void setIn_idUsuario(int in_idUsuario) {
        this.in_idUsuario = in_idUsuario;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getOperadorDesc() {
        return operadorDesc;
    }

    public void setOperadorDesc(String operadorDesc) {
        this.operadorDesc = operadorDesc;
    }

    public String getEntidadDesc() {
        return entidadDesc;
    }

    public void setEntidadDesc(String entidadDesc) {
        this.entidadDesc = entidadDesc;
    }
}
