package com.ran.joke.utils.http;

import com.ran.joke.bean.JokeResult;
import com.ran.joke.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by fanyiran on 16/12/31.
 */

public class RetrofitUtil extends HttpFactory{
    private OnCompleteListener onCompleteListener;
    private Call<JokeResult> repos;
    private String key;

    public RetrofitUtil(String key,OnCompleteListener onCompleteListener) {
        this.key = key;
        this.onCompleteListener = onCompleteListener;
    }

    @Override
    public void getJoke(int page,int pageSize) {
        execute(key,page,pageSize);
    }



    public interface JokeService {
        @GET(Constant.Url.jokeUrl)
        Call<JokeResult> listRepos(@QueryMap Map<String, String> options);
    }


    public void execute(String key, int page, int pageSize) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.Url.jokeBase)
//				增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Map<String, String> optionsMap = new HashMap<>();
        optionsMap.put("key", key);
        optionsMap.put("page", page+"");
        optionsMap.put("pagesize", pageSize+"");
        JokeService service = retrofit.create(JokeService.class);
        repos = service.listRepos(optionsMap);
        repos.enqueue(new Callback<JokeResult>() {
            @Override
            public void onResponse(Call<JokeResult> call, Response<JokeResult> response) {
                JokeResult jokeResult = response.body();
                if(jokeResult.getError_code() != 0){
                    if (onCompleteListener != null)
                        onCompleteListener.onFailure();
                }else if (onCompleteListener != null)
                    onCompleteListener.onSuccess(jokeResult.getResult());
            }

            @Override
            public void onFailure(Call<JokeResult> call, Throwable t) {
                if (onCompleteListener != null)
                    onCompleteListener.onFailure();
            }
        });
    }

    public void cancleTask() {
        repos.cancel();
    }
}
