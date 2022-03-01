package com.bim.utp.pe.app;

import android.app.Application;
import android.content.Context;

import com.bim.utp.pe.local.database.InstanceDB;
import com.bim.utp.pe.util.Util;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // CREA LA INSTANCIA DE LA BASE DE DATOS
        InstanceDB.setInstance(getApplicationContext());
        Util.instanciarRetrofit();
        Util.instanciaShardPreference(getApplicationContext().getSharedPreferences("stored", Context.MODE_PRIVATE));
    }
}
