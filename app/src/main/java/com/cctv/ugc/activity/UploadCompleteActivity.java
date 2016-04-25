package com.cctv.ugc.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cctv.ugc.R;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.util.ImageUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by liuxin on 16/4/25.
 */
@EActivity(R.layout.activity_upload_complete)
public class UploadCompleteActivity extends BaseActivity {


    @ViewById(R.id.uploadedDes)
    TextView uploadedDes;

    @ViewById(R.id.title)
    TextView title;

    @ViewById(R.id.btnleft)
    ImageView btnLeft;

    @AfterViews
    void init() {
        title.setText("上传完成");
        btnLeft.setImageResource(R.drawable.img_close);
    }

    @Click({R.id.continueUpload, R.id.back, R.id.btnleft})
    protected void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.continueUpload:
                Bitmap bitmap = ImageUtils.applyBlur(getWindow());
                Intent intent = new Intent(this, ChooseNewsTypeActivity_.class);
                intent.putExtra("bitmap", bitmap);
                startActivity(intent);
                break;
            case R.id.back:
            case R.id.btnleft:
                Intent intentBack = new Intent(this, HomeActivity_.class);
                intentBack.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentBack);
                break;
        }

    }
}
