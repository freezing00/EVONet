package com.example.evonet.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.evonet.MyApplication;
import com.example.evonet.R;
import com.example.evonet.javaBeans.User;

import java.util.Objects;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivities implements ActivityInterface{

    private Toolbar toolbar;
    private EditText et_registerUser;
    private EditText et_registerPassword;
    private EditText et_surePassword;
    private EditText et_registerEmail;
    private EditText et_registerPhoneNumber;
    private EditText et_registerIdentifyNumber;
    private Button bt_registerSave,bt_registerCancel,bt_registerIdentify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.resetDomain("https://open3.bmob.cn/8");
        Bmob.initialize(RegisterActivity.this,"8acc38da08a5413e0a41e6ba6883e40f");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        bindView();

        iniToolbar();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.bt_regist_save:
                String user_num = et_registerUser.getText().toString();
                String user_email = et_registerEmail.getText().toString().trim();
                String user_phoneNumber = et_registerPhoneNumber.getText().toString().trim();
                String user_password = et_registerPassword.getText().toString().trim();
                String user_identify = et_registerIdentifyNumber.getText().toString().trim();
                // 非空验证
                if (user_num.isEmpty() || user_password.isEmpty() || user_email.isEmpty() ||  user_phoneNumber.isEmpty()  ||  user_identify.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "信息必须填写完整", Toast.LENGTH_SHORT).show();
                    return;
                }

                //TODO bmob
                BmobUser user = new BmobUser();
                user.setUsername(user_num);
                user.setPassword(user_password);
                user.setEmail(user_email);
                user.setMobilePhoneNumber(user_phoneNumber);
                user.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }else{
                            //loge(e);
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                finish();
                break;
            case R.id.bt_regist_cancel:
                finish();
                break;
            default:
                break;
        }
    }

    private void iniToolbar(){
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//返回
            }
        });
    }


    @Override
    public void bindView() {
        et_registerEmail = findViewById(R.id.email);
        et_registerIdentifyNumber  = findViewById(R.id.et_identify);
        et_registerPassword = findViewById(R.id.et_pwd);
        et_registerPhoneNumber = findViewById(R.id.phoneNumber);
        et_registerUser = findViewById(R.id.user);
        et_surePassword = findViewById(R.id.sure_pwd);
        bt_registerIdentify = findViewById(R.id.bt_register_identify);
        bt_registerCancel = findViewById(R.id.bt_regist_cancel);
        bt_registerSave = findViewById(R.id.bt_regist_save);

        toolbar=findViewById(R.id.tool_bar);


        bt_registerSave.setOnClickListener(RegisterActivity.this);
        bt_registerCancel.setOnClickListener(RegisterActivity.this);
        bt_registerIdentify.setOnClickListener(RegisterActivity.this);

    }
}
