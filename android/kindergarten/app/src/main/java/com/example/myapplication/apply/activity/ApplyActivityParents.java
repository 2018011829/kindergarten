package com.example.myapplication.apply.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApplyActivityParents extends AppCompatActivity {
    private EditText parentName1;//家长姓名1
    private EditText relation1;//与宝宝关系1
    private EditText parentIDnumber1;//家长身份证号1
    private EditText phoneNumber1;//联系方式1
    private EditText workSpace1;//工作单位1
    private EditText homeAddress1;//家庭住址1
    private EditText parentName2;//家长姓名2
    private EditText relation2;//与宝宝关系2
    private EditText parentIDnumber2;//家长身份证号1
    private EditText phoneNumber2;//联系方式2
    private EditText workSpace2;//工作单位2
    private EditText homeAddress2;//家庭住址2
    private ImageView returnBaby;//返回宝宝界面
    private RelativeLayout rlApply;
    private Button next2;//下一步
    private Gson gson;//定义Gson对象属性
    private ApplyInfo applyInfo = new ApplyInfo();//定义报名信息对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_parent);
        initView();
        setListener();
        Intent intent = getIntent();
        String appInfo = intent.getStringExtra("applyInfo");
        applyInfo = gson.fromJson(appInfo, ApplyInfo.class);
    }

    private void initView() {
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
        returnBaby = findViewById(R.id.return_baby);
        rlApply = findViewById(R.id.rl_apply);

        parentName1.addTextChangedListener(textWatcher);
        parentName2.addTextChangedListener(textWatcher);
        relation1.addTextChangedListener(textWatcher);
        relation2.addTextChangedListener(textWatcher);
        parentIDnumber1.addTextChangedListener(textWatcher);
        parentIDnumber2.addTextChangedListener(textWatcher);
        phoneNumber1.addTextChangedListener(textWatcher);
        phoneNumber2.addTextChangedListener(textWatcher);
        workSpace1.addTextChangedListener(textWatcher);
        workSpace2.addTextChangedListener(textWatcher);
        homeAddress1.addTextChangedListener(textWatcher);
        homeAddress2.addTextChangedListener(textWatcher);

        next2 = findViewById(R.id.next2);
        gson = new GsonBuilder()//创建GsonBuilder对象
                .setPrettyPrinting()//格式化输出
                .serializeNulls()//允许输出Null值属性
                .setDateFormat("YY:MM:dd")//日期格式化
                .create();//创建Gson对象
    }
    private void setListener() {
        Mylistener mylistener = new Mylistener();
        returnBaby.setOnClickListener(mylistener);
        rlApply.setOnClickListener(mylistener);

    }
    class Mylistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.return_baby:
                    finish();
                    break;
                case R.id.rl_apply:
                    hintKbTwo();
                    break;
            }
        }
    }
    //此方法只是关闭软键盘
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager)getSystemService(ApplyActivityParents.this.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&getCurrentFocus()!=null){
            if (getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
    TextWatcher textWatcher = new TextWatcher() {
        // 输入文本之前的状态
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        // 输入文本中的状态
        @SuppressLint("ResourceAsColor")
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(!parentName1.getText().toString().equals("")&&!relation1.getText().toString().equals("")
            &&!parentIDnumber1.getText().toString().equals("")&&!phoneNumber1.getText().toString().equals("")
            &&!workSpace1.getText().toString().equals("")&&!homeAddress1.getText().toString().equals("")){
                Resources resources = ApplyActivityParents.this.getResources();
                Drawable drawable = resources.getDrawable(R.drawable.apply_button2);
                next2.setBackground(drawable);
                next2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(parentName2.getText().toString().equals("")&&relation2.getText().toString().equals("")
                                &&parentIDnumber2.getText().toString().equals("")&&phoneNumber2.getText().toString().equals("")
                                &&workSpace2.getText().toString().equals("")&&homeAddress2.getText().toString().equals("")){
                            applyInfo.setParentName1(parentName1.getText().toString());
                            applyInfo.setRelation1(relation1.getText().toString());
                            applyInfo.setParentIDnumber1(parentIDnumber1.getText().toString());
                            applyInfo.setPhoneNumber1(phoneNumber1.getText().toString());
                            applyInfo.setWorkSpace1(workSpace1.getText().toString());
                            applyInfo.setHomeAddress1(homeAddress1.getText().toString());

                            String appInfo = gson.toJson(applyInfo);
                            Intent intent = new Intent(ApplyActivityParents.this, ApplyActivityAffirm.class);
                            intent.putExtra("applyInfo",appInfo);
                            intent.putExtra("whether","0");
                            startActivity(intent);
                        }else if(!parentName2.getText().toString().equals("")&&!relation2.getText().toString().equals("")
                                &&!parentIDnumber2.getText().toString().equals("")&&!phoneNumber2.getText().toString().equals("")
                                &&!workSpace2.getText().toString().equals("")&&!homeAddress2.getText().toString().equals("")){
                            applyInfo.setParentName1(parentName1.getText().toString());
                            applyInfo.setRelation1(relation1.getText().toString());
                            applyInfo.setParentIDnumber1(parentIDnumber1.getText().toString());
                            applyInfo.setPhoneNumber1(phoneNumber1.getText().toString());
                            applyInfo.setWorkSpace1(workSpace1.getText().toString());
                            applyInfo.setHomeAddress1(homeAddress1.getText().toString());

                            applyInfo.setParentName2(parentName2.getText().toString());
                            applyInfo.setRelation2(relation2.getText().toString());
                            applyInfo.setParentIDnumber2(parentIDnumber2.getText().toString());
                            applyInfo.setPhoneNumber2(phoneNumber2.getText().toString());
                            applyInfo.setWorkSpace2(workSpace2.getText().toString());
                            applyInfo.setHomeAddress2(homeAddress2.getText().toString());

                            String appInfo = gson.toJson(applyInfo);
                            Intent intent = new Intent(ApplyActivityParents.this, ApplyActivityAffirm.class);
                            intent.putExtra("applyInfo",appInfo);
                            intent.putExtra("whether","1");
                            startActivity(intent);
                        }else {
                            Toast.makeText(ApplyActivityParents.this,"信息未完成",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else {
                Resources resources = ApplyActivityParents.this.getResources();
                Drawable drawable = resources.getDrawable(R.drawable.apply_button1);
                next2.setBackground(drawable);
                next2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ApplyActivityParents.this,"信息未完成",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        // 输入文本之后的状态
        @Override
        public void afterTextChanged(Editable s) {}
    };
}
