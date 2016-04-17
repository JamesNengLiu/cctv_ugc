package com.cctv.ugc.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.cctv.ugc.R;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.util.forapp.AppFileUtils;
import com.cctv.ugc.util.forapp.TipsHelper;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.io.File;

@EActivity(R.layout.choose_news_type)
public class ChooseNewsTypeActivity extends BaseActivity {

    private static final int REQEST_CODE_CHOSE = 1;
    private static final int REQUEST_CODE_RECORD = 2;


    @Click({R.id.record, R.id.local})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.local:
                chooseLocalVideo();
                break;
            case R.id.record:
                recordVideo();
                break;
        }
    }


    private void chooseLocalVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        try {
            this.startActivityForResult(intent, REQEST_CODE_CHOSE);
        } catch (Exception e) {
            TipsHelper.showErrorTip(this, getString(R.string.error1));
        }

    }

    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        try {
            File file = AppFileUtils.createRecordFile();
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            //0最差，1最好
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, REQUEST_CODE_RECORD);
        } catch (Exception e) {
            TipsHelper.showErrorTip(this, getString(R.string.error2));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "requestCode=" + requestCode + ",resultCode=" + requestCode + ",Intent=" + (data != null ? data.toString() : data));
        if (!isDataValid(data)) {
            TipsHelper.showErrorTip(this, getString(R.string.error3));
            return;
        }
        Intent intent = new Intent(this, CreateNewsActivity_.class);
        intent.putExtra("from", ChooseNewsTypeActivity.class.getSimpleName());
        intent.putExtra("filePath", data.getData());
        startActivity(intent);
    }

    private boolean isDataValid(Intent data) {
        if (data != null && data.getData() != null)
            return true;
        else
            return false;
    }
}
