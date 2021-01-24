package com.example.myapplication.apply.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.example.myapplication.main.util.ChangeStatusBarColor;
import com.example.myapplication.main.util.IdCardVerification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;

public class ApplyActivityBaby extends AppCompatActivity {
    private EditText babyName;//宝宝姓名
    private EditText babyBirthday;//宝宝生日
    private EditText babySex;//宝宝性别
    private EditText babyIDnumber;//宝宝身份证号
    private EditText babyAddoAllergies;//宝宝过敏食物
    private DatePicker datePicker;//日期选择器
    private Button btnCancel;//取消按钮
    private Button btnConfirm;//确定按钮
    private RelativeLayout rlApply;//最外层布局
    private ImageView returnMain;//返回主页
    private boolean btnBck = true;//按钮是否更换背景颜色
    private Button next1;//下一步
    private Gson gson;//定义Gson对象属性

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_baby);
        ChangeStatusBarColor.initSystemBar(this);
        initView();
        setListener();

        babyBirthday.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    datePicker.setVisibility(View.VISIBLE);
                    hintKbTwo();
                } else {
                    datePicker.setVisibility(View.GONE);
                }
            }
        });
    }
    private void initView() {
        babyName = findViewById(R.id.baby_name);//宝宝姓名
        babyBirthday = findViewById(R.id.baby_birthday);//宝宝生日
        babyBirthday.setInputType(InputType.TYPE_NULL);
        babySex = findViewById(R.id.baby_sex);//宝宝性别
        babyIDnumber = findViewById(R.id.baby_IDnumber);//宝宝身份证号
        babyAddoAllergies = findViewById(R.id.baby_addo_allergies);//宝宝过敏食物
        datePicker = findViewById(R.id.dp_datapick);//日期选择器
        btnCancel = findViewById(R.id.btn_cancel);//取消按钮
        btnConfirm = findViewById(R.id.btn_confirm);//確定按鈕
        rlApply = findViewById(R.id.rl_apply);
        returnMain = findViewById(R.id.return_main);

        next1 = findViewById(R.id.next1);
        gson = new GsonBuilder()//创建GsonBuilder对象
                .setPrettyPrinting()//格式化输出
                .serializeNulls()//允许输出Null值属性
                .setDateFormat("yy:MM:dd")//日期格式化
                .create();//创建Gson对象
    }
    private void setListener() {
        Mylistener mylistener = new Mylistener();
        babyBirthday.setOnClickListener(mylistener);
        btnCancel.setOnClickListener(mylistener);
        btnConfirm.setOnClickListener(mylistener);
        rlApply.setOnClickListener(mylistener);
        returnMain.setOnClickListener(mylistener);

        babyName.addTextChangedListener(textWatcher);
        babyBirthday.addTextChangedListener(textWatcher);
        babySex.addTextChangedListener(textWatcher);
        babyIDnumber.addTextChangedListener(textWatcher);
        babyAddoAllergies.addTextChangedListener(textWatcher);
    }
    class Mylistener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.baby_birthday:
                    datePicker.setVisibility(View.VISIBLE);
                    hintKbTwo();
                    break;
                case R.id.rl_apply:
                    datePicker.setVisibility(View.GONE);
                    hintKbTwo();
                    break;
                case R.id.btn_cancel://取消时间选择器
                    datePicker.setVisibility(View.GONE);
                    break;
                case R.id.btn_confirm://确定按钮
                    int yearx = datePicker.getYear();
                    int monthx = datePicker.getMonth();
                    int dayx = datePicker.getDayOfMonth();
                    babyBirthday.setText(yearx+"年"+(monthx+1)+"月"+dayx+"日");
                    break;
                case R.id.return_main:
                    finish();
                    break;
            }
        }
    }


    //此方法只是关闭软键盘
    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager)getSystemService(ApplyActivityBaby.this.INPUT_METHOD_SERVICE);
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
            if(!babyName.getText().toString().equals("")&&!babyBirthday.getText().toString().equals("")
                    &&!babySex.getText().toString().equals("")&&!babyIDnumber.getText().toString().equals("")
                    &&!babyAddoAllergies.getText().toString().equals("")){
                String IdCardRational = null;
                //判断身份证号的合理性
                try {
                    // 将身份证最后一位的x转换为大写，便于统一
                    String IdCard = babyIDnumber.getText().toString();
                    IdCard = IdCard.toUpperCase();
                    IdCardRational = IdCardVerification.IDCardValidate(IdCard);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (!IdCardRational.equals("该身份证有效！")){
                    Resources resources = ApplyActivityBaby.this.getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.apply_button1);
                    next1.setBackground(drawable);
                    next1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ApplyActivityBaby.this,"身份证号不合理",Toast.LENGTH_LONG).show();
                        }
                    });
                }else if(!babySex.getText().toString().equals("男")&&!babySex.getText().toString().equals("女")){
                    Resources resources = ApplyActivityBaby.this.getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.apply_button1);
                    next1.setBackground(drawable);
                    next1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ApplyActivityBaby.this,"宝宝性别不合理",Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    Resources resources = ApplyActivityBaby.this.getResources();
                    Drawable drawable = resources.getDrawable(R.drawable.apply_button2);
                    next1.setBackground(drawable);
                    next1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ApplyInfo applyInfo = new ApplyInfo(getUserNumber(),babyName.getText().toString(),
                                    babyBirthday.getText().toString(),babySex.getText().toString(),
                                    babyIDnumber.getText().toString(),babyAddoAllergies.getText().toString());
                            String appInfo = gson.toJson(applyInfo);

                            Intent intent = new Intent(ApplyActivityBaby.this, ApplyActivityParents.class);
                            intent.putExtra("applyInfo",appInfo);
                            startActivity(intent);
                        }
                    });
                }
            }else {
                Resources resources = ApplyActivityBaby.this.getResources();
                Drawable drawable = resources.getDrawable(R.drawable.apply_button1);
                next1.setBackground(drawable);
                next1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ApplyActivityBaby.this,"信息未完成",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        // 输入文本之后的状态
        @Override
        public void afterTextChanged(Editable s) {}
    };
    //获取当前用户的手机号
    private String getUserNumber(){
        return "12345678";
    }

}