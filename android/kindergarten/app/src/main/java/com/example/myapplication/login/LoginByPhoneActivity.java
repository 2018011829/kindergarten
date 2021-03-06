package com.example.myapplication.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.login.entity.User;
import com.example.myapplication.main.activity.MainActivity;
import com.example.myapplication.main.util.ChangeStatusBarColor;
import com.example.myapplication.main.util.ConfigUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.utils.CKConfigUtil;
import cn.leancloud.im.AVIMOptions;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginByPhoneActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "LoginByPhoneActivity";

    private EditText etPhoneNum;
    private EditText etCheckNum;
    private Button btnGetCheckNum;
    private Button btnLogin;
    private TextView tvLoginByPassword;
    private TextView tvRegister;
    private String userId;
    public EventHandler eh; //事件接收器
    private TimeCount mTimeCount;//计时器
    //定义一个用于判断退出时的时间
    private long mExitTime;
    //定义Handler对象属性
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://获得密码登录的请求结果
                    String response= (String) msg.obj;
                    if (response.equals("success")){//手机号已经注册
                        SMSSDK.getVerificationCode("+86",etPhoneNum.getText().toString());//获取验证码
                        mTimeCount.start();
                    }else{
                        Toast.makeText(LoginByPhoneActivity.this, "该手机号还未注册！", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    // 在LeanCloud登录
                    AVIMOptions.getGlobalOptions().setAutoOpen(true);
                    //使用用户id登录
                    LCChatKit.getInstance().open(userId, new AVIMClientCallback() {
                        @Override
                        public void done(AVIMClient avimClient, AVIMException e) {
                            if (null == e) {
//                                    Intent intent = new Intent(LoginByPasswordActivity.this, TestActivity.class);
//                                    startActivity(intent);
                            } else {
                                Toast.makeText(LoginByPhoneActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    //登录成功 跳转到首页
                    Intent intent = new Intent();
                    intent.setClass(LoginByPhoneActivity.this, MainActivity.class);
                    intent.putExtra("phone", etPhoneNum.getText().toString().trim());
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    public void getUserIdByPhone(String phone){
        //提交键值对格式的数据
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", phone);
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(ConfigUtil.SERVICE_ADDRESS + "GetUserMsgServlet")
                .build();
        //获得call对象
        Call call = new OkHttpClient().newCall(request);
        //提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("lxl", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                User user = new Gson().fromJson(result,User.class);
                userId = user.getId()+"";
                CKConfigUtil.user = new LCChatKitUser(userId,user.getNickname(),ConfigUtil.SETVER_AVATAR+user.getAvatar());
                //处理请求结果
                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by_phone);
        ChangeStatusBarColor.initSystemBar(this);

        findViews();
        init();
    }

    /**
     * 初始化事件接收器
     */
    private void init(){
        eh = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) { //回调完成

                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//验证通过
                        Log.i("lxl", "afterEvent: 验证成功");
                        // 存入用户手机电话
                        ConfigUtil.PHONE= etPhoneNum.getText().toString().trim();
                        CKConfigUtil.PHONE= etPhoneNum.getText().toString().trim();
                        getUserIdByPhone(etPhoneNum.getText().toString().trim());
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){ //获取验证码成功

                    } else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){ //返回支持发送验证码的国家列表

                    }
                } else{
                    ((Throwable)data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_get_check_num://点击获取验证码按钮
                if(!etPhoneNum.getText().toString().trim().equals("")){
                    if (checkTel(etPhoneNum.getText().toString().trim())) {
                        //判断该手机号是否已经注册
                        checkRegisted(etPhoneNum.getText().toString().trim());
                    }else{
                        Toast.makeText(LoginByPhoneActivity.this, "请输入正确的手机号码！", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginByPhoneActivity.this, "请输入手机号码！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_login:
                if (!etPhoneNum.getText().toString().trim().equals("")) {
                    if (checkTel(etPhoneNum.getText().toString().trim())) {
                        if (!etCheckNum.getText().toString().trim().equals("")) {
                            SMSSDK.submitVerificationCode("+86",etPhoneNum.getText().toString().trim(),etCheckNum.getText().toString().trim());//提交验证
                        }else{
                            Toast.makeText(LoginByPhoneActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginByPhoneActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginByPhoneActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_login_by_password://点击用密码登录
                Intent intentLoginByPassword=new Intent();
                intentLoginByPassword.setClass(LoginByPhoneActivity.this,LoginByPasswordActivity.class);
                startActivity(intentLoginByPassword);
                finish();
                break;
            case R.id.tv_register:
                Intent intentRegister=new Intent();
                intentRegister.setClass(LoginByPhoneActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
                finish();
                break;
        }
    }

    /**
     * 连接服务器 检测该手机号是否已经注册
     * @param trim
     */
    private void checkRegisted(String trim) {
        //创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS
                        + "LoginByPhoneNumServlet"+"?phone="+trim)
                .build();
        //创建CALL对象
        Call call = new OkHttpClient().newCall(request);
        //异步方式提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("lxl", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //获取服务端返回的数据（假设是字符串）
                String result = response.body().string();
                Log.i("lxl", result);

                //通知界面信息改变
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
            Log.e("检验结果",""+tel.matches(telRegex));
            return tel.matches(telRegex);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);
    }

    private void findViews() {
        etPhoneNum=findViewById(R.id.et_phone);
        etCheckNum=findViewById(R.id.et_check_num);
        btnGetCheckNum=findViewById(R.id.btn_get_check_num);
        btnLogin=findViewById(R.id.btn_login);
        tvLoginByPassword=findViewById(R.id.tv_login_by_password);
        tvRegister=findViewById(R.id.tv_register);
        btnGetCheckNum.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvLoginByPassword.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        mTimeCount = new TimeCount(60000, 1000);
    }

    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {//获取验证码按钮被点击
            btnGetCheckNum.setClickable(false);
            btnGetCheckNum.setTextColor(getResources().getColor(android.R.color.black,null));
            btnGetCheckNum.setBackground(getResources().getDrawable(R.drawable.half_circle_button_clicked,null));
            btnGetCheckNum.setText(l/1000 + "秒后重新获取");
        }

        @Override
        public void onFinish() {//验证码失效
            btnGetCheckNum.setClickable(true);
            btnGetCheckNum.setText("获取验证码");
            btnGetCheckNum.setTextColor(getResources().getColor(android.R.color.white,null));
            btnGetCheckNum.setBackground(getResources().getDrawable(R.drawable.half_circle_button_failure,null));
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

            Toast.makeText(LoginByPhoneActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            //MyConfig.clearSharePre(this, "users");
            finish();
            System.exit(0);
        }
    }
}
