package com.example.evonet.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.evonet.R;
import com.example.evonet.activities.ActivityInterface;
import com.example.evonet.activities.BaseActivities;
import com.example.evonet.utiles.FileManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.evonet.R;
import com.example.evonet.utiles.FileManager;

//创建继承自activity的activity
//重写需要的回调方法
//设置需要显示的视图 setContentView(R.layout.activity_main)
public class MainActivity extends BaseActivities implements ActivityInterface {
    private RelativeLayout layout1, layout2, layout3;//对应3个课程布局的点击事件
    private ImageView functionBar, add;//对应侧拉工具栏，加入课程

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bindView();
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            //点击课程事件
            case R.id.layout1:
            case R.id.layout2:
            case R.id.layout3:
                Intent intentlesson = new Intent(MainActivity.this, AddLesson.class);// TODO 跳转到签到界面，activity_add_lesson.xml
                startActivity(intentlesson);
                break;
//            case R.id.add:
//                Intent intent
//                break;
        }
    }

    //绑定组件对应的ID
    public void bindView() {
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        add = findViewById(R.id.add_lesson);
        functionBar = findViewById(R.id.main_healine);

        layout1.setOnClickListener(MainActivity.this);
        layout2.setOnClickListener(MainActivity.this);
        layout3.setOnClickListener(MainActivity.this);
        functionBar.setOnClickListener(MainActivity.this);
        add.setOnClickListener(MainActivity.this);

    }
}
