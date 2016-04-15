package com.cctv.ugc.account;

/**
 * Created by liuxin on 16/4/8.
 */
public class LoginManager {

    private static LoginManager instance;

    public synchronized static LoginManager getInstance(){
        if(instance == null){
            instance = new LoginManager();
        }
        return instance;
    }



    public boolean isLogin(){
        return true;
    }

    public String getIdentity(){
        return "93709377";
    }
}
