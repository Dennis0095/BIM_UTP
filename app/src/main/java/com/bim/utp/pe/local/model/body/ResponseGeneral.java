package com.bim.utp.pe.local.model.body;

import com.google.gson.annotations.SerializedName;

public class ResponseGeneral {

    @SerializedName("mensaje")
    private String mensaje;

    @SerializedName("estado")
    private String estado;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
