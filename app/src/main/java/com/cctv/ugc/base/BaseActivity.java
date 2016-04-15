package com.cctv.ugc.base;

import android.app.Activity;

import com.umeng.analytics.MobclickAgent;

/**
 * Created by liuxin on 16/4/5.
 */
public class BaseActivity extends Activity{


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
