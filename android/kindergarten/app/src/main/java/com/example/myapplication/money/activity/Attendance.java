package com.example.myapplication.money.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.main.entity.ChildConsumeInfo;
import com.example.myapplication.main.entity.LeaveInfo;
import com.example.myapplication.main.fragment.MyFragment;
import com.example.myapplication.main.util.ConfigUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Attendance extends AppCompatActivity {

    private TextView tvMonthDay;
    private TextView tvLeaveDay;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String str= (String) msg.obj;
                    LeaveInfo leaveInfo =new Gson().fromJson(str,LeaveInfo.class);
                    tvMonthDay.setText(leaveInfo.getMonthDay()+"");
                    tvLeaveDay.setText(leaveInfo.getLeaveDay()+"");

                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_attendance);

        findViews();
        //从服务器端获取数据
        getLeaveAndShouldDay(MyFragment.childName, ConfigUtil.PHONE);
    }

    /**
     * 从服务器端获取孩子的上个月应到天数和请假天数
     * @param childName
     * @param phone
     */
    private void getLeaveAndShouldDay(String childName, String phone) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", phone);
        builder.add("childName", childName);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "GetLeaveDayAndShouldDay")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询请假信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.i("查询请假信息", "onResponse: "+result);
                Message message = new Message();
                message.obj = result;
                message.what = 1;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    private void findViews() {
        tvMonthDay=findViewById(R.id.tv_month_day);
        tvLeaveDay=findViewById(R.id.tv_leave_day);
    }

}
