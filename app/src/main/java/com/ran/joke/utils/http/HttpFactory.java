package com.ran.joke.utils.http;

import com.ran.joke.bean.JokeResult;

/**
 * Created by fanyiran on 16/12/31.
 */

public abstract class HttpFactory {
    public static HttpFactory getInstance(String key,OnCompleteListener onCompleteListener){
        RetrofitUtil retrofitUtil = new RetrofitUtil(key,onCompleteListener);
        return retrofitUtil;
    }

    public interface OnCompleteListener {
        void onFailure();

        void onSuccess(JokeResult.JokeData pics);
    }

    public abstract void getJoke(int page,int pagesize);
}
