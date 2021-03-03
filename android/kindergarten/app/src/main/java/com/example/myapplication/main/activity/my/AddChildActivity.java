package com.example.myapplication.main.activity.my;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.myapplication.R;
import com.example.myapplication.main.util.ChangeStatusBarColor;
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

public class AddChildActivity extends AppCompatActivity {
    private List<String> name = new ArrayList<>();
    private List<String> sClasses = new ArrayList<>();
    private TextView tv_relation;
    private TextView tv_class;
    private EditText tv_id;
    private WheelView wheelView;
    private TextView tv_ok;
    private TextView tv_cancle;
    private int tag;
    private EditText edt_name;
    private String sex = "男";
    private String relation;
    private String sClass;
    private Button btn_child_ok;
    private RadioGroup radioGroup;
    private PopupWindow popupWindow;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String str= (String) msg.obj;
                    if (!str.equals("")){
                        if (str.equals("success")){
                            Toast.makeText(AddChildActivity.this,"添加成功！",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(AddChildActivity.this, str, Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Log.e("msg","收到的消息为空！");
                    }

                    break;
                case 2:
                    String classStr= (String) msg.obj;
                    Type typeList=new TypeToken<List<String>>(){}.getType();
                    sClasses=new Gson().fromJson(classStr,typeList);
                    //将数据显示在选择条中


                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        ChangeStatusBarColor.initSystemBar(this);

        //给界面添加返回按钮相关代码
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initDateSource();
        initViews();
        setViewListener();
    }

    //给界面添加返回按钮相关代码
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    * 给界面布局文件设置监听器
    * */
    private void setViewListener() {
        //利用TextWatcher实现对编辑框输入内容的实时监控
        edt_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            //通过监控charSequence实现对编辑框内容的实时监控
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(tv_relation.getText().equals("请选择与孩子的关系")
                        ||tv_id.getText().equals("请输入孩子的身份证号")
                        ||charSequence.toString().trim().equals("")||charSequence.toString().trim().length()==0){
                    btn_child_ok.setBackgroundColor(Color.GRAY);
                    btn_child_ok.setTextColor(Color.WHITE);
                    btn_child_ok.setBackground(getResources().getDrawable(R.drawable.apply_button1,null));
                }else {
                    btn_child_ok.setBackgroundColor(Color.parseColor("#99CCFF"));
                    btn_child_ok.setTextColor(Color.BLACK);
                    btn_child_ok.setBackground(getResources().getDrawable(R.drawable.apply_button_blue,null));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //点击选择关系文本框时初始化相关参数
        tv_relation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag = 1;
                showPopupwindow();
                relation = "父亲";
            }
        });

        //点击选择关系文本框时初始化相关参数
        tv_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tag = 2;
                showPopupwindow();
                sClass = "小班一班";
            }
        });

        //单选按钮的点击操作
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_man:
                        sex = "男";
                        Toast.makeText(AddChildActivity.this,"男",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_woman:
                        sex = "女";
                        Toast.makeText(AddChildActivity.this,"女",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        //点击确认按钮时进行的判断操作（主要判断各文本内容是否填写,全填写的话按钮会有颜色的变化来提示）
        btn_child_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_relation.getText().equals("请选择与孩子的关系")
                        ||tv_id.getText().equals("请输入孩子的身份证号")
                        ||edt_name.getText().toString().trim().equals("")||edt_name.getText().toString().trim().length()==0){
                    Toast.makeText(AddChildActivity.this,"请补全孩子信息",Toast.LENGTH_LONG).show();
                }else {
                    //先判断身份证号的位数是否正确
                    String str=tv_id.getText().toString();
                    if (str.length()==18){
                        addChildMessage();
                    }else{
                        Toast.makeText(AddChildActivity.this,
                                "身份证号是18位！",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void addChildMessage() {
        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("parentPhone", MyFragment.phoneNum);
        builder.add("parentPhone", ConfigUtil.PHONE);
        builder.add("name",edt_name.getText().toString());
        builder.add("idNum", tv_id.getText().toString());
        builder.add("sex",sex);
        builder.add("sClass",tv_class.getText().toString());
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "AddChildServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("孩子信息更新", "请求失败");
                Toast.makeText(AddChildActivity.this,
                        "请检查网络是否已断开",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.what = 1;
                message.obj=result;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    /*
    * 加载界面布局文件
    * */
    private void initViews() {
        edt_name = findViewById(R.id.edt_child_name);
        btn_child_ok = findViewById(R.id.btn_child_session_ok);
        radioGroup = findViewById(R.id.rg_sex);
        tv_relation = findViewById(R.id.tv_relation_child);
        tv_class = findViewById(R.id.tv_class_child);
        tv_id = findViewById(R.id.tv_child_id);
    }

    /*
    * 初始化wheelview(滚轮选择器)所需要的集合信息
    * */
    private void initDateSource() {
        name.add("父亲");
        name.add("母亲");
        name.add("其他");
        //从数据库中获取班级信息
        searchAllClassInfo();
    }

    /**
     * 从数据库中获取班级信息
     */
    private void searchAllClassInfo() {
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "SearchClassInfoServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("班级信息更新", "请求失败");
                Toast.makeText(AddChildActivity.this,
                        "请检查网络是否已断开",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.what = 2;
                message.obj=result;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    /*
    * 利用pupopwindow实现wheelview(滚轮选择器)的点击弹出效果
    * */
    private void showPopupwindow() {
        //创建popupwindow
        popupWindow = new PopupWindow(this);
        //设置popupwindow显示的宽度（默认不占满屏幕）
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //加载popupwindow布局
        View view = getLayoutInflater().inflate(R.layout.activity_mine_child_popupwindow,null);

        /*
        * 加载popupwindow布局文件
        * 给布局文件设置相应监听器
        * */
        wheelView = view.findViewById(R.id.wheelview);
        wheelView.setCyclic(false); //设置不可循环滚动
        setWheelView(wheelView);//设置wheelview参数

        tv_ok = view.findViewById(R.id.tv_relation_ok);
        tv_cancle = view.findViewById(R.id.tv_relation_cancle);


        //利用tag判断wheelview选中的值（sclass或relation）赋予给哪一个文本框
        //同时赋值完毕后对所有信息是否填全进行判断
        if(tag==1) {
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_relation.setText(relation);
                    if(tv_relation.getText().equals("请选择与孩子的关系")
                            ||tv_class.getText().equals("请选择孩子所在的班级")
                            ||tv_id.getText().equals("请输入孩子的身份证号")
                            ||edt_name.getText().toString().trim().equals("")||edt_name.getText().toString().trim().length()==0){
                        btn_child_ok.setBackgroundColor(Color.GRAY);
                        btn_child_ok.setTextColor(Color.WHITE);
                        btn_child_ok.setBackground(getResources().getDrawable(R.drawable.apply_button1,null));
                    }else {
                        btn_child_ok.setBackgroundColor(Color.parseColor("#90EE90"));
                        btn_child_ok.setTextColor(Color.BLACK);
                        btn_child_ok.setBackground(getResources().getDrawable(R.drawable.apply_button_blue,null));
                    }
                    popupWindow.dismiss();
                }
            });
            tv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }
        if(tag==2) {
            tv_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_class.setText(sClass);
                    if(tv_relation.getText().equals("请选择与孩子的关系")
                            ||tv_class.getText().equals("请选择孩子所在的班级")
                            ||tv_id.getText().equals("请输入孩子的身份证号")
                            ||edt_name.getText().toString().trim().equals("")||edt_name.getText().toString().trim().length()==0){
                        btn_child_ok.setBackgroundColor(Color.GRAY);
                        btn_child_ok.setTextColor(Color.WHITE);
                        btn_child_ok.setBackground(getResources().getDrawable(R.drawable.apply_button1,null));
                    }else {
                        btn_child_ok.setBackgroundColor(Color.parseColor("#90EE90"));
                        btn_child_ok.setTextColor(Color.BLACK);
                        btn_child_ok.setBackground(getResources().getDrawable(R.drawable.apply_button_blue,null));
                    }
                    popupWindow.dismiss();
                }
            });
            tv_cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                }
            });
        }
        //加载根布局（popupwindow要显示在其上面）
        LinearLayout root = findViewById(R.id.root);
        //为popupwindow绑定布局
        popupWindow.setContentView(view);
        //设置popupwindow显示的位置
        popupWindow.showAtLocation(root, Gravity.CENTER,0,0);//指定显示的位置
    }

    /*
    * 给wheelview设置相关参数
    * */
    private void setWheelView(WheelView wheelView){

        //tag用于判断加载两个数据源中的哪一个（sClasses和name数据源）
        //设置数据源
        if(tag==1){
            wheelView.setAdapter(new WheelAdapter() {
                @Override
                public int getItemsCount() {
                    return name.size();
                }

                @Override
                public Object getItem(int index) {
                    return name.get(index);
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }
            });
        }else if(tag==2) {
            wheelView.setAdapter(new WheelAdapter() {
                @Override
                public int getItemsCount() {
                    return sClasses.size();
                }

                @Override
                public Object getItem(int index) {
                    return sClasses.get(index);
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }
            });
        }

        //添加数据源的每一个item被选中时的监听器
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                if(tag==1){
                    relation = name.get(index);
                }else if(tag==2) {
                    sClass = sClasses.get(index);
                }
            }
        });
    }

}