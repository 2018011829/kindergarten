package com.example.myapplication.school.tool;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.main.util.ConfigUtil;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide.with(context)
                .load(ConfigUtil.SERVICE_ADDRESS+"imgs/schoolInfoPicture/"+path)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.faliure)
                .into(imageView);
    }
}
