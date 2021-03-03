package com.example.myapplication.main.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.main.entity.ChildConsumeInfo;
import com.example.myapplication.main.util.ConfigUtil;
import com.example.myapplication.money.activity.Attendance;
import com.example.myapplication.money.activity.Money;
import com.example.myapplication.money.activity.UploadPicture;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewFragment extends Fragment {
    private View view;
    private Button btnAttendance;
    private Button btnMoney;
    private Button btnUploadPicture;
    private TextView tvName;
    private TextView tvDay;
    private TextView tvMoney;
    //两张图片
    private ImageView ivWx;
    private ImageView ivZfb;
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String str= (String) msg.obj;
                    ChildConsumeInfo childInfo=new Gson().fromJson(str,ChildConsumeInfo.class);
                    tvDay.setText(childInfo.getDay()+"");
                    tvMoney.setText(childInfo.getMoney()+"");
                    break;
                case 2:
                    String str1= (String) msg.obj;
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new,container,false);
        if (!ConfigUtil.PHONE.equals("") && ConfigUtil.PHONE!=null){ //判断是否登录

            if (!MyFragment.childName.equals("") && MyFragment.childName!=null){ //判断是否选择了孩子
                // 初始化控件
                initView();
                tvName.setText(MyFragment.childName);
                // 设置点击事件的监听器
                setListeners();
                //获取孩子的出勤天数，和需要缴费的金额
                getMoneyMsg();
                //获取二维码信息
                getPictureMsg();
            }else{
                Toast.makeText(getContext(),
                        "您还未选择孩子！",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
           Toast.makeText(getContext(),
                   "您还未登录！",
                   Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void getPictureMsg() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", ConfigUtil.PHONE);
        builder.add("childName", MyFragment.childName);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "SendChargeServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询消费信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.i("查询消费信息", "onResponse: "+result);
                Message message = new Message();
                message.obj = result;
                message.what = 2;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    private void getMoneyMsg() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", ConfigUtil.PHONE);
        builder.add("childName", MyFragment.childName);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "GetChildMoneyServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询消费信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.i("查询消费信息", "onResponse: "+result);
                Message message = new Message();
                message.obj = result;
                message.what = 1;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    // 初始化控件
    private void initView() {
        tvName=view.findViewById(R.id.money_child_name);
        tvDay=view.findViewById(R.id.money_child_day);
        tvMoney=view.findViewById(R.id.money_child);
        btnAttendance = view.findViewById(R.id.btn_attendance);
        btnMoney = view.findViewById(R.id.btn_money);
        btnUploadPicture = view.findViewById(R.id.btn_upload_picture);
        ivWx = view.findViewById(R.id.iv_weixin);
        ivZfb = view.findViewById(R.id.iv_zhifubao);
    }
    // 设置点击事件的监听器
    private void setListeners() {
        MyListener myListener = new MyListener();
        btnAttendance.setOnClickListener(myListener);
        btnMoney.setOnClickListener(myListener);
        btnUploadPicture.setOnClickListener(myListener);


        ivZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("保存", "onLongClick: 点击了图片");

                // 弹出保存图片的对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示");
                builder.setMessage("保存图片到本地");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Drawable drawable = getResources().getDrawable(R.drawable.gathering2,null);
                        BitmapDrawable bd = (BitmapDrawable) drawable;
                        Bitmap bitmap = bd.getBitmap();
                        byte[] b = bitmap2Bytes(bitmap);
                        saveImageToGallery(getContext(),b,"zfb");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    // 自动dismiss
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        ivWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("保存", "onLongClick: 点击了图片");

                // 弹出保存图片的对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("提示");
                builder.setMessage("保存图片到本地");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Drawable drawable = getResources().getDrawable(R.drawable.gathering1,null);
                        BitmapDrawable bd = (BitmapDrawable) drawable;
                        Bitmap bitmap = bd.getBitmap();
                        byte[] b = bitmap2Bytes(bitmap);
                        saveImageToGallery(getContext(),b,"wx");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    // 自动dismiss
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }
    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_attendance:
                    Intent intent = new Intent();
                    intent.setClass(getContext(), Attendance.class);
                    startActivity(intent);
                    break;
                case R.id.btn_money:
                    Intent intent1 = new Intent();
                    intent1.setClass(getContext(), Money.class);
                    startActivity(intent1);
                    break;
                case R.id.btn_upload_picture:
                    Intent intent2 = new Intent();
                    intent2.setClass(getContext(), UploadPicture.class);
                    startActivity(intent2);
                    break;
            }

        }
    }
    /**
     * bitmap转化成byte数组
     * @param bm 需要转换的Bitmap
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    //把图片转换成bitmap形式传递通过intent形式传递过去
    private Bitmap setimage(Drawable view1){
        Bitmap image = ((BitmapDrawable)view1).getBitmap();
        Bitmap bitmap1 = Bitmap.createBitmap(image);
        return bitmap1;
    }

    /**
     * 保存图片到指定路径
     * @param context
     * @param *bitmap   要保存的图片
     * @param fileName 自定义图片名称
     */
    public void  saveImageToGallery(Context context, byte[] data, String fileName) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        fileName = fileName + format.format(new Date())+".JPEG";
        // 保存图片至指定路径
        String storePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"LS" ;
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片(80代表压缩20%)
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //发送广播通知系统图库刷新数据
            System.out.println("发送广播通知系统图库刷新数据");
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

            if (isSuccess) {
                Toast.makeText(context,"图片已保存至"+file,Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context,"图片保存失败",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
