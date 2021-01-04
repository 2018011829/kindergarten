package com.example.myapplication.school.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.school.adapter.MyAdapter;
import com.example.myapplication.school.tool.LoadBannerTool;
import com.youth.banner.Banner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SchoolActivity extends AppCompatActivity {

    private MyAdapter adapter;
    private GridView gridView;
    private List<Integer> listGrid;
    private List<Integer> outsideImages = new ArrayList<>();
    private Banner bannerOutside;
    private List<Integer> angleImages = new ArrayList<>();
    private Banner bannerAngle;
    private ImageView imageView;
    private TextView tv_right;
    private TextView tv_bottom;
    static final String text = "华兴幼儿园成立于2000年，我园始终以“服务于社会，服务于家长，服务于幼儿”促进幼儿身心和谐发展为办园宗旨。以塑造优美的校园环境，优秀的教师队伍，优质的园所教育为办园目标。以”在游戏中学习，在实践中获得并创造“为教育理念。以培养”自信 、乐观 、 具有创造力   、具有人文情怀“的孩子为培养目标。全面贯彻落实《幼儿园工作规程》、《幼儿园教育指导纲要》、《3-6岁儿童学习与发展指南》，以提高教师专业化技能为根本，将素质教育贯穿于培养幼儿的全过程，不断提高幼儿园的办园质量，形成了团结、奋进、求实、创新的园风。";

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        findViews();

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
        gridView=findViewById(R.id.gridview);
        bannerOutside=findViewById(R.id.outside_banner);
        bannerAngle=findViewById(R.id.banner_angle);
        tv_right=findViewById(R.id.test_tv_right);
        tv_bottom=findViewById(R.id.test_tv_bottom);
        imageView =findViewById(R.id.test_image);
        imageView.setImageResource(R.mipmap.all);
        imageView.setTag("all");
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        //给外部环境添加轮播照片
        addOutsideImages();
        //给区角添加轮播照片
        addAngleImages();
        //给GridView添加图片
        addGridViewImages();

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

    }

    /**
     * 给区角添加轮播照片
     */
    private void addAngleImages() {
        angleImages.add(R.mipmap.angle1);
        angleImages.add(R.mipmap.angle8);
        angleImages.add(R.mipmap.angle9);
        angleImages.add(R.mipmap.angle10);
        angleImages.add(R.mipmap.angle11);
        LoadBannerTool.startBanner(bannerAngle,angleImages);
    }

    /**
     * 给GridView添加图片
     */
    private void addGridViewImages() {
        listGrid=new ArrayList<>();
        listGrid.add(R.mipmap.angle2);
        listGrid.add(R.mipmap.angle3);
        listGrid.add(R.mipmap.angle7);
        listGrid.add(R.mipmap.angle15);
        listGrid.add(R.mipmap.angle12);
        listGrid.add(R.mipmap.angle13);
        listGrid.add(R.mipmap.angle14);
        listGrid.add(R.mipmap.angle4);

        adapter=new MyAdapter(SchoolActivity.this,R.layout.school_angle_grid_item,listGrid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //点击事件
            }
        });
    }

    /**
     * 给外部环境添加轮播照片
     */
    private void addOutsideImages() {
        outsideImages.add(R.mipmap.all);
        outsideImages.add(R.mipmap.fruit1);
        outsideImages.add(R.mipmap.fruit2);
        outsideImages.add(R.mipmap.fruit3);
        outsideImages.add(R.mipmap.fruit4);
        LoadBannerTool.startBanner(bannerOutside,outsideImages);
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