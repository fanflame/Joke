package com.ran.joke.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ran.joke.R;

public class MainActivity extends ActivityBase {

    public static void startActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
