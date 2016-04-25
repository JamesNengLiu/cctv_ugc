package com.cctv.ugc.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liuxin on 16/4/24.
 */
public class VideoLocalItem implements Parcelable{

    public String[] filepath = new String[3];
    public String[] images = new String[3];

    public VideoLocalItem(){

    }

    protected VideoLocalItem(Parcel in) {
        filepath = in.createStringArray();
        images = in.createStringArray();
    }

    public static final Creator<VideoLocalItem> CREATOR = new Creator<VideoLocalItem>() {
        @Override
        public VideoLocalItem createFromParcel(Parcel in) {
            return new VideoLocalItem(in);
        }

        @Override
        public VideoLocalItem[] newArray(int size) {
            return new VideoLocalItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(filepath);
        dest.writeStringArray(images);
    }
}
