package com.cctv.ugc.model;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by liuxin on 16/4/6.
 */
public class VideoItem extends DataSupport{

    private int textid;

    public void setTextid(int textid) {
        this.textid = textid;
    }

    public int getTextid() {
        return textid;
    }
}
