package com.example.myapplication.main.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.login.LoginByPasswordActivity;
import com.example.myapplication.main.util.ChangeStatusBarColor;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_logout;
    private TextView tv_phone;
    private Intent intent;
    private RelativeLayout rl_password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);
        ChangeStatusBarColor.initSystemBar(this);

        findView();
        setListener();
    }

    private void setListener() {
        btn_logout.setOnClickListener(this);
        rl_password.setOnClickListener(this);
    }

    private void findView() {
        intent = getIntent();
        btn_logout = findViewById(R.id.btn_mine_logout);
        tv_phone = findViewById(R.id.tv_phone);
        rl_password = findViewById(R.id.rl_et_password);
        tv_phone.setText(intent.getStringExtra("userPhone").replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mine_logout:
                //跳转登录到登录activity之前，清空所有的活动栈，否则一直返回会返回到上一个用户的登录状态
                startActivity(new Intent(SettingActivity.this, LoginByPasswordActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.rl_et_password:
                startActivity(new Intent(SettingActivity.this, EditorPasswordActivity.class));
                break;
        }
    }
}
