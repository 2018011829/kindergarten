package com.example.myapplication.teacher.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.example.myapplication.R;
import com.example.myapplication.main.util.ChangeStatusBarColor;
import com.example.myapplication.teacher.bean.Teacher;
import com.example.myapplication.teacher.tool.euclid.EuclidActivity;
import com.example.myapplication.teacher.tool.euclid.EuclidListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends EuclidActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_teacher_activtiy);
    }

    @Override
    protected BaseAdapter getAdapter() {
       
        Intent intent = getIntent();
        String result = intent.getStringExtra("results");
        List<Teacher> profilesList = new ArrayList<>();
        if (result!=null){

            //解析获取到的字符串
            Type type =new TypeToken<List<Teacher>>(){}.getType();
            //2.反序列化
            profilesList = new Gson().fromJson(result,type);
        }


        return new EuclidListAdapter(this, R.layout.euclid_list_item, profilesList);
    }

}