package com.example.evonet.activities;

import android.os.Bundle;
import android.view.Window;

import com.example.evonet.R;

public class RegisterActivity extends BaseActivities implements ActivityInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
    }

    @Override
    public void bindView() {

    }
}
