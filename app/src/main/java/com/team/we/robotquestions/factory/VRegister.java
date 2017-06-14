package com.team.we.robotquestions.factory;

import android.util.Log;

/**
 * Created by czm on 2017/6/5 0005.
 */

public  class VRegister implements IRegister {

    private static final String TAG = "VRegister";

    @Override
    public boolean verifyRegister(String username, String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyLogin(String username, String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return true;
        }
        return false;
    }
}
