package com.example.evonet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentHostCallback;

import com.example.evonet.R;

import cn.bmob.v3.BmobUser;

public class AccountActivity extends BaseActivities implements ActivityInterface{
    private Toolbar toolbar;

    private RelativeLayout email,phoneNumber,password;
    FragmentHostCallback mHost;
    private Button quitButton;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_account);

        bindView();
    }

    @Override
    public void bindView() {
        email = findViewById(R.id.account_email);
        phoneNumber =  findViewById(R.id.account_phone_number);
        quitButton = findViewById(R.id.quit_button);
        password = findViewById(R.id.account_password);

        email.setOnClickListener(AccountActivity.this);
        phoneNumber.setOnClickListener(AccountActivity.this);
        password.setOnClickListener(AccountActivity.this);
        quitButton.setOnClickListener(AccountActivity.this);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        Intent intent = new Intent(AccountActivity.this, ChangeInfoActivity.class);
        switch (view.getId()){
            case R.id.account_email:
                intent.putExtra("function","email");
                startActivityForResult(intent,0);
                break;
            case R.id.account_phone_number:
                intent.putExtra("function","phoneNumber");
                startActivityForResult(intent,0);
                break;
            case R.id.account_password:
                intent.putExtra("function","password");
                startActivityForResult(intent,0);
            case R.id.quit_button:
                BmobUser.logOut();
                startActivity(new Intent(this, StartActivity.class));
                finish();
        }
    }



}
