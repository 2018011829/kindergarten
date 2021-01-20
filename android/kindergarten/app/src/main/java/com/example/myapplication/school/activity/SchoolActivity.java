package com.example.myapplication.school.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.example.myapplication.main.activity.my.ShowApplyInfoActivity;
import com.example.myapplication.main.util.ConfigUtil;
import com.example.myapplication.school.adapter.MyAdapter;
import com.example.myapplication.school.entity.SchoolOutside;
import com.example.myapplication.school.entity.ThreePicture;
import com.example.myapplication.school.entity.TwoPicture;
import com.example.myapplication.school.tool.LoadBannerTool;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SchoolActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private GridView gridView;
    private List<String> listGrid = new ArrayList<>();
    private List<String> outsideImages = new ArrayList<>();
    private Banner bannerOutside;
    private List<String> angleImages = new ArrayList<>();
    private Banner bannerAngle;
    private ImageView imageView;
    private TextView tv_right;
    private TextView tv_bottom;
    private String text;
    private TextView tv_school_outside;
    private TextView tv_school_angle_banner;
    private TextView tv_school_passageway;
    private ImageView iv_school_passageway1;
    private ImageView iv_school_passageway2;
    private TextView tv_school_inside;
    private ImageView iv_school_inside1;
    private ImageView iv_school_inside2;
    private TextView tv_school_play;
    private ImageView iv_school_play1;
    private ImageView iv_school_play2;
    private ImageView iv_school_play3;
    private TextView tv_school_gridview;

    boolean imageMeasured = false;
    // 屏幕的高度
    int screenWidth = 0;
    // 总共可以放多少个字
    int count = 0;
    // textView全部字符的宽度
    float textTotalWidth = 0.0f;
    // textView一个字的宽度
    float textWidth = 0.0f;
    Paint paint =new Paint();

    private Handler handler=new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1://简介文字信息
                    text = (String) msg.obj;
                    /**
                     * 获取一个字的宽度
                     */
                    textWidth = tv_right.getTextSize();
                    paint.setTextSize(textWidth);
                    /**
                     * 因为图片一开始的时候，高度是测量不出来的，通过增加一个监听器，即可获取其图片的高度和长度
                     */
                    ViewTreeObserver vto = imageView.getViewTreeObserver();
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        public boolean onPreDraw() {
                            if(!imageMeasured) {
                                imageMeasured =true;
                                int height = imageView.getMeasuredHeight();
                                int width = imageView.getMeasuredWidth();
                                drawImageViewDone(width, height);
                            }
                            return imageMeasured;
                        }
                    });
                    break;
                case 2://获取校园外部环境
                    String str= (String) msg.obj;
                    if (!str.equals("") && str.equals("未找到校园外部环境信息")){
                        Toast.makeText(SchoolActivity.this,
                                "未找到校园外部环境信息！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        SchoolOutside schoolOutside =new Gson().fromJson(str, SchoolOutside.class);
                        tv_school_outside.setText(schoolOutside.getDescription().replace("\\n","\n"));
                        outsideImages.addAll(schoolOutside.getList());
                        LoadBannerTool.startBanner(bannerOutside,outsideImages);
                        bannerOutside.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                Intent intent=new Intent(SchoolActivity.this,ImageShower.class);
                                intent.putExtra("tag",outsideImages.get(position));
                                startActivity(intent);
                            }
                        });
                    }
                    break;
                case 3://获取校园区角环境
                    String str1= (String) msg.obj;
                    if (!str1.equals("") && str1.equals("未找到校园区角环境信息")){
                        Toast.makeText(SchoolActivity.this,
                                "未找到校园区角环境信息！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        SchoolOutside schoolOutside =new Gson().fromJson(str1, SchoolOutside.class);
                        tv_school_angle_banner.setText(schoolOutside.getDescription().replace("\\n","\n"));
                        angleImages.addAll(schoolOutside.getList());
                        LoadBannerTool.startBanner(bannerAngle,angleImages);
                        bannerAngle.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                Intent intent=new Intent(SchoolActivity.this,ImageShower.class);
                                intent.putExtra("tag",angleImages.get(position));
                                startActivity(intent);
                            }
                        });
                    }
                    break;
                case 4://校园楼道环境信息
                    String str2= (String) msg.obj;
                    if (!str2.equals("") && str2.equals("未找到校园楼道环境信息")){
                        Toast.makeText(SchoolActivity.this,
                                "未找到校园楼道环境信息！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        TwoPicture twoPicture =new Gson().fromJson(str2, TwoPicture.class);
                        tv_school_passageway.setText(twoPicture.getDescription().replace("\\n","\n"));
                        Glide.with(SchoolActivity.this)
                                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+twoPicture.getPicture1())
                                .placeholder(R.mipmap.loading)
                                .error(R.mipmap.faliure)
                                .into(iv_school_passageway1);
                        Glide.with(SchoolActivity.this)
                                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+twoPicture.getPicture2())
                                .placeholder(R.mipmap.loading)
                                .error(R.mipmap.faliure)
                                .into(iv_school_passageway2);
                    }
                    break;
                case 5://校园内部环境信息
                    String str3= (String) msg.obj;
                    if (!str3.equals("") && str3.equals("未找到校园内部环境信息")){
                        Toast.makeText(SchoolActivity.this,
                                "未找到校园内部环境信息！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        TwoPicture twoPicture =new Gson().fromJson(str3, TwoPicture.class);
                        tv_school_inside.setText(twoPicture.getDescription().replace("\\n","\n"));
                        Glide.with(SchoolActivity.this)
                                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+twoPicture.getPicture1())
                                .placeholder(R.mipmap.loading)
                                .error(R.mipmap.faliure)
                                .into(iv_school_inside1);
                        Glide.with(SchoolActivity.this)
                                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+twoPicture.getPicture2())
                                .placeholder(R.mipmap.loading)
                                .error(R.mipmap.faliure)
                                .into(iv_school_inside2);
                    }
                    break;
                case 6://校园游乐设施环境信息
                    String str4= (String) msg.obj;
                    if (!str4.equals("") && str4.equals("未找到游乐设施环境信息")){
                        Toast.makeText(SchoolActivity.this,
                                "未找到游乐设施环境信息！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        ThreePicture threePicture =new Gson().fromJson(str4, ThreePicture.class);
                        tv_school_play.setText(threePicture.getDescription().replace("\\n","\n"));
                        Glide.with(SchoolActivity.this)
                                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+threePicture.getPicture1())
                                .placeholder(R.mipmap.loading)
                                .error(R.mipmap.faliure)
                                .into(iv_school_play1);
                        Glide.with(SchoolActivity.this)
                                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+threePicture.getPicture2())
                                .placeholder(R.mipmap.loading)
                                .error(R.mipmap.faliure)
                                .into(iv_school_play2);
                        Glide.with(SchoolActivity.this)
                                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/"+threePicture.getPicture3())
                                .placeholder(R.mipmap.loading)
                                .error(R.mipmap.faliure)
                                .into(iv_school_play3);
                    }
                    break;
                case 7://给GridView添加图片
                    String str5= (String) msg.obj;
                    if (!str5.equals("") && str5.equals("未找到GridView环境信息")){
                        Toast.makeText(SchoolActivity.this,
                                "未找到GridView环境信息！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        SchoolOutside schoolOutside =new Gson().fromJson(str5, SchoolOutside.class);
                        tv_school_gridview.setText(schoolOutside.getDescription().replace("\\n","\n"));
                        listGrid.addAll(schoolOutside.getList());

                        adapter=new MyAdapter(SchoolActivity.this,R.layout.school_angle_grid_item,listGrid);
                        gridView.setAdapter(adapter);
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                //点击事件
                            }
                        });
                    }
                    break;
            }
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        findViews();
        initData();
    }

    private void initData() {
        //加载幼儿园简洁的全景图片
        Glide.with(SchoolActivity.this)
                .load(ConfigUtil.SERVICE_ADDRESS + "imgs/schoolInfoPicture/all.png")
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.faliure)
                .into(imageView);
        //加载幼儿园简介文字信息
        initDataAboutIntroduceText();
    }

    /**
     * 加载幼儿园简介文字信息
     */
    public void initDataAboutIntroduceText() {
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "InitDataAboutIntroduceText")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("获取幼儿园简介信息失败", "请求失败");
                Toast.makeText(SchoolActivity.this,
                        "网络错误，请连接网络",
                        Toast.LENGTH_SHORT).show();
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

    /**
     * 显示图片
     * 将bitmap对象存入临时文件里
     * @param v
     */
    public void show_click(View v){
        Object tag=v.getTag();
        Intent intent=new Intent(this,ImageShower.class);
        intent.putExtra("tag",tag.toString());
        startActivity(intent);
    }

    private void findViews() {
        tv_school_gridview=findViewById(R.id.tv_school_gridview);
        iv_school_play1=findViewById(R.id.iv_school_play1);
        iv_school_play2=findViewById(R.id.iv_school_play2);
        iv_school_play3=findViewById(R.id.iv_school_play3);
        tv_school_play=findViewById(R.id.tv_school_play);
        iv_school_inside1=findViewById(R.id.iv_school_inside1);
        iv_school_inside2=findViewById(R.id.iv_school_inside2);
        tv_school_inside=findViewById(R.id.tv_school_inside);
        iv_school_passageway1=findViewById(R.id.iv_school_passageway1);
        iv_school_passageway2=findViewById(R.id.iv_school_passageway2);
        tv_school_passageway=findViewById(R.id.tv_school_passageway);
        tv_school_angle_banner=findViewById(R.id.tv_school_angle_banner);
        tv_school_outside=findViewById(R.id.tv_school_outside);
        gridView=findViewById(R.id.gridview);
        bannerOutside=findViewById(R.id.outside_banner);
        bannerAngle=findViewById(R.id.banner_angle);
        tv_right=findViewById(R.id.test_tv_right);
        tv_bottom=findViewById(R.id.test_tv_bottom);
        imageView =findViewById(R.id.test_image);
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        //给外部环境添加轮播照片
        addOutsideImages();
        //给区角添加轮播照片
        addAngleImages();
        //给楼道添加图片
        addPassagewayImages();
        //给室内添加图片
        addInsideImages();
        //给游乐设施添加图片
        addPlayImages();
        //给GridView添加图片
        addGridViewImages();

    }

    /**
     * 给游乐设施添加图片
     */
    private void addPlayImages() {
        //从服务器端获取游乐设施环境的描述与图片
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "InitDataAboutSchoolPlay")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("获取游乐设施环境信息失败", "请求失败");
                Toast.makeText(SchoolActivity.this,
                        "网络错误，请连接网络",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 6;
                handler.sendMessage(message);
                Log.i("游乐设施环境", result);
            }
        });
    }

    /**
     * 给室内添加图片
     */
    private void addInsideImages() {
        //从服务器端获取外部环境的描述与图片
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "InitDataAboutSchoolInside")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("获取室内环境信息失败", "请求失败");
                Toast.makeText(SchoolActivity.this,
                        "网络错误，请连接网络",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 5;
                handler.sendMessage(message);
                Log.i("室内环境", result);
            }
        });
    }

    /**
     * 给楼道添加图片
     */
    private void addPassagewayImages() {
        //从服务器端获取外部环境的描述与图片
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "InitDataAboutSchoolPassageway")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("获取楼道环境信息失败", "请求失败");
                Toast.makeText(SchoolActivity.this,
                        "网络错误，请连接网络",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 4;
                handler.sendMessage(message);
                Log.i("楼道环境", result);
            }
        });
    }

    /**
     * 给区角添加轮播照片
     */
    private void addAngleImages() {
        //从服务器端获取外部环境的描述与图片
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "InitDataAboutSchoolAngleBanner")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("获取幼儿园区角环境轮播图信息失败", "请求失败");
                Toast.makeText(SchoolActivity.this,
                        "网络错误，请连接网络",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 3;
                handler.sendMessage(message);
                Log.i("区角环境", result);
            }
        });

    }

    /**
     * 给GridView添加图片
     */
    private void addGridViewImages() {
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "InitDataAboutSchoolGridView")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("获取幼儿园GridView环境轮播图信息失败", "请求失败");
                Toast.makeText(SchoolActivity.this,
                        "网络错误，请连接网络",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 7;
                handler.sendMessage(message);
                Log.i("GridView环境", result);
            }
        });

    }

    /**
     * 给外部环境添加轮播照片
     */
    private void addOutsideImages() {
        //从服务器端获取外部环境的描述与图片
        Request request = new Request.Builder()
                .url(ConfigUtil.SERVICE_ADDRESS + "InitDataAboutSchoolOutside")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("获取幼儿园外部环境信息失败", "请求失败");
                Toast.makeText(SchoolActivity.this,
                        "网络错误，请连接网络",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.obj = result;
                message.what = 2;
                handler.sendMessage(message);
                Log.i("外部环境", result);
            }
        });

    }



    private void drawImageViewDone(int width, int height) {
        // 一行字体的高度
        int lineHeight = tv_right.getLineHeight();
        // 可以放多少行
        int lineCount = (int) Math.ceil((double) height / (double) lineHeight);
        // 一行的宽度
        float rowWidth = screenWidth - width - tv_right.getPaddingLeft() - tv_right.getPaddingRight();
        // 一行可以放多少个字
        int columnCount = (int) (rowWidth / textWidth);
        // 总共字体数等于 行数*每行个数
        count = lineCount * columnCount;
        // 一个TextView中所有字符串的宽度和（字体数*每个字的宽度）
        textTotalWidth = (float) ((float) count * textWidth);
        measureText();
        tv_right.setText(text.substring(0, count));
        // 检查行数是否大于设定的行数，如果大于的话，就每次减少一个字符，重新计算行数与设定的一致
        while(tv_right.getLineCount() >= lineCount) {
            count -=1;
            tv_right.setText(text.substring(0, count));
        }
        tv_bottom.setPadding(0, lineCount * lineHeight - height+20,0, 0);
        tv_bottom.setText(text.substring(count));
    }
    /**
     * 测量已经填充的长度，计算其剩下的长度
     */
    private void measureText() {
        String string = text.substring(0, count);
        float size = paint.measureText(string);
        int remainCount = (int) ((textTotalWidth - size) / textWidth);
        if(remainCount > 0) {
            count += remainCount;
            measureText();
        }
    }
}