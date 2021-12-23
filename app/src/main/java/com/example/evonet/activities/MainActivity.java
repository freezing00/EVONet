package com.example.evonet.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.evonet.R;

import com.example.evonet.fragments.InformationFragment;

import android.widget.RelativeLayout;



import org.jetbrains.annotations.NotNull;

//创建继承自activity的activity
//重写需要的回调方法
//设置需要显示的视图 setContentView(R.layout.activity_main)
public class MainActivity extends BaseActivities implements ActivityInterface {
    private RelativeLayout layout1, layout2, layout3;//对应3个课程布局的点击事件
    private ImageView functionBar, add;//对应侧拉工具栏，加入课程

    //侧划栏
    private DrawerLayout drawer_layout;
    private Fragment fg_information;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);//TODO 存在BUG
        fragmentManager = getSupportFragmentManager();
        fg_information = fragmentManager.findFragmentById(R.id.fg_information);
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
//                String[] add_lesson=new String[]{"扫一扫","加入课堂"};
//                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,add_lesson);
//                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//                Spinner spinner=(Spinner) findViewById(R.id.)
//
//
//                break;
        }
    }

    //绑定组件对应的ID
    @SuppressLint("WrongConstant")
    @Override
    public void bindView() {
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        add = findViewById(R.id.add_lesson);
        functionBar = findViewById(R.id.main_healine);
        //侧划栏
        drawer_layout = findViewById(R.id.drawer_layout);
        drawer_layout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull @NotNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull @NotNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull @NotNull View drawerView) {
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.END);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });




        layout1.setOnClickListener(MainActivity.this);
        layout2.setOnClickListener(MainActivity.this);
        layout3.setOnClickListener(MainActivity.this);
        functionBar.setOnClickListener(MainActivity.this);
        add.setOnClickListener(MainActivity.this);



    }
}
