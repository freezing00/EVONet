package com.example.evonet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.evonet.R;
import com.example.evonet.utiles.FileManager;


public class StartActivity extends BaseActivities implements ActivityInterface{
    //定义组件对象
    private EditText et_startUser,et_startPassword;
    private TextView tv_register;
    private Button bt_login;
    private CheckBox ck_remember;
    private Boolean isClickLogin = false;


    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);

        bindView();

        if (!FileManager.getAccount(this).equals("")){
            ck_remember.setChecked(true);
            ck_remember.setSelected(true);
            et_startUser.setText(FileManager.getAccount(this));
            et_startPassword.setText(FileManager.getPassword(this));
        }

        //TODO Bmob

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.register:
                Intent intentRegister = new Intent();// TODO 跳转到注册界面,需要建立注册界面
                startActivity(intentRegister);
                break;
            case R.id.log_in_button:
                if(!isClickLogin){//避免重复点击
                    logIn();
                }
                break;
            case R.id.remember:
                if(ck_remember.isSelected()){
                    ck_remember.setSelected(false);
                    ck_remember.setChecked(false);
                }
                else{
                    ck_remember.setSelected(true);
                    ck_remember.setChecked(true);
                }
                break;
        }
    }

    private void logIn() {
        String userName = et_startUser.getText().toString();
        String userPassword = et_startPassword.getText().toString().trim();

        //不能为空
        if(userName.isEmpty()||userPassword.isEmpty()){
            Toast.makeText(StartActivity.this,"账号与密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO 登录
        isClickLogin = true;
        ck_remember.setClickable(false);
    }

    @Override
    public void bindView() {
        tv_register = findViewById(R.id.register);
        et_startUser = findViewById(R.id.user);
        et_startPassword = findViewById(R.id.et_pwd);
        bt_login = findViewById(R.id.log_in_button);
        ck_remember = findViewById(R.id.remember);

        tv_register.setOnClickListener(StartActivity.this);
        bt_login.setOnClickListener(StartActivity.this);
        ck_remember.setOnClickListener(this);
    }
}