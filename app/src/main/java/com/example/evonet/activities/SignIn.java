package com.example.evonet.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.evonet.R;

//对应activity_add_lesson.xml中签到打卡和考勤记录两个按钮的点击事件
public class SignIn extends AppCompatActivity {
    private Button button_kaoqin,button_qiandao;//全局变量
    private ImageView fanhui;//跳转到主界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        button_kaoqin=(Button)findViewById(R.id.button_kaoqin);
        button_qiandao=(Button)findViewById(R.id.button_qiandao);
        fanhui=(ImageView)findViewById(R.id.back);
        //点击签到事件-这未做GPS
        // （GPS定位，定位准确则变成绿色，显示文本为签到成功；定位错误为签到失败）
        button_qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(SignIn.this,"签到成功！",Toast.LENGTH_SHORT).show();
                button_qiandao.setText("签到成功");
                button_qiandao.setBackgroundColor(Color.parseColor("#FF03DAC5"));

            }
        });
        //点击考勤事件-跳转到考勤记录界面
        button_kaoqin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignIn.this, "查看考勤记录！", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignIn.this, RecordActivity.class);
                startActivity(intent);
            }
        });
        //点击返回-跳转到主界面
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}