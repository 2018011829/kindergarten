package com.example.myapplication.main.activity.my;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.main.util.ChangeStatusBarColor;
import com.example.myapplication.main.util.ConfigUtil;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditorParentActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG="EditorParentActivity";
    private ImageView iv_head;
    private Button btn_commit;
    private EditText edt_name;
    private Bitmap bitmap;
    private String imgName;
    private LinearLayout linearBack;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    upLoadParentMessage();
                    break;
                case 2:
                    setResult(200,new Intent());
                    finish();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_editor_parent);
        ChangeStatusBarColor.initSystemBar(this);

        //给界面添加返回按钮相关代码
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        findViews();
        initData();
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    private void initData() {

        Intent intent = getIntent();
        edt_name.setText(intent.getStringExtra("userName"));
        imgName = intent.getStringExtra("imgName");
        Glide.with(this)
                .load(ConfigUtil.SETVER_AVATAR+imgName)
                .circleCrop()
                .into(iv_head);
    }

    private void findViews() {
        iv_head = findViewById(R.id.iv_mine_editorParent_head);
        edt_name = findViewById(R.id.edt_mine_editorName);
        btn_commit = findViewById(R.id.btn_mine_editorCommit);
        linearBack = findViewById(R.id.linear_toback);
        iv_head.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_mine_editorParent_head:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
                break;
            case R.id.btn_mine_editorCommit:
                if(edt_name.getText().toString()!=null && !edt_name.getText().toString().equals("")) {
                    if (bitmap != null) {
                        Toast.makeText(this,"修改中,请稍等",Toast.LENGTH_SHORT).show();

                        upBitmap(bitmap);
                        bitmap = null;
                    } else {
                        upLoadParentMessage();
                    }
                }else {
                    Toast.makeText(this,"用户昵称不能为空！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void upBitmap(final Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        final byte[] bytes = baos.toByteArray();
        new Thread() {
            @Override
            public void run() {
                //获得网络流
                try {
                    URL url = new URL(ConfigUtil.SERVICE_ADDRESS + "DownLoadHeadPortraitServlet");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置网络请求的方式为POST
                    conn.setRequestMethod("POST");
                    OutputStream outputStream = conn.getOutputStream();
                    BufferedOutputStream out = new BufferedOutputStream(outputStream);
                    Log.e("bitmap", "run: 上传图片，建立连接");
                    out.write(bytes);
                    out.flush();
                    Log.e("图片touxiang上传", "run: 完成");
                    conn.getInputStream();
                    outputStream.close();
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //输出数据输出流
                //写数据
            }
        }.start();
    }

    private void upLoadParentMessage() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", ConfigUtil.PHONE);
        builder.add("nickName",edt_name.getText().toString());
        builder.add("headName",imgName);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "DownLoadMessageServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("家长信息更新", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                Log.i("lsq:",uri.toString());

                //使用content的接口
                ContentResolver cr = this.getContentResolver();
                Cursor cursor = cr.query(uri, null, null, null, null);
                cursor.moveToFirst();
                String imgName = cursor.getString(3); //图片文件名
                Log.e("图片的文件名：", "onActivityResult: " + imgName);
                //获取图片
                try {
//                    bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                   bitmap= getSmallBitmap(cr.openInputStream(uri));

                Glide.with(this)
                        .load(bitmap)
                        .circleCrop()
                        .into(iv_head);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                //操作错误或没有选择图片
                Log.e("myFragmetn", "operation error");
            }
        }
    }


    //压缩图片方法，对图片进行尺寸压缩
    // 根据路径获得图片并压缩，返回bitmap用于显示
    public  Bitmap getSmallBitmap(InputStream inputStream) {//图片所在SD卡的路径
        Log.e(TAG, "getSmallBitmap: " );
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = calculateInSampleSize(options, 100, 100);//自定义一个宽和高
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null,options);
    }

    //计算图片的缩放值
    public  int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
        final int height = options.outHeight;//获取图片的高
        final int width = options.outWidth;//获取图片的框
        int inSampleSize = 4;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;//求出缩放值
    }

}