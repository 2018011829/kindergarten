package com.example.myapplication.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.contrarywind.adapter.WheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.example.myapplication.R;
import com.example.myapplication.apply.activity.Bean.ApplyInfo;
import com.example.myapplication.main.activity.my.AddChildActivity;
import com.example.myapplication.main.activity.my.ApplyInfoActivity;
import com.example.myapplication.main.activity.my.EditorParentActivity;
import com.example.myapplication.main.activity.my.SettingActivity;
import com.example.myapplication.main.entity.Child;
import com.example.myapplication.main.entity.UserParent;
import com.example.myapplication.main.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class MyFragment extends Fragment {

//    public static String phoneNum="19831127142"; //当前手机号

    //个人信息填充
    private TextView tvUserName;
    private TextView tvUserPhone;
    private ImageView ivUserPic;
    private UserParent userParent;


    private static String phone=ConfigUtil.PHONE;
    private View view;
    private RelativeLayout addChild;
    private LinearLayout chooseChild;
    private LinearLayout applyInfo;
    private PopupWindow popupWindow;
    private WheelView wheelView;
    private TextView tv_ok;
    private TextView tv_cancle;
    private TextView tv_mine_myChildName;
    private ImageView iv_mine_myChildImg;
    private LinearLayout ll_mine_editorParent;
    private RelativeLayout rl_mine_setting;
    public static String childName=""; //纪录当前登录的手机号下的孩子姓名，用来在收藏前进行判断、存储收藏信息
    public static String childSex="" ; //记录当前从孩子数据源中选择的孩子性别
    public static String childGrade=""; //记录当前从孩子数据源中选择的孩子年级
    private String cname;
    private String cgrade;
    private String csex;
    private String cClass;
    private String childMessage = "";
    private List<String> children = new ArrayList<>();
    private Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    String str1= (String) msg.obj;
                    if (!str1.equals("") && str1.equals("未找到相关报名信息")){
                        Toast.makeText(getContext(),
                                "您还没有进行报名操作！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent=new Intent(getContext(), ApplyInfoActivity.class);
                        startActivity(intent);
                    }
                    break;
                case 2://加载孩子列表
                    String str= (String) msg.obj;
                    if (!str.equals("") && str.equals("您还没有添加孩子")){
                        Toast.makeText(getContext(),
                                "您还没有添加孩子",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        try {
//                            children = new ArrayList<>();
                            JSONObject jChildren = new JSONObject(str);
                            JSONArray jArray = jChildren.getJSONArray("children");
                            for(int i= 0;i<jArray.length();i++){
                                String json = jArray.getJSONObject(i).toString();
                                Log.i("json", json);
                                Child child = new Gson().fromJson(json, Child.class);
                                childMessage = child.getName()+" "+child.getGrade()+" "+child.getSex()+" "+child.getsClass();
                                children.add(childMessage);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 3:
                    String userMsg = msg.obj.toString();
                    userParent = new Gson().fromJson(userMsg,UserParent.class);
                    tvUserName.setText(userParent.getNickname());
                    tvUserPhone.setText(userParent.getPhone());
                    Glide.with(getActivity()).load(ConfigUtil.SETVER_AVATAR+userParent.getAvator()).into(ivUserPic);
                    Log.e("path",ConfigUtil.SETVER_AVATAR+userParent.getAvator());
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my,container,false);
        findViews();
        //个人信息填充
        getUserMsg();
        Log.e("phone",phone);

        queryChildren();
        //判断是否已经选择过孩子，是直接显示之前选择过的
        if (childName!=null && !childName.equals("")){
            tv_mine_myChildName.setText(childName);
            if(childSex.equals("男")){
                iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.boy));
            }else {
                iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.girl));
            }
        }

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getUserMsg();
        children.clear();
//        queryChildren();
    }

    /**
     * 填充信息 用户昵称 电话 头像
     */
    private void getUserMsg() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", ConfigUtil.PHONE);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "GetUserParentMsgServlet")
                .build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("查询User信息", "请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.i("userMsg", "onResponse: "+result);
                Message message = new Message();
                message.obj = result;
                message.what = 3;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    private void queryChildren() {
        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("phone", phoneNum);
        builder.add("phone", ConfigUtil.PHONE);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "QueryChildrenServlet")
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
                message.what = 2;
                handler.sendMessage(message);
                Log.i("result", result);
            }
        });
    }

    public class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rl_mine_addChild://点击添加孩子,跳转到输入孩子信息界面
                    Intent addChild = new Intent();
                    addChild.setClass(getContext(), AddChildActivity.class);
                    startActivity(addChild);

                    break;
                case R.id.ll_mine_mychildren://点击选择孩子，弹出选择孩子的框
                    if(children==null || children.size()==0){
                        Toast.makeText(getActivity(),"当前暂无您孩子的信息，请先添加孩子！",Toast.LENGTH_SHORT).show();
                    }else {
                        String[] message= children.get(0).split(" ");
                        cname = message[0];
                        cgrade = message[1];
                        csex = message[2];
                        cClass = message[3];
                        showSelectChildPopupwindow();
                    }

                    break;
                case R.id.ll_applyinfo_by_phone://点击报名信息，跳转到显示该手机号下的报名信息的activity
                    queryApplyInfoByPhoneNum();

                    break;
                case R.id.ll_mine_editorParent://点击跳转到编辑家长资料界面
                    Intent editorParent = new Intent();
                    editorParent.setClass(getContext(), EditorParentActivity.class);
                    editorParent.putExtra("userName",userParent.getNickname());
                    editorParent.putExtra("imgName",userParent.getAvator());

                    startActivity(editorParent);

                    break;

                case R.id.rl_mine_setting://点击跳转到设置中心
                    Intent intent = new Intent();
                    intent.setClass(getContext(), SettingActivity.class);
                    intent.putExtra("userPhone",userParent.getPhone());
                    startActivity(intent);
                    break;
            }
        }
    }

    private void queryApplyInfoByPhoneNum() {
        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("phone", MyFragment.phoneNum);
        builder.add("phone", ConfigUtil.PHONE);
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(ConfigUtil.SERVICE_ADDRESS + "QueryApplyInfoServlet")
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

    private void showSelectChildPopupwindow() {
        //创建popupwindow
        popupWindow = new PopupWindow(this.getActivity());
        //设置popupwindow显示的宽度（默认不占满屏幕）
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        //加载popupwindow布局
        View view2 = getLayoutInflater().inflate(R.layout.activity_mine_child_popupwindow,null);

        /*
         * 加载popupwindow布局文件
         * 给布局文件设置相应监听器
         * */
        wheelView = view2.findViewById(R.id.wheelview);
        wheelView.setCyclic(false); //设置不可循环滚动
        setWheelView(wheelView);//设置wheelview参数

        tv_ok = view2.findViewById(R.id.tv_relation_ok);
        tv_cancle = view2.findViewById(R.id.tv_relation_cancle);


        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childName = cname;
                childGrade = cClass;
                childSex = csex;
                tv_mine_myChildName.setText(childName);
                if(childSex.equals("男")){
                    iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.boy));
                }else {
                    iv_mine_myChildImg.setImageDrawable(MyFragment.this.getContext().getDrawable(R.drawable.girl));
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

        //加载根布局（popupwindow要显示在其上面）
        ScrollView root = view.findViewById(R.id.sv_root);
        //为popupwindow绑定布局
        popupWindow.setContentView(view2);
        //设置popupwindow显示的位置
        popupWindow.showAtLocation(root, Gravity.CENTER,0,0);//指定显示的位置
    }

    private void setWheelView(WheelView wheelView) {
        wheelView.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return children.size();
            }

            @Override
            public Object getItem(int index) {
                return children.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }
        });

        //添加数据源的每一个item被选中时的监听器
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                String [] childSessions = children.get(index).split(" ");
                cname = childSessions[0];
                cgrade = childSessions[1];
                csex = childSessions[2];
            }
        });
    }

    private void findViews() {
        //个人信息
        tvUserName = view.findViewById(R.id.tv_mine_userName);
        tvUserPhone = view.findViewById(R.id.tv_mine_phone);
        ivUserPic = view.findViewById(R.id.iv_headPhoto);



        MyListener myListener=new MyListener();
        addChild=view.findViewById(R.id.rl_mine_addChild);
        addChild.setOnClickListener(myListener);
        chooseChild=view.findViewById(R.id.ll_mine_mychildren);
        chooseChild.setOnClickListener(myListener);
        tv_mine_myChildName = view.findViewById(R.id.tv_mine_myChildName);
        iv_mine_myChildImg = view.findViewById(R.id.iv_mine_myChildImg);
        applyInfo=view.findViewById(R.id.ll_applyinfo_by_phone);
        applyInfo.setOnClickListener(myListener);
        ll_mine_editorParent  = view.findViewById(R.id.ll_mine_editorParent);
        ll_mine_editorParent.setOnClickListener(myListener);
        rl_mine_setting = view.findViewById(R.id.rl_mine_setting);
        rl_mine_setting.setOnClickListener(myListener);
    }
}
