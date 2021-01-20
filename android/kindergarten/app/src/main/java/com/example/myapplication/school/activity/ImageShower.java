package com.example.myapplication.school.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.main.util.ConfigUtil;
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
//        int resId = getResources().getIdentifier(tag, "mipmap", getPackageName());
//        mPhotoView.setImageResource(resId);
        Glide.with(ImageShower.this)
                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+tag)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.faliure)
                .into(mPhotoView);
        mAttacher.update();

    }

}
