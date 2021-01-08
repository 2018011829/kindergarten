package com.example.myapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main.activity.MainActivity;
import com.example.myapplication.main.util.ChangeStatusBarColor;

public class LoginByPhoneActivity extends AppCompatActivity {
    private final static String TAG = "LoginByPhoneActivity";

    private EditText etPhoneNum;
    private EditText etCheckNum;
    private Button btnGetCheckNum;
    private Button btnLogin;
    private TextView tvLoginByPassword;
    private TextView tvRegister;
//    private EventHand

    //定义一个用于判断退出时的时间
    private long mExitTime;

    //定义Handler对象属性
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://获得密码登录的请求结果
                    String response = (String) msg.obj;
                    if (response.equals("success")){
                        //登录成功 跳转到首页
                        Intent intent = new Intent(LoginByPhoneActivity.this, MainActivity.class);
//                        intent.putExtra("phone",etPhone.getText().toString().trim());
                        startActivity(intent);
                        finish();
                    }else {//登录失败 显示错误信息
                        Toast.makeText(getApplicationContext(),
                                ""+response,Toast.LENGTH_SHORT).show();

                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_phone);
        ChangeStatusBarColor.initSystemBar(this);
        findViews();
    }

    private void findViews() {
//        etPhone = findViewById(R.id.et_phone);
//        etPassword = findViewById(R.id.et_pa)

    }
}