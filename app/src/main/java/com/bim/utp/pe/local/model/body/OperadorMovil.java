package com.bim.utp.pe.local.model.body;

import com.google.gson.annotations.SerializedName;


public class OperadorMovil {
    @SerializedName("idOperador")
    private String idOperador;
    @SerializedName("descripcion")
    private String descripcion;

    public String getidOperador() {
        return idOperador;
    }

    public void setidOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
