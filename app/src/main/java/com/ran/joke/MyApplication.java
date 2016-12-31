package com.ran.joke;

import android.app.Application;
import android.content.Context;

/**
 * Created by fanyiran on 16/12/31.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus eventBus = EventBus.builder().addIndex(new MyEventBusIndex()).build();
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        instance = this;
    }

    public static Context getContext(){
        return instance;
    }

}
