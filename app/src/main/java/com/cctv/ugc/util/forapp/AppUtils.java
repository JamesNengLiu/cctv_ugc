package com.cctv.ugc.util.forapp;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

/**
 * Created by liuxin on 16/4/17.
 */
public class AppUtils {

    public static String trasByteToM(long src) {
        int des = (int) (src /= 1024 * 1024);
        return String.valueOf(des);
    }

    public static Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
