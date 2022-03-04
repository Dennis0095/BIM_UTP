package com.bim.utp.pe.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UsuarioPreferences{

    private static UsuarioPreferences UsuarioPreferences;

    public static UsuarioPreferences getInstance() {
        if (UsuarioPreferences == null) {
            UsuarioPreferences = new UsuarioPreferences();
        }
        return UsuarioPreferences;
    }

    public static String preferenceDatosUsuario = "datosUsuario";

    public void guardarMovilUsuario(Context c, String movil) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        datosUsuario.edit().putString("movilUsuario", movil).apply();
    }

    public String recuperarMovilUsuario(Context c) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        return datosUsuario.getString("movilUsuario", "no hay dato");
    }


    public void guardarDNIUsuario(Context c, String dniUsuario) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        datosUsuario.edit().putString("dniUsuario", dniUsuario).apply();
    }

    public String recuperarDNIUsuario(Context c) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        return datosUsuario.getString("dniUsuario", "no hay dato");
    }

    public void guardarcodDNIUsuario(Context c, String coddniUsuario) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        datosUsuario.edit().putString("codDniUsuario", coddniUsuario).apply();
    }

    public String recuperarcodDNIUsuario(Context c) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        return datosUsuario.getString("codDniUsuario", "no hay dato");
    }

    public void guardarOperadorUsuario(Context c, int operadorUsuario) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        datosUsuario.edit().putInt("operadorUsuario", operadorUsuario).apply();
    }

    public int recuperarOperadorUsuario(Context c) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        return datosUsuario.getInt("operadorUsuario",0);
    }

    public void guardarEntidadUsuario(Context c, int entidadUsuario) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        datosUsuario.edit().putInt("entidadUsuario", entidadUsuario).apply();
    }

    public int recuperarEntidadUsuario(Context c) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        return datosUsuario.getInt("entidadUsuario",0);
    }

    public void guardarContraseniaUsuario(Context c, String contraseniaUsuario) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        datosUsuario.edit().putString("contraseniaUsuario", contraseniaUsuario).apply();
    }

    public String recuperarContraseniaUsuario(Context c) {
        SharedPreferences datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE);
        return datosUsuario.getString("contraseniaUsuario", "no hay dato");
    }

    public void limpiardatosUsuario(Context c) {
        SharedPreferences.Editor datosUsuario = c.getSharedPreferences(preferenceDatosUsuario, Context.MODE_PRIVATE).edit();
        datosUsuario.clear().apply();
    }
}
