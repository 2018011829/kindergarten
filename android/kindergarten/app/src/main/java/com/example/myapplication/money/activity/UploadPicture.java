package com.example.myapplication.money.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.main.util.ConfigUtil;
import com.example.myapplication.money.activity.adapter.SelectPlotAdapter;
import com.example.myapplication.money.activity.bean.MoneyPicture;
import com.example.myapplication.money.activity.util.Tools;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UploadPicture extends AppCompatActivity {
    private static final String TAG = "UploadDynamic";
    @BindView(R.id.recycler)
    RecyclerView recycler;//RecyclerView对象（九宫格）
    private SelectPlotAdapter adapter;
    private ArrayList<String> allSelectList;//所有的图片集合
    private ArrayList<String> categoryLists;//查看图片集合
    private List<LocalMedia> selectList = new ArrayList<>();
    private ImageView ivReturnMoments;
    private OkHttpClient okHttpClient;//定义OKHTTPClient对象属性
    private Handler handler;//定义Handler对象属性
    private Gson gson;//定义Gson对象属性
    private RadioGroup rgGrade;//宝宝年级的单选按钮
    private EditText etBabyGrade;
    private RadioGroup rgClass;//宝宝班级的单选按钮
    private EditText etBabyClass;
    private EditText etBabyName;//宝宝姓名
    private MoneyPicture moneyPicture;//截图文本对象
    private void initHandler() {
        handler = new Handler(){//handlerThread.getLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what){
                    case 1://如果服务端返回的数据是字符串
                        //获取图片资源路径
                        String json = (String) msg.obj;//接收到的是一个说说对象
                        Toast.makeText(UploadPicture.this,json,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_upload);
        ButterKnife.bind(this);
        if (null == allSelectList) {
            allSelectList = new ArrayList();
        }
        if (null == categoryLists) {
            categoryLists = new ArrayList();
        }
        Tools.requestPermissions(UploadPicture.this);
        initAdapter();
        initView();
        //为radioGroup设置一个监听器:setOnCheckedChanged()
        rgGrade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                etBabyGrade.setText(radbtn.getText());
            }
        });
        rgClass.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radbtn = (RadioButton) findViewById(checkedId);
                etBabyClass.setText(radbtn.getText());
            }
        });

    }
    private void initView(){
        initOkHttpClient();//初始化OKHTTPClient对象
        initHandler();//初始化Handler对象
        initGson();//初始化Gson
        moneyPicture = new MoneyPicture();
        rgGrade = findViewById(R.id.rg_grade);
        rgClass = findViewById(R.id.rg_class);
        etBabyGrade = findViewById(R.id.baby_grade);
        etBabyClass = findViewById(R.id.baby_class);
        etBabyName = findViewById(R.id.baby_name);
    }
    //设置点击事件
    @OnClick({R.id.upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.upload:
                String babyName= etBabyName.getText().toString();
                String babyGrade = etBabyGrade.getText().toString();
                String babylass = etBabyClass.getText().toString();
                if (TextUtils.isEmpty(babyName)) {
                    Toast.makeText(this, "请输入宝宝姓名", Toast.LENGTH_LONG).show();
                    return;
                }else if(TextUtils.isEmpty(babyGrade)){
                    Toast.makeText(this, "请选择宝宝年级", Toast.LENGTH_LONG).show();
                    return;
                }else if(TextUtils.isEmpty(babylass)){
                    Toast.makeText(this, "请选择宝宝班级", Toast.LENGTH_LONG).show();
                    return;
                }
                if (allSelectList.size() == 0) {
                    Toast.makeText(this, "请选择图片进行上传", Toast.LENGTH_LONG).show();
                    return;
                }
                moneyPicture.setBabyGrade(babyGrade);
                moneyPicture.setBabyClass(babylass);
                moneyPicture.setBabyName(babyName);
                moneyPicture.setPhone(ConfigUtil.PHONE);
                //序列化
                String json = gson.toJson(moneyPicture);
                sendTimeAndContentToServer(json);//向服务端发送当前用户手机号，当前时间和说说文本
                String pictureUrl = allSelectList.toString().substring(1,allSelectList.toString().length()-1);//图片路径集合
                List<String> pictureUrls = Arrays.asList(pictureUrl.split(", "));//将图片路径集合分割开
                for(int i=0;i<pictureUrls.size();i++){
                    sendPictureToServer(pictureUrls.get(i));//循环发送多张图片
                }
                finish();
                break;
        }
    }
    private void initAdapter() {
        //最多9张图片
        adapter = new SelectPlotAdapter(this, 9);//定义adapter对象
        recycler.setLayoutManager(new GridLayoutManager(this, 3));
        adapter.setImageList(allSelectList);//所有图片的集合
        recycler.setAdapter(adapter);
        adapter.setListener(new SelectPlotAdapter.CallbackListener() {
            @Override
            public void add() {
                //可添加的最大张数=9-当前已选的张数
                int size = 9 - allSelectList.size();
                Tools.galleryPictures(UploadPicture.this, size);
            }
            @Override
            public void delete(int position) {
                allSelectList.remove(position);
                categoryLists.remove(position);
                adapter.setImageList(allSelectList);//再set所有集合
            }
            @Override
            public void item(int position, List<String> mList) {
                selectList.clear();
                for (int i = 0; i < allSelectList.size(); i++) {
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(allSelectList.get(i));
                    selectList.add(localMedia);
                }
                //①、图片选择器自带预览
                PictureSelector.create(UploadPicture.this)
                        .themeStyle(R.style.picture_default_style)
                        .openExternalPreview(position, selectList);
                //②:自定义布局预览
                Tools.startPhotoViewActivity(UploadPicture.this, categoryLists, position);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 结果回调
            selectList = PictureSelector.obtainMultipleResult(data);
            showSelectPic(selectList);
        }
    }
    private void showSelectPic(List<LocalMedia> result) {
        for (int i = 0; i < result.size(); i++) {
            String path;
            //判断是否10.0以上
            if (Build.VERSION.SDK_INT >= 29) {
                path = result.get(i).getAndroidQToPath();
            } else {
                path = result.get(i).getPath();
            }
            allSelectList.add(path);
            categoryLists.add(path);
            Log.e(TAG, "图片链接: " + path);
        }
        adapter.setImageList(allSelectList);
    }
    //向服务端发送图片
    private void sendPictureToServer(String urlPath) {
        final String path= ConfigUtil.SERVICE_ADDRESS+"MomentsPicturesServlet";
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    OutputStream os=conn.getOutputStream();
                    InputStream inputStream=new FileInputStream(urlPath);
                    int b=-1;
                    while ((b=inputStream.read())!=-1){
                        os.write(b);
                    }
                    conn.getInputStream();
                    os.flush();
                    os.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    // 采用POST请求方式提交截图文本信息
    private void sendTimeAndContentToServer(String content) {
        //2 创建Request对象
        //1) 使用RequestBody封装请求数据
        //获取待传输数据对应的MIME类型
        MediaType type = MediaType.parse("text/plain");
        //序列化
        FormBody formBody =
                new FormBody.Builder()
                        .add("content",content)
                        .build();
        //2) 创建请求对象
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS +"UploadScreenShot")
                .post(formBody)
                .build();
        //3. 创建CALL对象
        Call call = okHttpClient.newCall(request);
        //4. 提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("lww", "请求失败");
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        });
    }
    private void initGson() {
        gson = new GsonBuilder()//创建GsonBuilder对象
                .setPrettyPrinting()//格式化输出
                .serializeNulls()//允许输出Null值属性
                .setDateFormat("YY:MM:dd")//日期格式化
                .create();//创建Gson对象
    }
    private void initOkHttpClient() {
        okHttpClient = new OkHttpClient();
    }


}
