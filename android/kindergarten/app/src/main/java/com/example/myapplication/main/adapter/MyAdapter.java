package com.example.myapplication.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.example.myapplication.main.activity.my.ShowApplyInfoActivity;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private int layoutRes;
    private List<ApplyInfo> data;

    public MyAdapter(Context mContext, int layoutRes, List<ApplyInfo> data) {
        this.mContext = mContext;
        this.layoutRes = layoutRes;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(mContext).inflate(layoutRes,null);
            holder.iv=view.findViewById(R.id.item_applyinfo_baby_iv);
            holder.tvBabyName=view.findViewById(R.id.item_applyinfo_baby_name);
            holder.tvBabySex=view.findViewById(R.id.item_applyinfo_baby_sex);
            holder.tvBabyBirthday=view.findViewById(R.id.item_applyinfo_baby_birthday);
            holder.linearLayout=view.findViewById(R.id.item_applyinfo);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        ApplyInfo applyInfo=data.get(i);
        holder.tvBabyName.setText(applyInfo.getBabyName());
        holder.tvBabySex.setText(applyInfo.getBabySex());
        holder.tvBabyBirthday.setText(applyInfo.getBabyBirthday());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//点击某一个孩子的信息，查看详情信息
                //将id传递给下一个activity
                Intent intent=new Intent(mContext, ShowApplyInfoActivity.class);
                intent.putExtra("applyId",applyInfo.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    static class ViewHolder{
        ImageView iv;
        TextView tvBabyName;
        TextView tvBabySex;
        TextView tvBabyBirthday;
        LinearLayout linearLayout;
    }
}
