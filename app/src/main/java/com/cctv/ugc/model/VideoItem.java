package com.cctv.ugc.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.io.File;

/**
 * Created by liuxin on 16/4/6.
 * card：记者证号(用93709377)，刚才验证的card，这个参数要求跟在URL里，这样后台在查询访问记录的时候可以直接提取
 * title：标题
 * summary：摘要
 * mobile：手机号
 * filename：原始文件名，以数组方式上传
 * descfilename：上传的视频目标文件名(2.2接口返回的文件名)，以数组方式上传
 * images：上传的图片目录文件名(2.2接口返回的文件名)，以数组方式上传
 */
public class VideoItem implements Parcelable {

    private String card;
    private String title;
    private String summary;
    private String mobile;
    private String[] filename = new String[3];
    private String[] descfilename = new String[3];
    private String[] images = new String[3];

    public VideoItem() {

    }

    protected VideoItem(Parcel in) {
        card = in.readString();
        title = in.readString();
        summary = in.readString();
        mobile = in.readString();
        filename = in.createStringArray();
        descfilename = in.createStringArray();
        images = in.createStringArray();
    }

    public static final Creator<VideoItem> CREATOR = new Creator<VideoItem>() {
        @Override
        public VideoItem createFromParcel(Parcel in) {
            return new VideoItem(in);
        }

        @Override
        public VideoItem[] newArray(int size) {
            return new VideoItem[size];
        }
    };

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (TextUtils.isEmpty(title))
            return;
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        if (TextUtils.isEmpty(summary))
            return;
        this.summary = summary;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        if (TextUtils.isEmpty(mobile))
            return;
        this.mobile = mobile;
    }

    public String[] getFilename() {
        return filename;
    }

    public void setFilename(String[] filename) {
        this.filename = filename;
    }

    public String[] getDescfilename() {
        return descfilename;
    }

    public void setDescfilename(String[] descfilename) {
        this.descfilename = descfilename;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(card);
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(mobile);
        dest.writeStringArray(filename);
        dest.writeStringArray(descfilename);
        dest.writeStringArray(images);
    }


    public static String[] getFileNames(String[] filePaths) {
        String[] fileNames = new String[filePaths.length];
        for (int i = 0; i < filePaths.length; i++) {
            String filePath = filePaths[i];
            if (TextUtils.isEmpty(filePath))
                continue;
            File file = new File(filePath);
            fileNames[i] = file.getName();
        }
        return fileNames;
    }
}
