package com.example.myapplication.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.money.activity.Attendance;
import com.example.myapplication.money.activity.Money;
import com.example.myapplication.money.activity.UploadPicture;

public class NewFragment extends Fragment {
    private View view;
    private Button btnAttendance;
    private Button btnMoney;
    private Button btnUploadPicture;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new,container,false);
        // 初始化控件
        initView();
        // 设置点击事件的监听器
        setListeners();

        return view;
    }

    // 初始化控件
    private void initView() {
        btnAttendance = view.findViewById(R.id.btn_attendance);
        btnMoney = view.findViewById(R.id.btn_money);
        btnUploadPicture = view.findViewById(R.id.btn_upload_picture);
    }
    // 设置点击事件的监听器
    private void setListeners() {
        MyListener myListener = new MyListener();
        btnAttendance.setOnClickListener(myListener);
        btnMoney.setOnClickListener(myListener);
        btnUploadPicture.setOnClickListener(myListener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_attendance:
                    Intent intent = new Intent();
                    intent.setClass(getContext(), Attendance.class);
                    startActivity(intent);
                    break;
                case R.id.btn_money:
                    Intent intent1 = new Intent();
                    intent1.setClass(getContext(), Money.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_upload_picture:
                    Intent intent2 = new Intent();
                    intent2.setClass(getContext(), UploadPicture.class);
                    startActivity(intent2);
                    break;
            }

        }
    }
}
