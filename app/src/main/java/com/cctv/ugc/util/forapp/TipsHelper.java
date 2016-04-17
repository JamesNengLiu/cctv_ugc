package com.cctv.ugc.util.forapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liuxin on 16/4/16.
 */
public class TipsHelper {

    public static void showErrorTip(Context ctx,String errorStr){
        Toast.makeText(ctx,errorStr,Toast.LENGTH_SHORT).show();
    }
}
