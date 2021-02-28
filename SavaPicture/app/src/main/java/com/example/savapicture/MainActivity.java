package com.example.savapicture;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView img;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.img);

        File directory_doc = Environment.getExternalStoragePublicDirectory(Environment. DIRECTORY_DOCUMENTS);
        //使用这个方法需要传入公共目录的类型如Environment.DIRECTORY_DOCUMENTS
        //查看公共目录文档文件的路径

        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "dearxy";
        Log.e("main","得到的公共目录:"+storePath);
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                img.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                img.buildDrawingCache();
                Bitmap mBitmap = img.getDrawingCache();
                try {
                FileOutputStream fos = new FileOutputStream(storePath);//getPath是自己封装的一个路径
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}