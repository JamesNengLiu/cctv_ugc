package com.cctv.ugc.fileuploader;

import com.lx.utils.FileUitls;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuxin on 16/4/7.
 */
public class FileChunkControler {

    public static final int FILE_CHUNK_SIZE = 4096 * 1024;

    private String mDirRoot;

    public FileChunkControler(String dirRoot) {
        mDirRoot = dirRoot;
    }

    public List<File> makeChunks(File file) {
        List<File> chunkList = new ArrayList<>();
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            // TODO: 16/4/15 buffer is too large
            byte[] buffer = new byte[FILE_CHUNK_SIZE];
            int index = 0;
            int count = 0;
            File fileDir = getChunkFileDir(file.getName());
            fileDir.mkdir();
            while ((count = bufferedInputStream.read(buffer)) != -1) {
                String filePath = fileDir.getPath() + "/" + index;
                File chunkFile = new File(filePath);
                chunkFile.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(chunkFile);
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                bufferedOutputStream.write(buffer, 0, count);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                chunkList.add(chunkFile);
                index++;
            }
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        }finally {
//            FileUitls.closeStream(bufferedInputStream);
//            FileUitls.closeStream(bufferedOutputStream);
//        }
        return chunkList;
    }

    /**
     * 我们认为只要是上传文件的目录在，则认为切片文件在
     *
     * @param
     * @return
     */
    public boolean isChunkFilesExist(String uploadFileName) {
        return getChunkFileDir(uploadFileName).exists();
    }

    public File getChunkFileDir(String uploadFileName) {
        return new File(mDirRoot + "/" + uploadFileName);
    }

}
