package com.cctv.ugc.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v4.widget.TextViewCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cctv.ugc.R;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.model.Response.LoginResponse;
import com.cctv.ugc.network.UgcClient;
import com.cctv.ugc.network.UgcClient_;
import com.cctv.ugc.util.UserUtils;
import com.cctv.ugc.util.forapp.TipsHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.IgnoredWhenDetached;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    final float LOGO_SCALE_TO = 0.6f;

    @ViewById(R.id.login_form)
    LinearLayout mLoginForm;

    @ViewById(R.id.login_logo)
    ImageView mLogo;

    @ViewById(R.id.login_name)
    EditText mUsername;

    @ViewById(R.id.login_password)
    EditText mPassword;

    @ViewById(R.id.eye)
    ImageButton mButtonEye;

    ProgressDialog mProgressDialog;

    private int mInvisibleVariation;
    private int mVisibleVariation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!TextUtils.isEmpty(UserUtils.getUserId())){
            jumpToMainActivity();
        }
//        else{
//            super.onCreate(savedInstanceState);
//        }
    }

    @AfterViews
    void init() {
        mInvisibleVariation = mPassword.getInputType();
        mVisibleVariation = mInvisibleVariation | EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
        mUsername.setText("93709377");
        mPassword.setText("1");
        playLogoAnimation();
        setEyeOpen(false);
    }

    @UiThread
    void playLogoAnimation() {
        final TimeInterpolator interpolator = new LinearOutSlowInInterpolator();

        int translateY = getResources().getDimensionPixelSize(R.dimen.login_logo_traslate_y);
        int duration = getResources().getInteger(R.integer.login_logo_anim_duration);

        mLogo.animate().scaleY(LOGO_SCALE_TO).scaleX(LOGO_SCALE_TO)
                .translationY(translateY)
                .setDuration(duration)
                .setInterpolator(interpolator)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        mLoginForm.setAlpha(0.3f);
                        mLoginForm.setVisibility(View.VISIBLE);
                        mLoginForm.animate().setDuration(300).setInterpolator(interpolator).alpha(1f).start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                }).start();
    }

    @Click(R.id.login_btn)
    void loginClick(){
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            toastMessage(getString(R.string.login_fail_username));
            return;
        }
        if(TextUtils.isEmpty(password)){
            toastMessage(getString(R.string.login_fail_password));
            return;
        }
        login(username, password);
    }

    @Background
    void login(String username, String password){
        showProgress();
        UgcClient client = UgcClient_.getInstance_(getApplicationContext());
        LoginResponse response = client.login(username, password);
        dismissProgress();
        if(response.ok()){
            UserUtils.saveUserId(username);
            UserUtils.saveUserNmae(response.getUsername());
            jumpToMainActivity();
        }else{
            String loginError = !TextUtils.isEmpty(response.getDesc())? response.getDesc(): getString(R.string.login_fail_network);
            toastMessage(loginError);
        }
    }

    void jumpToMainActivity(){
        startActivity(new Intent(this, HomeActivity_.class));
        if(!isFinishing()){
            finish();
        }
    }

    @UiThread
    void toastMessage(String message){
        TipsHelper.showErrorTip(this, message);
    }

    @UiThread
    void showProgress(){
        if(mProgressDialog == null){
            mProgressDialog = ProgressDialog.show(this, null, null, false, false);
        }
        if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    @UiThread
    void dismissProgress(){
        if(mProgressDialog!= null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Click(R.id.eye)
    void onEyeClick(){
        setEyeOpen(!mButtonEye.isSelected());
    }

    void setEyeOpen(boolean open){
        mButtonEye.setSelected(open);
        mPassword.setInputType(open? mVisibleVariation: mInvisibleVariation);
    }
}
