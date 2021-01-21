package com.example.myapplication.main.activity.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.example.myapplication.main.adapter.MyAdapter;
import com.example.myapplication.main.entity.Child;
import com.example.myapplication.main.fragment.MyFragment;
import com.example.myapplication.main.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApplyInfoActivity extends AppCompatActivity {

    private ListView listView;
    private List<ApplyInfo> data = null;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://加载报名信息列表
                    String str= (String) msg.obj;
                    if (!str.equals("") && str.equals("未找到相关报名信息")){
                        Toast.makeText(ApplyInfoActivity.this,
                                "您还没有进行报名操作！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        data = new ArrayList<>();
                        //解析Gson串
                        //集合反序列化
                        //获取集合类型
                        Type typeList=new TypeToken<List<ApplyInfo>>(){}.getType();
                        data=new Gson().fromJson(str,typeList);

                        //向listview中加载数据
                        initData();
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_info);

        Intent intent=getIntent();
        listView=findViewById(R.id.list_about_applyinfo);
        queryApplyInfoByPhoneNum();
    }

    /**
     * 加载报名信息列表
     */
    private void initData() {
        MyAdapter myAdapter=new MyAdapter(ApplyInfoActivity.this,R.layout.list_item_about_applyinfo,data);
        listView.setAdapter(myAdapter);
    }

    private void queryApplyInfoByPhoneNum() {
        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("phone", MyFragment.phoneNum);
        builder.add("phone", ConfigUtil.PHONE);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "QueryApplyInfoServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询孩子信息", "请求失败");
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
    }
}