package com.example.evonet.activities;


import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.evonet.R;

import android.widget.RelativeLayout;
import android.widget.TextView;


import org.jetbrains.annotations.NotNull;

//创建继承自activity的activity
//重写需要的回调方法
//设置需要显示的视图 setContentView(R.layout.activity_main)
public class MainActivity extends BaseActivities implements ActivityInterface {
    private RelativeLayout layout1, layout2, layout3;//对应3个课程布局的点击事件
    private ImageView functionBar, add;//对应侧拉工具栏，加入课程

    //侧划栏
    private DrawerLayout drawer_layout;
    private ImageView userImage,userIdCode;
    private Bitmap bitmap;
    private TextView userId,userName;
    private RelativeLayout accountManager,message,major,courseTable,setting,for_us;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if((Integer)msg.obj==0){
                userImage.setImageBitmap(bitmap);
            }
//            if (msg.what == 0x0){
//                ((Personal)mPerson).getmTips().setText("");
//            }
        }
    };


    @Override
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

        layout1.setOnClickListener(MainActivity.this);
        layout2.setOnClickListener(MainActivity.this);
        layout3.setOnClickListener(MainActivity.this);
        functionBar.setOnClickListener(MainActivity.this);
        add.setOnClickListener(MainActivity.this);

    }



//    /*
//    *侧滑栏用户个人信息设置
//    * */
//    private void bindSideView(){
//        userImage = findViewById(R.id.userImage);
//        userName =findViewById(R.id.info_name);
//        userId = findViewById(R.id.info_id);
//        userIdCode = findViewById(R.id.id_code);
//        accountManager = findViewById(R.id.layout_account_manager);
//        message =findViewById(R.id.layout_message);
//        major = findViewById(R.id.layout_major);
//        courseTable = findViewById(R.id.layout_course_table);
//        for_us = findViewById(R.id.layout_for_us);
//        setting = findViewById(R.id.layout_setting);
//
//        userIdCode.setOnClickListener(this);
//        accountManager.setOnClickListener(this);
//        message.setOnClickListener(this);
//        major.setOnClickListener(this);
//        courseTable.setOnClickListener(this);
//        for_us.setOnClickListener(this);
//        setting.setOnClickListener(this);
//
//
//
//        //drawer_layout.closeDrawer(GravityCompat.START);
//
//    }
}
