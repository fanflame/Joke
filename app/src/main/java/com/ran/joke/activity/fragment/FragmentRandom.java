package com.ran.joke.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ran.joke.R;

import butterknife.ButterKnife;

/**
 * Created by fanyiran on 16/12/31.
 */

public class FragmentRandom extends Fragment {

    public static FragmentRandom getInstance(){
        FragmentRandom fragmentRandom = new FragmentRandom();
        return fragmentRandom;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_random,container,false);
        ButterKnife.bind(this,root);
        return root;
    }
}
