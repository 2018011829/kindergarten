package com.example.myapplication.school.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private int layoutRes;
    private List<Integer> imgs;

    public MyAdapter(Context mContext,int layoutRes,List<Integer> imgs){
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
        imageView.setImageResource((Integer) getItem(i));

        return view;
    }
}
