package com.example.myapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main.util.ChangeStatusBarColor;
import com.example.myapplication.main.util.ConfigUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //注册的身份
    private RadioButton rbParent;
    private RadioButton rbTeacher;

    private EditText etPhone;
    private EditText etNickName;
    private EditText etPassword;
    private Button btnRegister;
    private TextView tvLoginByMsg;
    private TextView tvLoginByPassword;
    //定义一个用于判断退出时的时间
    private long mExitTime;
    //定义Handler对象属性
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://获得密码登录的请求结果
                    String response= (String) msg.obj;
                    if (response.equals("success")){//注册成功 跳转到登录界面
                        Intent intent=new Intent();
                        intent.setClass(RegisterActivity.this, LoginByPasswordActivity.class);
                        startActivity(intent);
                        finish();
                    }else{//注册失败 显示错误信息
                        Toast.makeText(getApplicationContext(),
                                ""+response,
                                Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ChangeStatusBarColor.initSystemBar(this);
        findViews();
    }

    private void findViews() {
        rbParent = findViewById(R.id.rb_parent);
        rbTeacher = findViewById(R.id.rb_teacher);
        etNickName=findViewById(R.id.et_nickname);
        etPassword=findViewById(R.id.et_password);
        etPhone=findViewById(R.id.et_phone);
        btnRegister=findViewById(R.id.btn_register);
        tvLoginByMsg=findViewById(R.id.tv_login_by_msg);
        tvLoginByPassword=findViewById(R.id.tv_login_by_password);
        tvLoginByPassword.setOnClickListener(this);
        tvLoginByMsg.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_register://点击注册按钮
                //连接服务器 进行注册
                toRegister();
                break;
            case R.id.tv_login_by_msg://点击验证码登录 跳转
                Intent intentLoginByMsg=new Intent();
                intentLoginByMsg.setClass(RegisterActivity.this, LoginByPhoneActivity.class);
                startActivity(intentLoginByMsg);
                finish();
                break;
            case R.id.tv_login_by_password://点击密码登录 跳转
                Intent intentLoginByPassword=new Intent();
                intentLoginByPassword.setClass(RegisterActivity.this, LoginByPasswordActivity.class);
                startActivity(intentLoginByPassword);
                finish();
                break;
        }
    }

    /**
     * 进行注册
     */
    private void toRegister() {
        //判断内容是否为空
        if (etPhone.getText() != null && !etPhone.getText().toString().equals("")) {
            //检测要注册的手机号是否合法
            if (checkTel(etPhone.getText().toString().trim())){
                if (etNickName.getText() != null && !etNickName.getText().toString().equals("")){
                    if (etPassword.getText() != null && !etPassword.getText().toString().equals("")) {
                        //都不为空 检查合法性
                        //检查密码长度
                        if (6<=etPassword.getText().toString().trim().length() && etPassword.getText().toString().trim().length()<=20){
                            registerMsg();
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "密码长度应为6~20位",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "密码不能为空！",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),
                            "昵称不能为空！",
                            Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),
                        "请输入正确的手机号码！",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "手机号不能为空！",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 连接服务器 进行注册
     */
    private void registerMsg() {
        //提交键值对格式的数据
        FormBody.Builder builder=new FormBody.Builder();
        if (rbTeacher.isChecked()){
            builder.add("identity","1");
        }else {
            builder.add("identity","0");
        }
        builder.add("phone",etPhone.getText().toString().trim());
        builder.add("password",etPassword.getText().toString().trim());
        builder.add("nickname",etNickName.getText().toString().trim());
        FormBody body=builder.build();
        Request request=new Request.Builder()
                .post(body)
                .url(ConfigUtil.SERVICE_ADDRESS+"RegisterServlet")
                .build();
        //获得call对象
        Call call=new OkHttpClient().newCall(request);
        //提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("密码登录：", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                //在环信注册该用户
//                new Thread(
//                        new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    EMClient.getInstance().createAccount(etPhone.getText().toString().trim(),
//                                            etPassword.getText().toString().trim());
//                                    Log.e("shenyayu", "run: 注册成功");
//                                    Looper.prepare();
//                                    Toast.makeText(getBaseContext(),"注册成功！",Toast.LENGTH_SHORT).show();
//                                    Looper.loop();
//                                } catch (HyphenateException e) {
//                                    e.printStackTrace();
//                                    Log.e("shenyayu", "run: 注册失败");
//                                    Looper.prepare();
//                                    Toast.makeText(getBaseContext(),"注册失败！",Toast.LENGTH_SHORT).show();
//                                    Looper.loop();
//                                }
//                            }
//                        }
//                ).start();
                //处理请求结果
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }

    /**
     * 正则匹配手机号码，检测手机号码是否合法
     * @param tel
     * @return
     */
    public boolean checkTel(String tel){
        String telRegex = "[1][345789]\\d{9}";
        //"[1]"第1位为数字1，"[34578]"第二位可以为3、4、5、7、8、9中的一个，"\\d{9}"第3位开始后面是可以是0～9的数字，有9位。共计11位。
        if (TextUtils.isEmpty(tel)) {
            return false;
        }
        else {
            Log.e("检验结果：",""+tel.matches(telRegex));
            return tel.matches(telRegex);
        }
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {

            Toast.makeText(RegisterActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //MyConfig.clearSharePre(this, "users");
            finish();
            System.exit(0);
        }
    }
}