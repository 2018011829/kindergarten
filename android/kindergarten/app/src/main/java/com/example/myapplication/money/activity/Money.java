package com.example.myapplication.money.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.main.fragment.NewFragment;

public class Money extends AppCompatActivity {
    private ImageView returnNew;
    private Button btnReturn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_money);
        initView();
        setListeners();
    }

    // 初始化控件
    @SuppressLint("WrongViewCast")
    private void initView() {
        returnNew = findViewById(R.id.return_new);
        btnReturn = findViewById(R.id.btn_return);
    }
    // 设置点击事件的监听器
    private void setListeners() {
        MyListener myListener = new MyListener();
        returnNew.setOnClickListener(myListener);
        btnReturn.setOnClickListener(myListener);
    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.return_new:
                    finish();
                    break;
                case R.id.btn_return:
                    finish();
                    break;
            }

        }
    }
}
