package com.example.evonet.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.evonet.R;
import com.example.evonet.javaBeans.User;

import cn.bmob.v3.BmobUser;

public class MessageActivity extends BaseActivities implements ActivityInterface{

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_message);
        bindView();
    }
    @Override

    public void bindView() {

    }
}
