package com.example.myapplication.teacher.tool.euclid;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.main.util.ConfigUtil;
import com.example.myapplication.teacher.bean.Teacher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EuclidListAdapter extends BaseAdapter {
    private Context context;
    private List<Teacher> teachers = new ArrayList<>();
//    下面已经有了R.layout.list_item
//    private int layoutResourceId;

    public static final String KEY_AVATAR = "avatar";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION_SHORT = "description_short";
    public static final String KEY_DESCRIPTION_FULL = "description_full";

    private final LayoutInflater mInflater;

    public EuclidListAdapter(Context context, int layoutResourceId, List<Teacher> teachers) {
        this.context = context;
        this.teachers = teachers;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return teachers==null?0:teachers.size();
    }

    @Override
    public Object getItem(int i) {
        return teachers==null?null:teachers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.euclid_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mViewOverlay = convertView.findViewById(R.id.view_avatar_overlay);
            viewHolder.mListItemAvatar = convertView.findViewById(R.id.image_view_avatar);
            viewHolder.mListItemName = convertView.findViewById(R.id.text_view_name);
            viewHolder.mListItemDescription = convertView.findViewById(R.id.text_view_description);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(ConfigUtil.SERVICE_ADDRESS + "pics/"+teachers.get(position).getPicture())
                .resize(EuclidActivity.sScreenWidth, EuclidActivity.sProfileImageHeight).centerCrop()
                .placeholder(R.color.blue)
                .into(viewHolder.mListItemAvatar);
        //姓名
        viewHolder.mListItemName.setText(teachers.get(position).getName());
        //职位
        viewHolder.mListItemDescription.setText(teachers.get(position).getPosition());
        viewHolder.mViewOverlay.setBackground(EuclidActivity.sOverlayShape);
        return convertView;
    }

    static class ViewHolder {
        View mViewOverlay;
        ImageView mListItemAvatar;
        TextView mListItemName;
        TextView mListItemDescription;
    }
}

