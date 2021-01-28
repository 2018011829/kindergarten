package com.example.myapplication.main.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.contact.CustomUserProvider;
import com.example.myapplication.login.LoginByPasswordActivity;
import com.example.myapplication.main.fragment.HomeFragment;
import com.example.myapplication.main.fragment.MyFragment;
import com.example.myapplication.main.fragment.NewFragment;
import com.example.myapplication.main.fragment.RelationFragment;
import com.example.myapplication.main.util.ChangeStatusBarColor;
import com.example.myapplication.main.util.ConfigUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.im.AVIMOptions;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;

public class MainActivity extends AppCompatActivity {




    @BindView(R.id.main_view_pager) ViewPager viewPager;
    @BindView(R.id.main_tab) TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChangeStatusBarColor.initSystemBar(this);

        //打印下手机号
        Log.i("lxl", "onCreate: "+ ConfigUtil.PHONE);

        ButterKnife.bind(this);

        //为ViewPager设置Adapter
        ViewPager viewPager = setViewPagerAdapter();

        //将ViewPager和TabLayout互相绑定
        BindViewPagerAndTabLayout(viewPager);

        //chatkit获取联系人
        AVIMOptions.getGlobalOptions().setAutoOpen(true);
        LCChatKit.getInstance().open("18831166551", new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (null == e) {
//                                    Intent intent = new Intent(LoginByPasswordActivity.this, TestActivity.class);
//                                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        CustomUserProvider.getContact();

    }


    /**
     * 将ViewPager和TabLayout互相绑定,并设置TabLayout的选择改变事件
     * @param viewPager
     */
    private void BindViewPagerAndTabLayout(final ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
            //设置tab选中的监听器
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.mipmap.home_img1);
                        break;
                    case 1:
                        tab.setIcon(R.mipmap.moments_img1);
                        break;
                    case 2:
                        tab.setIcon(R.mipmap.relations_img1);
                        break;
                    case 3:
                        tab.setIcon(R.mipmap.my_img1);
                        break;
                }
            }
            //设置tab未选中的监听器
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        tab.setIcon(R.mipmap.home_img0);
                        break;
                    case 1:
                        tab.setIcon(R.mipmap.moments_img0);
                        break;
                    case 2:
                        tab.setIcon(R.mipmap.relations_img0);
                        break;
                    case 3:
                        tab.setIcon(R.mipmap.my_img0);
                        break;
                }
            }
        });
    }

    /**
     * 得到ViewPager引用，并设置Adapter
     *
     * @return ViewPager
     */
    private ViewPager setViewPagerAdapter() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),1) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        HomeFragment homeFragment = new HomeFragment();
                        return homeFragment;
                    case 1:
                        NewFragment newFragment = new NewFragment();
                        return newFragment;
                    case 2:
                        RelationFragment relationFragment = new RelationFragment();
                        return relationFragment;
                    case 3:
                        MyFragment myFragment = new MyFragment();
                        return myFragment;
                    default:
                        return null;
                }
            }
            @Override
            public int getCount() {
                return 4;
            }
        });
        return viewPager;
    }
}