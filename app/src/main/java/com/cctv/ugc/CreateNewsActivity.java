package com.cctv.ugc;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.cctv.ugc.base.BaseActivity;

/**
 * Created by liuxin on 16/4/16.
 */
public class CreateNewsActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.create_news);
    }
}
