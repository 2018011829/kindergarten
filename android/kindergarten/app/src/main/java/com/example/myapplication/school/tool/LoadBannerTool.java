package com.example.myapplication.school.tool;

import com.youth.banner.Banner;

import java.util.List;

public class LoadBannerTool {

    /**
     * 加载轮播图
     */
    public static void startBanner(Banner banner, List<String> images) {
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
