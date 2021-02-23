package com.example.myapplication.contact;

import android.os.Message;
import android.util.Log;

import com.example.myapplication.main.util.ConfigUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 实现自定义用户体系
 */
public class CustomUserProvider implements LCChatProfileProvider {

    private static CustomUserProvider customUserProvider;

    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    private CustomUserProvider() {
    }

    private static List<LCChatKitUser> partUsers = new ArrayList<LCChatKitUser>();

    // 从用户体系获取数据
    public static void getContact(){
        //提交键值对格式的数据
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone", ConfigUtil.PHONE);
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(ConfigUtil.SERVICE_ADDRESS + "GetContactByPhoneServlet")
                .build();
        //获得call对象
        Call call = new OkHttpClient().newCall(request);
        //提交请求并获取响应
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("chatkit", "getContact请求失败");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String result = response.body().string();
                Log.i("lxl","onResponse: "+result);
                if (!result.equals("您还没有联系人[]")){
                    //将得到的结果反序列化
                    Type myType = new TypeToken<ArrayList<LCChatKitUser>>(){}.getType();
                    List<LCChatKitUser> lcChatKitUsers = new Gson().fromJson(result,myType);
                    Log.i("lxl", "onResponse: "+lcChatKitUsers.size());
                    //添加到我的好友列表，替换掉假数据
                    for (LCChatKitUser user:lcChatKitUsers) {
                        Log.i("lxl", "onResponse: 即将添加");
                        partUsers.add(new LCChatKitUser(user.getUserId(), user.getName(), ConfigUtil.SETVER_AVATAR+user.getAvatarUrl()));
                        Log.i("lxl", "onResponse: 添加成功");
                    }
                }



                //处理请求结果
//                Message msg = handler.obtainMessage();
//                msg.what = 1;
//                msg.obj = result;
//                handler.sendMessage(msg);
            }
        });
//    partUsers.add(new LCChatKitUser("Tom", "Tom", "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));

//        partUsers.add(new LCChatKitUser("Tom", "Tom", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1825282309,2865804785&fm=26&gp=0.jpg"));
//        partUsers.add(new LCChatKitUser("Jerry", "Jerry", "http://www.avatarsdb.com/avatars/jerry.jpg"));
//        partUsers.add(new LCChatKitUser("Harry", "Harry", "http://www.avatarsdb.com/avatars/young_harry.jpg"));
//        partUsers.add(new LCChatKitUser("William", "William", "http://www.avatarsdb.com/avatars/william_shakespeare.jpg"));
//        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
    }

    @Override
    public void fetchProfiles(List<String> list, LCChatProfilesCallBack callBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list) {
            for (LCChatKitUser user : partUsers) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        callBack.done(userList, null);
    }

    @Override
    public List<LCChatKitUser> getAllUsers() {
        return partUsers;
    }
}
