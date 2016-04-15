package com.cctv.ugc.fileuploader;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cctv.ugc.App;
import com.cctv.ugc.account.LoginManager;
import com.cctv.ugc.net.ServerApi;

import net.gotev.uploadservice.BinaryUploadRequest;
import net.gotev.uploadservice.UploadService;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.Callable;

import bolts.Continuation;
import bolts.ExtExecutors;
import bolts.Task;

/**
 * Created by liuxin on 16/4/6.
 */
public class FileUploaderManager {

    public String mRootDir;
    public static final String FILE_CHUNK_ROOT = "chunks";
    private Context mCtx;

    public FileUploaderManager() {
        mCtx = App.getAppContext();
        mRootDir = mCtx.getExternalFilesDir(FILE_CHUNK_ROOT).getAbsolutePath();
    }

    /**
     * card：记者证号(用93709377)，刚才验证的card，这个参数要求跟在URL里，这样后台在查询访问记录的时候可以直接提取
     * callback：jsonp用法
     * name：上传的文件名（如果有特殊要求的，可以上传这个参数，如果没有特殊要求，系统会自动使用上传的文件名。）
     * chunk：第几个分片，从0开始
     * chunks：总分片数
     *
     * @param file
     */
    public void startUpload(final File file) {
        final FileChunkControler controler = new FileChunkControler(mRootDir);
        if (controler.isChunkFilesExist(file.getName())) {

            Task.call(new Callable<List<File>>() {
                @Override
                public List call() throws Exception {
                    File[] fileChunks = controler.getChunkFileDir(file.getName()).listFiles();
                    int len = fileChunks.length;
                    for (int i = 0; i < len; i++) {
                        File fileChunk = fileChunks[i];
                        BinaryUploadRequest request = new BinaryUploadRequest(mCtx, file.getName(), ServerApi.API_UPLOAD_FILE);
                        request.setFileToUpload(fileChunk.getAbsolutePath());
                        request.addParameter("card", LoginManager.getInstance().getIdentity());
                        request.addParameter("name", file.getName());
                        request.addParameter("chunk", String.valueOf(i));
                        request.addParameter("chunks", String.valueOf(len));
                        request.startUpload();
                    }
                    return null;
                }
            },ExtExecutors.BACKGROUND_THREAD);
            }else {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    List<File> fileChunks = controler.makeChunks(file);
                    int len = fileChunks.size();
                    for (int i = 0; i < len; i++) {
                        File fileChunk = fileChunks.get(i);
                        BinaryUploadRequest request = new BinaryUploadRequest(mCtx, file.getName(), ServerApi.API_UPLOAD_FILE);
                        try {
                            request.setFileToUpload(fileChunk.getAbsolutePath());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        request.addParameter("card", LoginManager.getInstance().getIdentity());
                        request.addParameter("name", file.getName());
                        request.addParameter("chunk", String.valueOf(i));
                        request.addParameter("chunks", String.valueOf(len));
                        try {
                            request.startUpload();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
//            Task task = Task.call(new Callable<List<File>>() {
//                @Override
//                public List call() throws Exception {
//                    return controler.makeChunks(file);
//                }
//            }, ExtExecutors.BACKGROUND_THREAD).onSuccess(new Continuation<List<File>, Object>() {
//                @Override
//                public Object then(Task<List<File>> task) throws Exception {
//                    List<File> fileChunks = task.getResult();
//                    int len = fileChunks.size();
//                    for (int i = 0; i < len; i++) {
//                        File fileChunk = fileChunks.get(i);
//                        BinaryUploadRequest request = new BinaryUploadRequest(mCtx, file.getName(), ServerApi.API_UPLOAD_FILE);
//                        request.setFileToUpload(fileChunk.getAbsolutePath());
//                        request.addParameter("card", LoginManager.getInstance().getIdentity());
//                        request.addParameter("name", file.getName());
//                        request.addParameter("chunk", String.valueOf(i));
//                        request.addParameter("chunks", String.valueOf(len));
//                        request.startUpload();
//                    }
//                    return null;
//                }
//            });
//            Log.e("lxtest", "error", task.getError());
        }
    }


    public void stopUpload(File file) {
        UploadService.stopUpload(file.getName());
    }

    public void stopAllUpload() {
        UploadService.stopAllUploads();
    }
}
