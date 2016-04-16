package com.cctv.ugc;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cctv.ugc.fileuploader.FileUploaderManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String []permissions = new String[]{"android.permission.READ_EXTERNAL_STORAGE","android.permission.MOUNT_UNMOUNT_FILESYSTEMS"};
        ActivityCompat.requestPermissions(this,permissions,200);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filePath = Environment.getExternalStorageDirectory()+"/"+"test3";
                new FileUploaderManager().startUpload(new File(filePath));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
