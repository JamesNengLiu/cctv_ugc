package com.cctv.ugc;

import android.app.Application;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import org.litepal.LitePalApplication;

/**
 * Created by liuxin on 16/4/6.
 */
public class App extends LitePalApplication{

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
        MobclickAgent.setDebugMode(Env.DEBUG);
        instance = this;
    }

    public static Context getAppContext(){
        return instance;
    }
}
