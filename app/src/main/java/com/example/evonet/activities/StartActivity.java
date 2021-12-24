package com.example.evonet.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.evonet.R;
import com.example.evonet.javaBeans.User;
import com.example.evonet.utiles.FileManager;

import org.jetbrains.annotations.NotNull;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;


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


        //TODO 自动登录
        if (BmobUser.isLogin()){
            Intent intent = new Intent(StartActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,"欢迎回来",Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.register:
                Intent intentRegister = new Intent(StartActivity.this,RegisterActivity.class);
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

        loginByAccount(userName,userPassword);
        isClickLogin = true;
        ck_remember.setClickable(false);
    }

    private void loginByAccount(String userName, String userPassword) {
        BmobUser.loginByAccount(userName, userPassword, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                    Toast.makeText(StartActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    try {
                        user = BmobUser.getCurrentUser(User.class);
                        FileManager.savePassword(user.getObjectId(),StartActivity.this);
                        if(ck_remember.isSelected()){
                            FileManager.saveAccount(userName,StartActivity.this);
                            FileManager.savePassword(userPassword,StartActivity.this);
                        }
                        else{
                            FileManager.saveAccount("",StartActivity.this);
                            FileManager.savePassword("",StartActivity.this);
                        }
                        finish();
                        Intent intent = new Intent(StartActivity.this,MainActivity.class);
                        startActivity(intent);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(StartActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    isClickLogin = false;
                    ck_remember.setClickable(true);
                }
            }
        });

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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && requestCode == 200) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) { // 用户点的拒绝，仍未拥有权限
                    Toast.makeText(this, "请在设置中打开摄像头或存储权限", Toast.LENGTH_SHORT).show();
                    // 可以选择添加如下代码在系统设置中打开该应用的设置页面
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    return;
                }
            }
        }
    }
}
