package com.cctv.ugc.base;

import android.content.Context;

import com.cctv.ugc.Env;
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
