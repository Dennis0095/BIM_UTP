package com.bim.utp.pe.local.model.body;

import com.google.gson.annotations.SerializedName;

public class Negocio {

    @SerializedName("in_ruc")
    private String in_ruc;

    @SerializedName("in_nombreNeg")
    private String in_nombreNeg;

    @SerializedName("in_direccion")
    private String in_direccion;

    @SerializedName("in_numeroContacto")
    private String in_numeroContacto;

    @SerializedName("in_contrasenia")
    private String in_contrasenia;

    @SerializedName("in_idUsuario")
    private int in_idUsuario;

    public String getIn_ruc() {
        return in_ruc;
    }

    public void setIn_ruc(String in_ruc) {
        this.in_ruc = in_ruc;
    }

    public String getIn_nombreNeg() {
        return in_nombreNeg;
    }

    public void setIn_nombreNeg(String in_nombreNeg) {
        this.in_nombreNeg = in_nombreNeg;
    }

    public String getIn_direccion() {
        return in_direccion;
    }

    public void setIn_direccion(String in_direccion) {
        this.in_direccion = in_direccion;
    }

    public String getIn_numeroContacto() {
        return in_numeroContacto;
    }

    public void setIn_numeroContacto(String in_numeroContacto) {
        this.in_numeroContacto = in_numeroContacto;
    }

    public String getIn_contrasenia() {
        return in_contrasenia;
    }

    public void setIn_contrasenia(String in_contrasenia) {
        this.in_contrasenia = in_contrasenia;
    }

    public int getIn_idUsuario() {
        return in_idUsuario;
    }

    public void setIn_idUsuario(int in_idUsuario) {
        this.in_idUsuario = in_idUsuario;
    }
}
