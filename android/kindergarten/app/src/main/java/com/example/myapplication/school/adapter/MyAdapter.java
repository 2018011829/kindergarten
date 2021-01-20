package com.example.myapplication.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.main.util.ConfigUtil;
import com.example.myapplication.school.activity.SchoolActivity;
import com.example.myapplication.school.tool.GetMipmapName;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private int layoutRes;
    private List<String> imgs;

    public MyAdapter(Context mContext,int layoutRes,List<String> imgs){
        this.imgs=imgs;
        this.layoutRes=layoutRes;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return imgs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(mContext).inflate(layoutRes,null);
        }
        ImageView imageView=view.findViewById(R.id.item_img);
        imageView.setTag(imgs.get(i));
        Glide.with(mContext)
                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+imgs.get(i))
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.faliure)
                .into(imageView);
//        Toast.makeText(mContext,
//                new GetMipmapName().getIdName(imgs.get(i)),
//                Toast.LENGTH_SHORT).show();

        return view;
    }
}
