package com.example.evonet.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.evonet.R;
import com.example.evonet.javaBeans.User;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ResetPasswordActivity extends BaseActivity implements ActivityInterface{
    private EditText et_old_pass;
    private EditText et_new_pass;
    private EditText et_sure_pass;
    private User user = null;

    private Button commit;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        user = BmobUser.getCurrentUser(User.class);
        bindView();
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        if(view.getId()==R.id.commit){
            String oldPass = et_old_pass.getText().toString();
            String newPass = et_new_pass.getText().toString();
            String surePass = et_sure_pass.getText().toString();
            // 非空验证
            if (oldPass.isEmpty() || newPass.isEmpty() || surePass.isEmpty() ) {
                Toast.makeText(ResetPasswordActivity.this, "信息必须填写完整", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!oldPass.equals(user.getPass())){
                Toast.makeText(ResetPasswordActivity.this, "原密码错误", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!newPass.equals(surePass)){
                Toast.makeText(ResetPasswordActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            user.setPassword(newPass);
            user.setPass(newPass);
            user.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(ResetPasswordActivity.this, "密码更新成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetPasswordActivity.this, "密码更新失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void bindView() {
        et_old_pass =findViewById(R.id.oldPass);
        et_new_pass = findViewById(R.id.newPass);
        et_sure_pass = findViewById(R.id.surePass);
        commit = findViewById(R.id.commit);

        commit.setOnClickListener(this);
    }
}
