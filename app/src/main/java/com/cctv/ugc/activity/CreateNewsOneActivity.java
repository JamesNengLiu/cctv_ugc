package com.cctv.ugc.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.cctv.ugc.R;
import com.cctv.ugc.activity.ChooseNewsTypeActivity;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.model.VideoItem;
import com.cctv.ugc.model.VideoLocalItem;
import com.cctv.ugc.util.DebugLog;
import com.cctv.ugc.util.MediaStoreUtils;
import com.cctv.ugc.util.forapp.AppFileUtils;
import com.cctv.ugc.util.forapp.AppUtils;
import com.cctv.ugc.util.forapp.TipsHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.File;


/**
 * Created by liuxin on 16/4/17.
 */
@EActivity(R.layout.fragment_create_news_1)
public class CreateNewsOneActivity extends BaseActivity {

    public static final int REQUEST_CODE_CHOSE = 1;
    public static final int REQUEST_CODE_CHOSE_SEC = 2;
    public static final int REQUEST_CODE_CHOSE_THIRD = 3;
    public static final int REQUEST_CODE_RECORD = 4;
    public static final int REQUEST_CODE_RECORD_SEC = 5;
    public static final int REQUEST_CODE_RECORD_THIRD = 6;

    protected Uri mMainData;

    @ViewById(R.id.mainImg)
    protected ImageView mMainImg;

    @ViewById(R.id.videoSize)
    protected TextView mMainVideoSize;

    @ViewById(R.id.videoPath)
    protected TextView mMainVideoPath;

    @ViewById(R.id.bottomBtn)
    protected Button bottomBtn;

    @ViewById(R.id.title)
    protected TextView title;

    @ViewById(R.id.secImg)
    protected ImageView mSecImg;

    @ViewById(R.id.secVideoSize)
    protected TextView mSecVideoSize;

    @ViewById(R.id.secVideoPath)
    protected TextView mSecVideoPath;

    @ViewById(R.id.thrImg)
    protected ImageView mThrImg;

    @ViewById(R.id.thrVideoSize)
    protected TextView mThrVideoSize;

    @ViewById(R.id.thrVideoPath)
    protected TextView mThrVideoPath;

    @ViewById(R.id.secParent)
    View mSecParent;

    @ViewById(R.id.thrParent)
    View mThrParent;

    @ViewById(R.id.videoSecondParent)
    View noVideoSecParent;

    @ViewById(R.id.videoThirdParent)
    View noVideoThrParent;

    private VideoLocalItem videoLocalItem;

    private Uri recordUri;

    @AfterViews
    protected void init() {
        initData();
        bottomBtn.setText("下一步");
        title.setText("上传视频（1/3）");


    }

