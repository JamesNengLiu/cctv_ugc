package com.cctv.ugc.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cctv.ugc.R;
import com.cctv.ugc.account.LoginManager;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.model.VideoItem;
import com.cctv.ugc.model.VideoLocalItem;
import com.cctv.ugc.util.UserUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by liuxin on 16/4/17.
 */
@EActivity(R.layout.fragment_create_news_2)
public class CreateNewsSecActivity extends BaseActivity {

    @ViewById(R.id.bottomBtn)
    protected Button bottomBtn;

    @ViewById(R.id.title)
    protected TextView title;

    @ViewById(R.id.vTitle)
    EditText vTitle;

    @ViewById(R.id.phoneNum)
    EditText phoneNum;

    @ViewById(R.id.vSummary)
    EditText vSummary;

    @ViewById(R.id.rememberPhone)
    CheckBox remember;

    @AfterViews
    protected void init() {
        bottomBtn.setText("下一步");
        title.setText("上传视频（2/3）");
        String phoneNumText = getSavedPhoneNum();
        if (!TextUtils.isEmpty(phoneNumText)) {
            phoneNum.setText(phoneNumText);
        }
    }

    @Click({R.id.reChoose, R.id.secondChoose, R.id.thirdChoose, R.id.bottomBtn, R.id.btnleft})
    protected void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.reChoose:
                break;
            case R.id.secondChoose:
                break;
            case R.id.thirdChoose:
                break;
            case R.id.bottomBtn:
                Editable phoneNumText = phoneNum.getText();
                if (!TextUtils.isEmpty(phoneNumText.toString()) && remember.isChecked()) {
                    trySavePhoneNum(phoneNumText.toString());
                }

                Intent intent = new Intent(this, CreateNewsUploadingActivity_.class);
                //将之前的数据直接塞进下一个页面
                Bundle bundle = getIntent().getExtras();
                bundle.putParcelable("videoItem", createVideoItem());
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.btnleft:
                finish();
                break;

        }
    }

    private VideoItem createVideoItem() {
        VideoItem item = new VideoItem();
        item.setCard(LoginManager.getInstance().getIdentity());
        String title = vTitle.getText() == null ? null : vTitle.getText().toString();
        item.setTitle(title);
        String summary = vSummary.getText() == null ? null : vSummary.getText().toString();
        item.setSummary(summary);
        String moblie = phoneNum.getText() == null ? null : phoneNum.getText().toString();
        item.setMobile(moblie);
        return item;
    }

    private void trySavePhoneNum(String phoneNum) {
        UserUtils.saveUploadPhoneNum(phoneNum);
    }

    private String getSavedPhoneNum() {
        return UserUtils.getSavedPhoneNum();
    }
}
