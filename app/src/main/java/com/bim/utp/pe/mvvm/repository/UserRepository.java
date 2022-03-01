package com.bim.utp.pe.mvvm.repository;

import com.bim.utp.pe.local.dao.UserDao;

public class UserRepository {

    private static UserDao userDao;

    public static void setUserDao(UserDao obj){
        if(userDao == null)
            userDao = obj;
    }
}
