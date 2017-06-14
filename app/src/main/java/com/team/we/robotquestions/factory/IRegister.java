package com.team.we.robotquestions.factory;

/**
 * Created by czm on 2017/6/5 0005.
 */

public interface IRegister {

    boolean verifyRegister(String username, String password);

    boolean verifyLogin(String username, String password);



}
