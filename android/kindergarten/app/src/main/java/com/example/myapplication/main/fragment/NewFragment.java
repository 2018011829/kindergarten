package com.example.myapplication.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.main.entity.ChildConsumeInfo;
import com.example.myapplication.main.util.ConfigUtil;
import com.example.myapplication.money.activity.Attendance;
import com.example.myapplication.money.activity.Money;
import com.example.myapplication.money.activity.UploadPicture;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewFragment extends Fragment {
    private View view;
    private Button btnAttendance;
    private Button btnMoney;
    private Button btnUploadPicture;
    private TextView tvName;
    private TextView tvDay;
    private TextView tvMoney;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String str= (String) msg.obj;
                    ChildConsumeInfo childInfo=new Gson().fromJson(str,ChildConsumeInfo.class);
                    tvDay.setText(childInfo.getDay()+"");
                    tvMoney.setText(childInfo.getMoney()+"");

                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new,container,false);
        if (!ConfigUtil.PHONE.equals("") && ConfigUtil.PHONE!=null){ //判断是否登录
            if (!MyFragment.childName.equals("") && MyFragment.childName!=null){ //判断是否选择了孩子
                // 初始化控件
                initView();
                tvName.setText(MyFragment.childName);
                // 设置点击事件的监听器
                setListeners();
                //获取孩子的出勤天数，和需要缴费的金额
                getMoneyMsg();
            }else{
                Toast.makeText(getContext(),
                        "您还未选择孩子！",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
           Toast.makeText(getContext(),
                   "您还未登录！",
                   Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void getMoneyMsg() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", ConfigUtil.PHONE);
        builder.add("childName", MyFragment.childName);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "GetChildMoneyServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询消费信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.i("查询消费信息", "onResponse: "+result);
                Message message = new Message();
                message.obj = result;
                message.what = 1;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    // 初始化控件
    private void initView() {
        tvName=view.findViewById(R.id.money_child_name);
        tvDay=view.findViewById(R.id.money_child_day);
        tvMoney=view.findViewById(R.id.money_child);
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
