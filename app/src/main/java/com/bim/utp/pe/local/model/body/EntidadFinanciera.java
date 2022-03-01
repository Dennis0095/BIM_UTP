package com.bim.utp.pe.local.model.body;

import com.google.gson.annotations.SerializedName;

public class EntidadFinanciera {
    @SerializedName("idEntidad")
    private String idEntidad;
    @SerializedName("descripcion")
    private String descripcion;

    public String getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(String idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
