package com.team.we.robotquestions.factory;

/**
 * Created by czm on 2017/6/5 0005.
 */

public  class UserGenerator extends IRGenerator {


    @Override
    public IRegister verifyRegister() {
        return new VRegister();
    }
}
