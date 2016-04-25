package com.cctv.ugc.activity;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cctv.ugc.R;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.fileuploader.FileUploaderManager;
import com.cctv.ugc.model.VideoItem;
import com.cctv.ugc.model.VideoLocalItem;
import com.cctv.ugc.uicomponent.UploadingProgressView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

/**
 * Created by liuxin on 16/4/23.
 */
@EActivity(R.layout.activity_uploading)
public class CreateNewsUploadingActivity extends BaseActivity {

    @ViewById(R.id.uploadingDes)
    TextView uploadingDes;

    @ViewById(R.id.title)
    TextView title;

    @ViewById(R.id.uploadingProgress)
    UploadingProgressView uploadingProgress;

    @AfterViews
    void init() {
        title.setText("正在上传");
        VideoLocalItem localItem = getIntent().getParcelableExtra("videoLocalItem");
        VideoItem videoItem = getIntent().getParcelableExtra("videoItem");
        String des = getString(R.string.uploading_des, videoItem.getTitle());
        uploadingDes.setText(des);

        //TODO test
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(CreateNewsUploadingActivity.this,UploadCompleteActivity_.class));
            }
        },2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        uploadingProgress.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        uploadingProgress.start();
    }

    private void uploadVideoInfo(){

    }

    private void uploadVideoFile(String[] filePaths){
        for (String filePath : filePaths) {
            if(TextUtils.isEmpty(filePath))
                continue;
            new FileUploaderManager().startUpload(new File(filePath));
        }
    }
}
