package com.cctv.ugc.Util;

import android.util.Log;

public class DebugLog {

    public static boolean DBG = true;

    public static void log(String tag, String msg) {
        if (DBG) {
            Log.d(tag, msg);
        }
    }

    public static void log(String tag, String msg, Throwable thr) {
        if (DBG) {
            Log.d(tag, msg, thr);
        }
    }
}
