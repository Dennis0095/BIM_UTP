package com.bim.utp.pe.local.model.body;

import com.google.gson.annotations.SerializedName;

public class EntidadDeposito {
    @SerializedName("idDeposito")
    private String idDeposito;
    @SerializedName("monto")
    private String monto;
    @SerializedName("fecha")
    private String fecha;

    public String getIdDeposito() {
        return idDeposito;
    }

    public void setIdDeposito(String idDeposito) {
        this.idDeposito = idDeposito;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
