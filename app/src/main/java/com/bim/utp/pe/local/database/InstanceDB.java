package com.bim.utp.pe.local.database;

import android.content.Context;

import androidx.room.Room;

import com.bim.utp.pe.mvvm.repository.UserRepository;

public class InstanceDB {

    public static DbBim dbBim;
    private static InstanceDB INSTANCE;

    public static void setInstance(Context ctx){
        if(INSTANCE == null)
            INSTANCE = Initializer.INSTANCE;
        INSTANCE.init(ctx);
    }

    private void init(Context ctx){
        if(dbBim == null)
            dbBim = Room.databaseBuilder(ctx, DbBim.class, "dbBim").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        UserRepository.setUserDao(dbBim.getUserDao());
    }

    public static InstanceDB getInstance(){
        return INSTANCE;
    }

    // INSTANCIANDO UNA VEZ LA CLASE
    private static class Initializer{
        private static final InstanceDB INSTANCE = new InstanceDB();
    }
}
