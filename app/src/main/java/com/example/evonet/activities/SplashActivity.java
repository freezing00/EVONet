package com.example.evonet.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.evonet.R;

public class SplashActivity extends BaseActivities implements ActivityInterface{
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setMenuBarTransport();

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),StartActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();

    }
    @Override
    public void bindView() {

    }
}
