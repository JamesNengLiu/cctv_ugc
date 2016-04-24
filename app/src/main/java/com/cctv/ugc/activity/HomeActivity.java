package com.cctv.ugc.activity;

import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.cctv.ugc.R;
import com.cctv.ugc.base.BaseActivity;
import com.cctv.ugc.fragment.VideoListFragment;
import com.cctv.ugc.util.UserUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    @ViewById(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @ViewById(R.id.all)
    View layoutAll;

    @ViewById(R.id.uploaded)
    View layoutUploaded;

    @ViewById(R.id.prepared)
    View layoutPrepared;

    @ViewById(R.id.setting)
    View layoutSetting;

    @ViewById(R.id.about)
    View layoutAbout;

    @ViewById(R.id.logout)
    View layoutLogout;

    @FragmentById(R.id.video_list_fragment)
    VideoListFragment videoListFragment;

    @ViewById(R.id.username)
    TextView mUserName;

    View[] items;

    @AfterViews
    void init(){
        mUserName.setText(UserUtils.getUserName());
        layoutAll.setSelected(true);
        videoListFragment.loadAllVideo();
        items = new View[]{layoutAll, layoutUploaded, layoutPrepared};
    }


    @Click(R.id.icon_navi)
    void onNaviClick(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    void resetNaviStatus(View view){
        for(View v: items){
            v.setSelected(v == view);
        }
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Click(R.id.all)
    void onAllClick(View view){
        videoListFragment.loadAllVideo();
        resetNaviStatus(view);
    }

    @Click(R.id.uploaded)
    void onUploadedClick(View view){
        videoListFragment.loadUploadedVideo();
        resetNaviStatus(view);
    }

    @Click(R.id.prepared)
    void onPreparedClick(View view){
        videoListFragment.loadPreparedVideo();
        resetNaviStatus(view);
    }

}
