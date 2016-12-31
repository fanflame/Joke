package com.ran.joke;

import android.app.Application;

/**
 * Created by fanyiran on 16/12/31.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus eventBus = EventBus.builder().addIndex(new MyEventBusIndex()).build();
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
