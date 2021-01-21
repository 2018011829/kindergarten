package com.example.myapplication.contact.util;

import android.app.Application;
import android.util.Log;

import com.example.myapplication.contact.CustomUserProvider;
import com.example.myapplication.contact.TestActivity;

import cn.leancloud.AVInstallation;
import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;
import cn.leancloud.AVObject;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.im.AVIMOptions;
import cn.leancloud.push.PushService;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by wli on 16/2/24.
 */
public class App extends Application {


    // 此 id 与 key 仅供测试使用
    private final String APP_ID = "ywlMubJ7lbzajm8S7YnPlK7n-gzGzoHsz";
    private final String APP_KEY = "XS2MbN6mytDYB0yMkCnHTyQf";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(App.class.getSimpleName(), "App#onCreate()");

        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);
//    AVOSCloud.useAVCloudUS();
        AVIMOptions.getGlobalOptions().setDisableAutoLogin4Push(true);
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY, "https://dyrq8yfh.lc-cn-n1-shared.com");

        PushService.setDefaultPushCallback(this, TestActivity.class);
        PushService.setAutoWakeUp(true);
        PushService.setDefaultChannelId(this, "default");

        AVInstallation.getCurrentInstallation().saveInBackground().subscribe(new Observer<AVObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AVObject avObject) {
                String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
                System.out.println("---  " + installationId);
            }

            @Override
            public void onError(Throwable e) {
                // 保存失败，输出错误信息
                System.out.println("failed to save installation.");
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
