package com.cctv.ugc.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.cctv.ugc.R;
import com.cctv.ugc.activity.ChooseNewsTypeActivity_;
import com.cctv.ugc.model.Response.VideoTimelineResponse;
import com.cctv.ugc.model.VideoListItem;
import com.cctv.ugc.network.UgcClient;
import com.cctv.ugc.network.UgcClient_;
import com.cctv.ugc.view.adapter.VideoListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.IgnoredWhenDetached;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EFragment(R.layout.fragment_video_list)
public class VideoListFragment extends Fragment {

    @ViewById(R.id.list)
    ListView mListView;

    @Bean(VideoListAdapter.class)
    VideoListAdapter mAdapter;

    @ViewById(R.id.progress)
    ProgressBar mProgress;

    UgcClient mClient;

    @AfterViews
    void initView(){
        mListView.setAdapter(mAdapter);
        fetchTimeline();
    }

    @Background
    void fetchTimeline(){
        mClient = UgcClient_.getInstance_(getContext());
        VideoTimelineResponse response = mClient.fetchVideoTimeline(0, 20);
        if(response.ok()){
            updateView(response.getData().getBody());
        }
    }

    @UiThread
    @IgnoredWhenDetached
    void updateView(List<VideoListItem> list){
        mProgress.setVisibility(View.GONE);
        mAdapter.update(list);
    }

    @Click(R.id.submit)
    void clickToUploadVideo(){
        getActivity().startActivity(new Intent(getActivity(), ChooseNewsTypeActivity_.class));
    }

}
