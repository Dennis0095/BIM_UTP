package com.bim.utp.pe.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.bim.utp.pe.local.tables.UserTbl;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user where id=:id")
    LiveData<UserTbl> getUser(int id);
}
