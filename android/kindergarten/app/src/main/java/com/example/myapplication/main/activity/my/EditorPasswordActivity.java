package com.example.myapplication.main.activity.my;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.main.util.ConfigUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditorPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_password1;
    private EditText et_password2;
    private EditText et_password3;
    private Button btn_save;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String rs = msg.obj.toString();
                    if(rs.equals("success")){
                        Toast.makeText(EditorPasswordActivity.this, "密码修改成功！", Toast.LENGTH_LONG).show();
                    }else if(rs.equals("failure1")){
                        Toast.makeText(EditorPasswordActivity.this, "原密码不正确！", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(EditorPasswordActivity.this, "新密码两次输入不一致或密码位数错误！", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting_password);

        findView();
        setListener();
    }

    private void setListener() {
        btn_save.setOnClickListener(this);
    }

    private void findView() {
        et_password1 = findViewById(R.id.et_password1);
        et_password2 = findViewById(R.id.et_password2);
        et_password3 = findViewById(R.id.et_password3);
        btn_save = findViewById(R.id.btn_save);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("phone", ConfigUtil.PHONE);
                builder.add("password1", et_password1.getText().toString());
                builder.add("password2", et_password2.getText().toString());
                builder.add("password3", et_password3.getText().toString());
                FormBody formBody = builder.build();
                Request request = new Request.Builder()
                        .post(formBody)
                        .url(ConfigUtil.SERVICE_ADDRESS + "EditorPasswordServlet")
                        .build();
                Call call = new OkHttpClient().newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("上传密码信息", "请求失败");
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String result = response.body().string();
                        Message message = new Message();
                        message.obj = result;
                        message.what = 1;
                        handler.sendMessage(message);
                        Log.i("result", result);
                    }
                });
                break;
        }
    }
}