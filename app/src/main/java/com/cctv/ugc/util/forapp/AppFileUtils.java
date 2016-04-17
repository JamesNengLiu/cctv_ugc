package com.cctv.ugc.util.forapp;

import android.os.Environment;

import com.cctv.ugc.account.LoginManager;

import java.io.File;
import java.io.IOException;

/**
 * Created by liuxin on 16/4/16.
 */
public class AppFileUtils {

    private static final String RECORD_FILE_DIR = Environment.getExternalStorageDirectory() + "/recordVideo/";

    static {
        checkToCreateRecordFileDir();
    }

    public static String createRecordFileSimpleName() {
        return LoginManager.getInstance().getIdentity() + System.currentTimeMillis()+".mp4";
    }

    public static File createRecordFile() throws IOException {
        String path = getRecordFileDir() + "/" + createRecordFileSimpleName();
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        return file;
    }

    public static String getRecordFileDir() {
        return RECORD_FILE_DIR;
    }

    private static void checkToCreateRecordFileDir() {
        File file = new File(RECORD_FILE_DIR);
        if (!file.isDirectory() || !file.exists()) {
            file.mkdir();
        }
    }
}
