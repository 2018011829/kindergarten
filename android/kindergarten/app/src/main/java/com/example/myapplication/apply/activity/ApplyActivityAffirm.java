package com.example.myapplication.apply.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.example.myapplication.main.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApplyActivityAffirm extends AppCompatActivity {
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
    private TextView homeAddress1;//家庭住+54址1
    private TextView parentName2;//家长姓名2
    private TextView relation2;//与宝宝关系2
    private TextView parentIDnumber2;//家长身份证号2
    private TextView phoneNumber2;//联系方式2
    private TextView workSpace2;//工作单位2
    private TextView homeAddress2;//家庭住址2

    private RelativeLayout rl3;//家长信息2
    private ImageView returnParent;//返回家长页面
    private Gson gson;//定义Gson对象属性
    private ApplyInfo applyInfo;//定义报名信息对象
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    private String appInfo;//上一个activity传来的gson串
    private Button btnSubmit;//提交按钮
    private String whether;
    private void initHandler() {
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1:
                        String str = (String) msg.obj;//接收到的是一个说说对象
                        Toast.makeText(ApplyActivityAffirm.this,str,Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_affirm);
        initView();
        setListener();
        Intent intent = getIntent();
        appInfo = intent.getStringExtra("applyInfo");
        whether = intent.getStringExtra("whether");
        applyInfo = gson.fromJson(appInfo, ApplyInfo.class);
        Log.e("123",appInfo);
        Log.e("5555",whether);
        setContent();
    }
    private void initView() {
        babyName = findViewById(R.id.baby_name);//宝宝姓名
        babyBirthday = findViewById(R.id.baby_birthday);//宝宝生日
        babySex = findViewById(R.id.baby_sex);//宝宝性别
        babyIDnumber = findViewById(R.id.baby_IDnumber);//宝宝身份证号
        babyAddoAllergies = findViewById(R.id.baby_addo_allergies);//宝宝过敏食物

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
        returnParent = findViewById(R.id.return_parent);
        rl3 = findViewById(R.id.rl3);

        btnSubmit = findViewById(R.id.btn_submit);
        okHttpClient = new OkHttpClient();
        gson = new GsonBuilder()//创建GsonBuilder对象
                .setPrettyPrinting()//格式化输出
                .serializeNulls()//允许输出Null值属性
                .setDateFormat("yy:MM:dd")//日期格式化
                .create();//创建Gson对象
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1:
                        String str = (String)msg.obj;
                        if (!str.equals("")&&str!=null){
                            Toast.makeText(ApplyActivityAffirm.this,str,Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(ApplyActivityAffirm.this,"提交失败",Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        };
    }
    private void setListener() {
        Mylistener mylistener = new Mylistener();
        returnParent.setOnClickListener(mylistener);
        btnSubmit.setOnClickListener(mylistener);

    }
    class Mylistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_submit:
                    new Thread(){
                        @Override
                        public void run() {
                            downLoadImgNameFromServerRequest();
                        }
                    }.start();
                    break;
                case R.id.return_parent:
                    finish();
                    break;
            }
        }
    }

    //显示所填信息
    private void setContent(){
        babyName.setText(applyInfo.getBabyName());
        babyBirthday.setText(applyInfo.getBabyBirthday());
        babySex.setText(applyInfo.getBabySex());
        babyIDnumber.setText(applyInfo.getBabyIDnumber());
        babyAddoAllergies.setText(applyInfo.getBabyAddoAllergies());

        parentName1.setText(applyInfo.getParentName1());
        relation1.setText(applyInfo.getRelation1());
        parentIDnumber1.setText(applyInfo.getParentIDnumber1());
        phoneNumber1.setText(applyInfo.getPhoneNumber1());
        workSpace1.setText(applyInfo.getWorkSpace1());
        homeAddress1.setText(applyInfo.getHomeAddress1());

        if (whether.equals("1")){
            rl3.setVisibility(View.VISIBLE);
            parentName2.setText(applyInfo.getParentName2());
            relation2.setText(applyInfo.getRelation2());
            parentIDnumber2.setText(applyInfo.getParentIDnumber2());
            phoneNumber2.setText(applyInfo.getPhoneNumber2());
            workSpace2.setText(applyInfo.getWorkSpace2());
            homeAddress2.setText(applyInfo.getHomeAddress2());
        }
    }
    //从服务端获取图片资源的网络路径
    private void downLoadImgNameFromServerRequest() {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //创建FormBody对象
        FormBody formBody =
                new FormBody.Builder()
                        .add("applyInfo",appInfo)
                        .build();
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "AddChildEnterInformation")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            String img = response.body().string();
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = img;
            handler.sendMessage(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
