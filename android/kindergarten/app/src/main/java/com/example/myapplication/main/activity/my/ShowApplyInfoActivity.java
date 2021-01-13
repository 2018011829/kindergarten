package com.example.myapplication.main.activity.my;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.example.myapplication.main.fragment.MyFragment;
import com.example.myapplication.main.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

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

public class ShowApplyInfoActivity extends AppCompatActivity {

    private TextView babyName;//宝宝姓名
    private TextView babyBirthday;//宝宝生日
    private TextView babySex;//宝宝性别
    private TextView babyIDnumber;//宝宝身份证号
    private TextView babyAddoAllergies;//宝宝过敏食物
    private TextView parentName1;//家长姓名1
    private TextView relation1;//与宝宝关系1
    private TextView parentIDnumber1;//家长身份证号1
    private TextView phoneNumber1;//联系方式1
    private TextView workSpace1;//工作单位1
    private TextView homeAddress1;//家庭住址1
    private TextView parentName2;//家长姓名2
    private TextView relation2;//与宝宝关系2
    private TextView parentIDnumber2;//家长身份证号1
    private TextView phoneNumber2;//联系方式2
    private TextView workSpace2;//工作单位2
    private TextView homeAddress2;//家庭住址2

    private ApplyInfo applyInfo;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1://加载报名信息列表
                    String str= (String) msg.obj;
                    if (!str.equals("") && str.equals("未找到相关报名信息")){
                        Toast.makeText(ShowApplyInfoActivity.this,
                                "您还没有进行报名操作！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        applyInfo=new Gson().fromJson(str,ApplyInfo.class);
                        //初始化数据信息
                        initData();
                    }

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_apply_info);

        findViews();
        Intent intent=getIntent();
        int id=intent.getIntExtra("applyId",0);
        showApplyInfoById(id);
    }

    private void findViews() {
        babyName = findViewById(R.id.baby_name);//宝宝姓名
        babyBirthday = findViewById(R.id.baby_birthday);//宝宝生日
        babySex = findViewById(R.id.baby_sex);//宝宝性别
        babyIDnumber = findViewById(R.id.baby_IDnumber);//宝宝身份证号
        babyAddoAllergies = findViewById(R.id.baby_cannot_eat);//宝宝过敏食物
        parentName1 = findViewById(R.id.parent_name1);
        parentName2 = findViewById(R.id.parent_name2);
        relation1 = findViewById(R.id.relation1);
        relation2 = findViewById(R.id.relation2);
        parentIDnumber1 = findViewById(R.id.parentIDnumber1);
        parentIDnumber2 = findViewById(R.id.parentIDnumber2);
        phoneNumber1 = findViewById(R.id.phone_number1);
        phoneNumber2 = findViewById(R.id.phone_number2);
        workSpace1 = findViewById(R.id.work_space1);
        workSpace2 = findViewById(R.id.work_space2);
        homeAddress1 = findViewById(R.id.home_address1);
        homeAddress2 = findViewById(R.id.home_address2);
    }

    /**
     * 初始化数据信息
     */
    private void initData() {
        babyName.setText(applyInfo.getBabyName());
        babyBirthday.setText(applyInfo.getBabyBirthday());
        babySex.setText(applyInfo.getBabySex());
        babyIDnumber.setText(applyInfo.getBabyIDnumber());
        babyAddoAllergies.setText(applyInfo.getBabyAddoAllergies());
        parentName1.setText(applyInfo.getParentName1());
        parentName2.setText(applyInfo.getParentName2());
        relation1.setText(applyInfo.getRelation1());
        relation2.setText(applyInfo.getRelation2());
        parentIDnumber1.setText(applyInfo.getParentIDnumber1());
        parentIDnumber2.setText(applyInfo.getParentIDnumber2());
        phoneNumber1.setText(applyInfo.getPhoneNumber1());
        phoneNumber2.setText(applyInfo.getPhoneNumber2());
        workSpace1.setText(applyInfo.getWorkSpace1());
        workSpace2.setText(applyInfo.getWorkSpace2());
        homeAddress1.setText(applyInfo.getHomeAddress1());
        homeAddress2.setText(applyInfo.getHomeAddress2());
    }

    private void showApplyInfoById(int id) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("applyId", id+"");
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "GetApplyInfoServlet")
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