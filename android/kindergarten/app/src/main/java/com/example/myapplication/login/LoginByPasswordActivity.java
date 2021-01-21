package com.example.myapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.contact.LoginActivity;
import com.example.myapplication.contact.TestActivity;
import com.example.myapplication.main.activity.MainActivity;
import com.example.myapplication.main.util.ChangeStatusBarColor;
import com.example.myapplication.main.util.ConfigUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.im.AVIMOptions;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginByPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = "LoginByPasswordActivity";


    private EditText etPhone;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvLoginByMsg;
    private TextView tvRegister;

    //定义一个用于判断退出时的时间
    private long mExitTime;
    //定义Handler对象属性
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1://获得密码登录的请求结果
                    String response = (String) msg.obj;
                    if (response.equals("success")) {
                        //在LeanCloud登录
                        AVIMOptions.getGlobalOptions().setAutoOpen(true);
                        LCChatKit.getInstance().open(etPhone.getText().toString().trim(), new AVIMClientCallback() {
                            @Override
                            public void done(AVIMClient avimClient, AVIMException e) {
                                if (null == e) {
                                    Intent intent = new Intent(LoginByPasswordActivity.this, TestActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginByPasswordActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        //登录成功 跳转到首页
                        Intent intent = new Intent();
                        intent.setClass(LoginByPasswordActivity.this, MainActivity.class);
                        intent.putExtra("phone", etPhone.getText().toString().trim());
                        startActivity(intent);
                        finish();
                    } else {//登录失败 显示错误信息
                        Toast.makeText(getApplicationContext(),
                                "" + response,
                                Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_password);
        ChangeStatusBarColor.initSystemBar(this);
        findViews();
    }

    private void findViews() {
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvLoginByMsg = findViewById(R.id.tv_login_by_msg);
        tvRegister = findViewById(R.id.tv_register);
        btnLogin.setOnClickListener(this);
        tvLoginByMsg.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login://点击登录按钮
                //检查手机号和密码是否存在
                checkPhoneNumAndPassword();
                break;
            case R.id.tv_register://点击注册
                Intent intentRegister = new Intent();
                intentRegister.setClass(LoginByPasswordActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                finish();
                break;
            case R.id.tv_login_by_msg://点击短信验证码登录
                Intent intentLoginByMsg = new Intent();
                intentLoginByMsg.setClass(LoginByPasswordActivity.this, LoginByPhoneActivity.class);
                startActivity(intentLoginByMsg);
                finish();
                break;
        }
    }

    /**
     * 检查手机号和密码是否存在
     */
    private void checkPhoneNumAndPassword() {
        //判断手机号、密码是否为空
        if (etPhone.getText() != null && !etPhone.getText().toString().equals("")) {
            if (etPassword.getText() != null && !etPassword.getText().toString().equals("")) {
                //都不为空 检查合法性
                checkPhoneNumAndPwd();

//                EMLogin();
            } else {
                Toast.makeText(getApplicationContext(),
                        "密码不能为空！",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "手机号不能为空！",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 连接环信服务器进行登录
     */
//    private void EMLogin() {
//        EMClient.getInstance().login(etPhone.getText().toString().trim(),
//                etPassword.getText().toString().trim(),
//                new EMCallBack() {
//                    @Override
//                    public void onSuccess() {
//                        //存储当前用户的昵称和头像
//                        ParentUtil.storeCurrentParent(EMClient.getInstance().getCurrentUser(), null);
//                        ContactManager.newFriends.put(EMClient.getInstance().getCurrentUser(), new ArrayList<>());
//                        startActivity(new Intent(LoginByPasswordActivity.this, MainActivity.class));
//                        finish();
//                        Looper.prepare();
//                        Toast.makeText(getBaseContext(), "登录成功！", Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                    }
//
//                    @Override
//                    public void onError(int i, String s) {
//                        Looper.prepare();
//                        Toast.makeText(getBaseContext(), "登录失败！", Toast.LENGTH_SHORT).show();
//                        Looper.loop();
//                        Log.e(TAG, "登录失败");
//                    }
//
//                    @Override
//                    public void onProgress(int i, String s) {
//
//                    }
//
//                });
//    }

    /**
     * OkHttp
     * 连接服务器 查找用户是否存在
     */
    private void checkPhoneNumAndPwd() {
        //提交键值对格式的数据
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", etPhone.getText().toString().trim());
        builder.add("password", etPassword.getText().toString().trim());
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(ConfigUtil.SERVICE_ADDRESS + "ParentLoginByPhoneAndPwdServlet")
                .build();
        //获得call对象
        Call call = new OkHttpClient().newCall(request);
        //提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("密码登录", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                //处理请求结果
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
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
            Toast.makeText(LoginByPasswordActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //MyConfig.clearSharePre(this, "users");
            finish();
            System.exit(0);
        }
    }
}