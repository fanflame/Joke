package com.ran.joke.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by fanyiran on 16/12/31.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> titleList;
    private ArrayList<Fragment> fragmentList;

    public MainPagerAdapter(FragmentManager fragmentManager,ArrayList<Fragment> fragmentList, ArrayList<String> titleList){
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0:fragmentList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if(position < titleList.size())
            return titleList.get(position);
        return super.getPageTitle(position);
    }

    @Override
    public Fragment getItem(int position) {
        if(position < fragmentList.size()){
            return fragmentList.get(position);
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
