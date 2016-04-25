package com.cctv.ugc.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.cctv.ugc.R;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.util.DebugLog;
import com.cctv.ugc.util.ImageUtils;
import com.cctv.ugc.util.forapp.AppFileUtils;
import com.cctv.ugc.util.forapp.TipsHelper;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.io.File;

@EActivity(R.layout.choose_news_type)
public class ChooseNewsTypeActivity extends BaseActivity {

    private static final int REQEST_CODE_CHOSE = 1;
    private static final int REQUEST_CODE_RECORD = 2;

    private Uri recordUri;


    @Click({R.id.record, R.id.local,R.id.bottomBtn})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.local:
                chooseLocalVideo();
                break;
            case R.id.record:
                recordVideo();
                break;
            case R.id.bottomBtn:
                finish();
                break;
        }
    }

    @AfterViews
    void init(){
        Bitmap bitmap = getIntent().getParcelableExtra("bitmap");
        findViewById(R.id.chooseParent).setBackgroundDrawable(new BitmapDrawable(bitmap));
    }



    private void chooseLocalVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");

        try {
            this.startActivityForResult(intent, REQEST_CODE_CHOSE);
        } catch (Exception e) {
            DebugLog.log(TAG,"choose error",e);
            TipsHelper.showErrorTip(this, getString(R.string.error1));
        }

    }

    private void recordVideo() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        try {
            File file = AppFileUtils.createRecordFile();
            recordUri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, recordUri);
            //0最差，1最好
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, REQUEST_CODE_RECORD);
        } catch (Exception e) {
            DebugLog.log(TAG,"record error",e);
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
        Uri filePath = data.getData();

//        if(requestCode == REQUEST_CODE_RECORD){
//            filePath = recordUri;
//        }

        Intent intent = new Intent(this, CreateNewsOneActivity_.class);
        intent.putExtra("from", ChooseNewsTypeActivity.class.getSimpleName());
        intent.putExtra("filePath", filePath);
        startActivity(intent);
        finish();
    }

    private boolean isDataValid(Intent data) {
        if (data != null && data.getData() != null)
            return true;
        else
            return false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
