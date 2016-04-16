package com.cctv.ugc.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cctv.ugc.model.VideoListItem;
import com.cctv.ugc.view.VideoListItemView;
import com.cctv.ugc.view.VideoListItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class VideoListAdapter extends BaseAdapter{

    List<VideoListItem> mList;

    @RootContext
    Context context;

    public VideoListAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return mList !=null? mList.size():0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoListItemView item;
        if(convertView == null){
            item = VideoListItemView_.build(context);
        }else{
            item = (VideoListItemView) convertView;
        }
        item.bindView(position, getItem(position));
        return item;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public VideoListItem getItem(int position) {
        return mList != null && position > -1 && position < mList.size()? mList.get(position): null;
    }

    public void update(List<VideoListItem> summaries){
        if (summaries == null) return;
        if (mList == null) {
            mList = new ArrayList<>();
        } else {
            mList.clear();
        }
        mList.addAll(summaries);
        notifyDataSetChanged();
    }


    public void appendList(List<VideoListItem> list) {
        if (list == null) return;
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
