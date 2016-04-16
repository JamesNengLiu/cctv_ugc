package com.cctv.ugc.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cctv.ugc.R;
import com.cctv.ugc.Util.DateUtils;
import com.cctv.ugc.model.VideoListItem;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.video_list_item)
public class VideoListItemView extends RelativeLayout {


    @ViewById(R.id.image)
    ImageView image;

    @ViewById(R.id.title)
    TextView title;

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.play_count)
    TextView playCount;

    @ViewById(R.id.image_count)
    TextView imageCount;


    public VideoListItemView(Context context) {
        super(context);
    }


    public VideoListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindView(int position, VideoListItem item) {
        if (item != null) {
            Glide.with(getContext()).load(item.getThumbnail()).placeholder(R.mipmap.list_image_placeholder).centerCrop().into(image);
            title.setText(item.getTitle());
            date.setText(DateUtils.parseDate(item.getPubTime()));
            playCount.setText(item.getCollectCount()+"");
            imageCount.setText(item.getReadCount()+"");
        }
    }


}
