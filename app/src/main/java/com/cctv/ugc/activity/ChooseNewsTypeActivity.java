package com.cctv.ugc.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.cctv.ugc.R;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.base.TipsHelper;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.choose_news_type)
public class ChooseNewsTypeActivity extends BaseActivity {


    @Click(R.id.local)
    public void onClick(View view) {
        chooseLocalVideo();
    }

    public void onClickRecord(){

    }

    private void chooseLocalVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        try {
            this.startActivityForResult(intent, 0);
        } catch (Exception e) {
            TipsHelper.showErrorTip(this, getString(R.string.error1));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "requestCode=" + requestCode + ",resultCode=" + requestCode + ",Intent=" + (data != null ? data.toString() : data));
        if (isDataValid(data)) {
            Intent intent = new Intent(this, CreateNewsActivity_.class);
            intent.putExtra("from",ChooseNewsTypeActivity.class.getSimpleName());
            intent.putExtra("filePath",data.getData());
            startActivity(intent);
        }
    }

    private boolean isDataValid(Intent data) {
        if (data != null && data.getData() != null)
            return true;
        else
            return false;
    }
}
