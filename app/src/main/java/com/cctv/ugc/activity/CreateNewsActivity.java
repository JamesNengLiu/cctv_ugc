package com.cctv.ugc.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.cctv.ugc.R;
import com.cctv.ugc.activity.ChooseNewsTypeActivity;
import com.cctv.ugc.base.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by liuxin on 16/4/16.
 */
@EActivity(R.layout.create_news)
public class CreateNewsActivity extends BaseActivity {

    @AfterViews
    protected void init() {
        initData();
        initFragments();
    }

    private void initData(){
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        if (from.equals(ChooseNewsTypeActivity.class.getSimpleName())) {
            Uri data = intent.getParcelableExtra("filePath");
            Log.d(TAG, "file uri = " + data.toString());
        }
    }

    private void initFragments(){

    }

}
