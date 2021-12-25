package com.example.evonet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentHostCallback;

import com.example.evonet.R;
import com.example.evonet.javaBeans.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class AccountActivity extends BaseActivities implements ActivityInterface{
    private Toolbar toolbar;

    private RelativeLayout emailLayout,phoneNumberLayout,passwordLayout;
    FragmentHostCallback mHost;
    private Button quitButton;
    private TextView email,phoneNumber,password;

    private User user;//用于更新云数据库

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_account);

        bindView();
    }

    @Override
    public void bindView() {
        emailLayout = findViewById(R.id.account_email);
        phoneNumberLayout =  findViewById(R.id.account_phone_number);
        quitButton = findViewById(R.id.quit_button);
        passwordLayout = findViewById(R.id.account_password);
        email = findViewById(R.id.tv_email);
        phoneNumber = findViewById(R.id.tv_phoneNumber);
        password = findViewById(R.id.tv_password);

        emailLayout.setOnClickListener(AccountActivity.this);
        phoneNumberLayout.setOnClickListener(AccountActivity.this);
        passwordLayout.setOnClickListener(AccountActivity.this);
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
                startActivityForResult(intent,1);
                break;
            case R.id.account_password:
                intent.putExtra("function","password");
                startActivityForResult(intent,2);
            case R.id.quit_button:
                BmobUser.logOut();
                startActivity(new Intent(this, StartActivity.class));
                finish();
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if (resultCode == RESULT_OK){
                    assert data != null;
                    changeEmail(data.getStringExtra("email"));
                }
            case 1:
                if (resultCode == RESULT_OK){
                    assert data != null;
                    changePhoneNumber(data.getStringExtra("phoneNumber"));
                }
            case 2:
                if (resultCode == RESULT_OK){
                    assert data != null;
                    changePassword(data.getStringExtra("password"));
                }
            default:
                break;
        }
    }

    private void changePassword(String updatePassword) {
        user.setPassword(updatePassword);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(AccountActivity.this, "用户名更新成功", Toast.LENGTH_SHORT).show();
                    password.setText(updatePassword);
                } else {
                    Toast.makeText(AccountActivity.this, "用户名更新失败，请检查网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changePhoneNumber(String updatePhoneNumber) {
        user.setMobilePhoneNumber(updatePhoneNumber);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(AccountActivity.this, "用户名更新成功", Toast.LENGTH_SHORT).show();
                    phoneNumber.setText(updatePhoneNumber);
                } else {
                    Toast.makeText(AccountActivity.this, "用户名更新失败，请检查网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void changeEmail(String updateEmail) {
        user.setEmail(updateEmail);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(AccountActivity.this, "用户名更新成功", Toast.LENGTH_SHORT).show();
                    email.setText(updateEmail);
                } else {
                    Toast.makeText(AccountActivity.this, "用户名更新失败，请检查网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
