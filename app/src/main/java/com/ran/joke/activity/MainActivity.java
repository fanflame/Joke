package com.ran.joke.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ran.joke.R;
import com.ran.joke.activity.adapter.MainPagerAdapter;
import com.ran.joke.activity.fragment.FragmentNew;
import com.ran.joke.activity.fragment.FragmentRandom;
import com.ran.joke.activity.fragment.FragmentSetting;
import com.ran.joke.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends ActivityBase {
    private final int PRESS_EXIT_INTERVAL= 1000;

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private FragmentNew fragmentNew;
    private FragmentRandom fragmentRandom;
    private FragmentSetting fragmentSetting;
    private MainPagerAdapter adapter;
    private long lastPressBackTime;

    public static void startActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fragmentNew = FragmentNew.getInstance();
        fragmentRandom = FragmentRandom.getInstance();
        fragmentSetting = FragmentSetting.getInstance();
        ArrayList<String> titleList = new ArrayList<>(3);
        titleList.add("最新");
        titleList.add("随机");
        titleList.add("设置");
        ArrayList<Fragment> fragmentList = new ArrayList<>(3);
        fragmentList.add(fragmentNew);
        fragmentList.add(fragmentRandom);
        fragmentList.add(fragmentSetting);
        adapter = new MainPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - lastPressBackTime <= PRESS_EXIT_INTERVAL){
            super.onBackPressed();
        }else {
            ToastUtil.show(this,"再按一次退出");
            lastPressBackTime = System.currentTimeMillis();
        }
    }
}
