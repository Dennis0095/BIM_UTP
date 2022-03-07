package com.bim.utp.pe.local.model;

public class EnviarDinero {

    private String celularDestino;
    private String monto;
    private String mensaje;
    private String contrasenia;
    private String idUsuario;
    private String tipoTransaccion;

    public String getCelularDestino() {
        return celularDestino;
    }

    public void setCelularDestino(String celularDestino) {
        this.celularDestino = celularDestino;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
}
