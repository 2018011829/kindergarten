package com.example.myapplication.school.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class ImageShower extends Activity {

    private PhotoView mPhotoView;
    private PhotoViewAttacher mAttacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower);

        mPhotoView=findViewById(R.id.big_img);
        mAttacher=new PhotoViewAttacher(mPhotoView);

        Intent intent=getIntent();
        String tag=intent.getStringExtra("tag");
        int resId = getResources().getIdentifier(tag, "mipmap", getPackageName());
        mPhotoView.setImageResource(resId);
        mAttacher.update();

    }

}
