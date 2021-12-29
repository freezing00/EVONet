package com.example.evonet.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.evonet.R;
import com.example.evonet.javaBeans.User;
import com.mob.MobSDK;
import cn.smssdk.EventHandler;

import java.util.Objects;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements ActivityInterface{
    private boolean identifyFlag = false;
    private boolean identifySuccessful =false;
    private Toolbar toolbar;
    private EditText et_registerUser;
    private EditText et_registerPassword;
    private EditText et_surePassword;
    private EditText et_registerEmail;
    private EditText et_registerPhoneNumber;
    private EditText et_registerIdentifyNumber;
    private RadioGroup rb_userType;
    private Button bt_registerSave,bt_registerCancel,bt_registerIdentify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        MobSDK.init(this,"34ff68b377ef3","ad737f7f4acaa34d4d54ace455bc560e");
        MobSDK.submitPolicyGrantResult(true,null);
        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object o) {
                super.afterEvent(i, i1, o);
                Message message = new Message();
                message.arg1 = i;
                message.arg2 = i1;
                message.obj = o;
                handler.sendMessage(message);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);
        bindView();

        iniToolbar();
    }



    @Override
    public void onClick(View view) {
        super.onClick(view);

        switch (view.getId()){
            case R.id.bt_register_identify:
                String phoneNumber = null;
                if (!TextUtils.isEmpty(et_registerPhoneNumber.getText())) {
                    if (et_registerPhoneNumber.getText().toString().length() == 11) {
                        phoneNumber = et_registerPhoneNumber.getText().toString();
                        SMSSDK.getVerificationCode("86", phoneNumber); // 发送验证码给号码的 phoneNumber 的手机
                        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                        et_registerIdentifyNumber.requestFocus();
                    } else {
                        Toast.makeText(this, "请输入完整的电话号码", Toast.LENGTH_SHORT).show();
                        et_registerPhoneNumber.requestFocus();
                    }
                } else {
                    Toast.makeText(this, "请输入电话号码", Toast.LENGTH_SHORT).show();
                    et_registerPhoneNumber.requestFocus();
                }
                break;
            case R.id.bt_regist_save:
                String user_type = null;
                int typeStatus = rb_userType.getCheckedRadioButtonId();
                switch (typeStatus){
                    case R.id.user_student:
                        user_type= "学生";
                        break;
                    case R.id.user_teacher:
                        user_type = "老师";
                        break;
                    default:
                        break;
                }

                String user_num = et_registerUser.getText().toString();
                String user_email = et_registerEmail.getText().toString().trim();
                String user_phoneNumber = et_registerPhoneNumber.getText().toString().trim();
                String user_password = et_registerPassword.getText().toString().trim();
                String user_identify = et_registerIdentifyNumber.getText().toString().trim();
                // 非空验证
                if (user_num.isEmpty() || user_password.isEmpty() || user_email.isEmpty() ||  user_phoneNumber.isEmpty()  ||  user_identify.isEmpty()||user_type.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "信息必须填写完整", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (et_registerIdentifyNumber.getText().length() == 4) {
                   // SMSSDK.submitVerificationCode("86", user_phoneNumber, user_identify);
                    Toast.makeText(this, "验证中。。。", Toast.LENGTH_SHORT).show();
                    identifyFlag = false;
                } else {
                    Toast.makeText(this, "请输入完整的验证码", Toast.LENGTH_SHORT).show();
                    et_registerIdentifyNumber.requestFocus();
                }
                //TODO 验证码获取失败，审核问题
//                if (!identifySuccessful){
//                    Toast.makeText(RegisterActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                User user = new User();
                user.setUsername(user_num);
                user.setPassword(user_password);
                user.setPass(user_password);
                user.setEmail(user_email);
                user.setMobilePhoneNumber(user_phoneNumber);
                user.setUserType(user_type);
                user.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            //loge(e);
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
        rb_userType = findViewById(R.id.user_type);
        toolbar=findViewById(R.id.tool_bar);


        bt_registerSave.setOnClickListener(RegisterActivity.this);
        bt_registerCancel.setOnClickListener(RegisterActivity.this);
        bt_registerIdentify.setOnClickListener(RegisterActivity.this);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;

            if (result == SMSSDK.RESULT_COMPLETE) {
                // 如果操作成功
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    // 校验验证码，返回校验的手机和国家代码
                    Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                    identifySuccessful = true;
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    // 获取验证码成功，true为智能验证，false为普通下发短信
                    Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    // 返回支持发送验证码的国家列表
                    Toast.makeText(RegisterActivity.this, "国家列表", Toast.LENGTH_SHORT).show();
                }
            } else {
                // 如果操作失败
                if (identifyFlag) {
                    Toast.makeText(RegisterActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    et_registerIdentifyNumber.requestFocus();
                } else {
                    ((Throwable) data).printStackTrace();
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();  // 注销回调接口
    }

}
