package com.bim.utp.pe.local.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bim.utp.pe.local.dao.UserDao;
import com.bim.utp.pe.local.tables.UserTbl;

@Database(entities = {
        UserTbl.class
        },
        version = 1,
        exportSchema = false)
abstract public class DbBim extends RoomDatabase {
    public abstract UserDao getUserDao();
}
