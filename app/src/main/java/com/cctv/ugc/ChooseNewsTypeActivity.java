package com.cctv.ugc;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.cctv.ugc.base.BaseActivity;

/**
 * Created by liuxin on 16/4/16.
 */
public class ChooseNewsTypeActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.choose_news_type);
        findViewById(R.id.local).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent());
            }
        });
    }
}
