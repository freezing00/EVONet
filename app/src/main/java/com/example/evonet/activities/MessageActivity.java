package com.example.evonet.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.evonet.R;

public class MessageActivity extends BaseActivity implements ActivityInterface{

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
