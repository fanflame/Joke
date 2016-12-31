package com.ran.joke.activity;

import android.support.v4.app.FragmentActivity;

import com.ran.joke.R;
import com.ran.joke.utils.ToastUtil;

/**
 * Created by fanyiran on 16/12/31.
 */

public class ActivityBase extends FragmentActivity {
    @Override
    protected void onStop() {
        super.onStop();
        ToastUtil.cancel();
    }
    @Override
    public void finish() {
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        super.finish();
    }
}
