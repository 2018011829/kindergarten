package com.example.myapplication.login.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myapplication.main.util.ConfigUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginUtil {

    public void getUserIdByPhone(String phone){
        String id = null;
        //提交键值对格式的数据
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", phone);
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(ConfigUtil.SERVICE_ADDRESS + "LoginByPhoneAndPwdServlet")
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
            }
        });

    }
}
