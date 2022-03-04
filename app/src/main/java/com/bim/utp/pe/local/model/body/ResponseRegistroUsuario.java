package com.bim.utp.pe.local.model.body;


import com.google.gson.annotations.SerializedName;

public class ResponseRegistroUsuario{

    @SerializedName("estado")
    private String estado;
    @SerializedName("mensaje")
    private String mensaje;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