    @Click({R.id.reChoose, R.id.secondChoose, R.id.thirdChoose, R.id.bottomBtn,
            R.id.btnleft, R.id.btnright, R.id.secReChoose, R.id.thrReChoose})
    protected void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.reChoose:
            case R.id.secondChoose:
            case R.id.secReChoose:
            case R.id.thirdChoose:
            case R.id.thrReChoose:
                showChooseDialog(id);
                break;
            case R.id.bottomBtn:
                Intent intent = new Intent(this, CreateNewsSecActivity_.class);
                intent.putExtra("videoLocalItem",videoLocalItem);
                startActivity(intent);
                break;
            case R.id.btnleft:
                finish();
                break;
            case R.id.btnright:
                break;
        }
    }

    public void refreshVideoInfo(Uri data, int requestCode) {
        String filePath = MediaStoreUtils.getRealFilePath(this,data);
        File file = new File(filePath);
//        createImg(filePath, requestCode);
        String fileSize = "(" + AppUtils.trasByteToM(file.length()) + "M)";
        switch (requestCode) {
            case REQUEST_CODE_CHOSE:
                mMainVideoSize.setText(fileSize);
                mMainVideoPath.setText(filePath);
                Glide.with(this).load(filePath).into(mMainImg);
                videoLocalItem.filepath[0]=filePath;
                break;
            case REQUEST_CODE_CHOSE_SEC:
                mSecParent.setVisibility(View.VISIBLE);
                noVideoSecParent.setVisibility(View.GONE);
                mSecVideoSize.setText(fileSize);
                mSecVideoPath.setText(filePath);
                Glide.with(this).load(filePath).into(mSecImg);
                videoLocalItem.filepath[1]=filePath;
                break;
            case REQUEST_CODE_CHOSE_THIRD:
                mThrParent.setVisibility(View.VISIBLE);
                noVideoThrParent.setVisibility(View.GONE);
                mThrVideoSize.setText(fileSize);
                mThrVideoPath.setText(filePath);
                Glide.with(this).load(filePath).into(mThrImg);
                videoLocalItem.filepath[2]=filePath;
                break;
        }
    }

    @Background
    protected void createImg(String videoPath, int requestCode) {
        Log.d(TAG, "videoPath=" + videoPath);
        Bitmap bitmap = AppUtils.getVideoThumbnail(videoPath);
        setImgBitmap(bitmap, requestCode);

    }

    @UiThread
    void setImgBitmap(Bitmap bitmap, int requestCode) {
        if (bitmap == null)
            return;
        switch (requestCode) {
            case REQUEST_CODE_CHOSE:
                mMainImg.setImageBitmap(bitmap);
                break;
            case REQUEST_CODE_CHOSE_SEC:
                mSecImg.setImageBitmap(bitmap);
                break;
            case REQUEST_CODE_CHOSE_THIRD:
                mThrImg.setImageBitmap(bitmap);
                break;
        }
    }


    boolean isHasSecVideo() {
        return false;
    }

    boolean isHasThrVideo() {
        return false;
    }

    public void chooseLocalVideo(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        try {
            this.startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            TipsHelper.showErrorTip(this, getString(R.string.error1));
        }

    }

    private void recordVideo(int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        try {
            File file = AppFileUtils.createRecordFile();
            recordUri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, recordUri);
            //0最差，1最好
            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            TipsHelper.showErrorTip(this, getString(R.string.error2));
        }
    }

    public void showChooseDialog(final int viewId) {
        final Dialog dialog = new Dialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_list, null);
        view.findViewById(R.id.first).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (viewId == R.id.reChoose) {
                    chooseLocalVideo(REQUEST_CODE_CHOSE);
                } else if (viewId == R.id.secondChoose || viewId == R.id.secReChoose) {
                    chooseLocalVideo(REQUEST_CODE_CHOSE_SEC);
                } else if (viewId == R.id.thirdChoose || viewId == R.id.thrReChoose) {
                    chooseLocalVideo(REQUEST_CODE_CHOSE_THIRD);
                }
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
        view.findViewById(R.id.sec).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (viewId == R.id.reChoose) {
                    recordVideo(REQUEST_CODE_CHOSE);
                } else if (viewId == R.id.secondChoose || viewId == R.id.secReChoose) {
                    recordVideo(REQUEST_CODE_CHOSE_SEC);
                } else if (viewId == R.id.thirdChoose || viewId == R.id.thrReChoose) {
                    recordVideo(REQUEST_CODE_CHOSE_THIRD);
                }
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
        view.findViewById(R.id.thr).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "requestCode=" + requestCode + ",resultCode=" + requestCode + ",Intent=" + (data != null ? data.toString() : data));
        if (!isDataValid(data)) {
            TipsHelper.showErrorTip(this, getString(R.string.error3));
            return;
        }
//        Uri data
//        switch(requestCode){
//            case REQUEST_CODE_RECORD:
//            case REQUEST_CODE_RECORD_SEC:
//            case REQUEST_CODE_RECORD_THIRD:
//
//                break;
//        }
        refreshVideoInfo(data.getData(), requestCode);
    }

    private boolean isDataValid(Intent data) {
        if (data != null && data.getData() != null)
            return true;
        else
            return false;
    }

    private void initData() {
        videoLocalItem = new VideoLocalItem();
        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        if (from.equals(ChooseNewsTypeActivity.class.getSimpleName())) {
            mMainData = intent.getParcelableExtra("filePath");
            Log.d(TAG, "file uri = " + mMainData.toString());
        }
        refreshVideoInfo(mMainData, REQUEST_CODE_CHOSE);
        if (isHasSecVideo()) {
            refreshVideoInfo(mMainData, REQUEST_CODE_CHOSE_SEC);
        }
        if (isHasThrVideo()) {
            refreshVideoInfo(mMainData, REQUEST_CODE_CHOSE_THIRD);
        }
    }
}
