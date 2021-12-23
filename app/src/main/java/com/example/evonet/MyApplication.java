package com.example.evonet;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {
    protected static final String APPID = "8acc38da08a5413e0a41e6ba6883e40f";

    @Override
    public void onCreate() {
        super.onCreate();
        //Bmob.resetDomain("https://open3.bmob.cn/8");
        Bmob.initialize(MyApplication.this,APPID);
    }
}
