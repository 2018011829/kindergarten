package com.example.myapplication.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.contact.CustomUserProvider;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.activity.LCIMContactFragment;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;
import cn.leancloud.chatkit.utils.LCIMConstants;
import cn.leancloud.im.v2.AVIMChatRoom;
import cn.leancloud.im.v2.AVIMClient;
import cn.leancloud.im.v2.AVIMConversation;
import cn.leancloud.im.v2.AVIMException;
import cn.leancloud.im.v2.callback.AVIMClientCallback;
import cn.leancloud.im.v2.callback.AVIMConversationCreatedCallback;

public class RelationFragment extends Fragment {
    private View view;

    private static Logger logger = Logger.getLogger(RelationFragment.class.getSimpleName());

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    /**
     * 上一次点击 back 键的时间
     * 用于双击退出的判断
     */
    private static long lastBackTime = 0;

    /**
     * 当双击 back 键在此间隔内是直接触发 onBackPressed
     */
    private final int BACK_INTERVAL = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_test,container,false);

        toolbar = view.findViewById(R.id.toolbar);
        viewPager = view.findViewById(R.id.pager);
        tabLayout = view.findViewById(R.id.tablayout);

        //去掉标题栏
//        getActivity().setTitle(R.string.app_name);


        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        initTabLayout();
        Log.d("MainActivity", "onCreate finished.");

        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d("RelationFragment", "onStart finished.");
    }

    @Override
    public void onResume() {
        super.onResume();
    }
//    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.test_menu_square, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.menu_square_members) {
            gotoSquareConversation();
        } else if (menuId == R.id.menu_quit) {
            LCChatKit.getInstance().close(new AVIMClientCallback() {
                @Override
                public void done(AVIMClient avimClient, AVIMException e) {
                    if (null!= e) {
                        e.printStackTrace();
                    } else {
//                        RelationFragment.this.finish();
                        getActivity().finish();
                    }
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    private void initTabLayout() {
        String[] tabList = new String[]{"会话", "联系人"};
        final Fragment[] fragmentList = new Fragment[] {new LCIMConversationListFragment(),
                new LCIMContactFragment()};

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tabList.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabList[i]));
        }

       TabFragmentAdapter adapter = new  TabFragmentAdapter(getActivity().getSupportFragmentManager(),
                Arrays.asList(fragmentList), Arrays.asList(tabList));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
//          EventBus.getDefault().post(new ConversationFragmentUpdateEvent());
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }


    public class TabFragmentAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragments;
        private List<String> mTitles;

        public TabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
            super(fm);
            mFragments = fragments;
            mTitles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }

    private void gotoSquareConversation() {
        List<LCChatKitUser> userList = CustomUserProvider.getInstance().getAllUsers();
        List<String> idList = new ArrayList<>();
        for (LCChatKitUser user : userList) {
            idList.add(user.getUserId());
        }
        LCChatKit.getInstance().getClient().createChatRoom(
                idList, getString(R.string.square), null, true, new AVIMConversationCreatedCallback() {
                    @Override
                    public void done(AVIMConversation avimConversation, AVIMException e) {
                        if (avimConversation instanceof AVIMChatRoom) {
                            Intent intent = new Intent(getActivity(), LCIMConversationActivity.class);
                            intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
                            startActivity(intent);
                        } else {
                            logger.log(Level.WARNING, "createChatRoom is wrong!");
                        }
                    }
                });
    }
}
