package com.example.myapplication.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.apply.activity.ApplyActivityBaby;
import com.example.myapplication.main.util.ConfigUtil;
import com.example.myapplication.more.activity.MoreActivity;
import com.example.myapplication.school.activity.SchoolActivity;
import com.example.myapplication.teacher.activity.TeacherActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeFragment extends Fragment implements OnBannerListener {

    private View view;
    private Banner homeBanner;
    private LinearLayout linearToApply;   //报名入口
    private LinearLayout linearToSchool;  //学校简介
    private LinearLayout linearToTeacher; //教师风采
    private LinearLayout linearToMore;    //了解更多

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        // 初始化控件
        initView();
        // 设置点击事件的监听器
        setListeners();

        return view;
    }

    // 设置点击事件的监听器
    private void setListeners() {
        MyListener myListener = new MyListener();
        linearToApply.setOnClickListener(myListener);
        linearToSchool.setOnClickListener(myListener);
        linearToTeacher.setOnClickListener(myListener);
        linearToMore.setOnClickListener(myListener);
    }

    // 初始化控件
    private void initView() {
        linearToApply = view.findViewById(R.id.linear_to_apply);
        linearToSchool = view.findViewById(R.id.linear_to_school);
        linearToTeacher = view.findViewById(R.id.linear_to_teacher);
        linearToMore = view.findViewById(R.id.linear_to_more);
        homeBanner = view.findViewById(R.id.home_banner);
    }

    // 设置轮播图的点击事件
    @Override
    public void OnBannerClick(int position) {

    }

    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.linear_to_apply:
                    Intent intent0 = new Intent();
                    intent0.setClass(getContext(), ApplyActivityBaby.class);
                    startActivity(intent0);
                    break;
                case R.id.linear_to_school:
                    Intent intent1 = new Intent();
                    intent1.setClass(getContext(), SchoolActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.linear_to_teacher:
                    new Thread() {
                        @Override
                        public void run() {
                            //发送到服务端
                            try {
                                URL url = new URL(ConfigUtil.SERVICE_ADDRESS + "getAllTeacherMessage.action" );
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setRequestMethod("POST");
                                //获取网络输入流
                                InputStream in = conn.getInputStream();
                                //使用字符流读取
                                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                                //读取返回的数据 json串
                                String results = reader.readLine();

                                Intent intent = new Intent(getContext(), TeacherActivity.class);
                                intent.putExtra("results", results);
                                Log.e("results", results);
                                startActivity(intent);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                    break;
                case R.id.linear_to_more:
                    Intent intent3 = new Intent();
                    intent3.setClass(getContext(), MoreActivity.class);
                    startActivity(intent3);
                    break;
            }
        }
    }
}
