package com.example.evonet.activities;


import androidx.annotation.NonNull;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.evonet.R;
import com.example.evonet.fragment.fragment_call;
import com.example.evonet.fragment.fragment_home;
import com.example.evonet.fragment.fragment_home_student;
import com.example.evonet.fragment.fragment_signin;
import com.example.evonet.javaBeans.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.RelativeLayout;
import android.widget.TextView;



import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivities {
    //百度地图API调用
    private final int GPS_REQUEST_CODE = 10;
    private final int REQUEST_CODE_FINE_GPS = 2;
//    private Menu toolbar_popmenu;

    private DrawerLayout mDrawerLayout;

    //    private Bitmap bitmap;//从相册获得图片
    private BottomNavigationView navigation;//底部导航栏
    private FragmentManager fragmentManager;//碎片管理器
    private String path ;//图片路径
    private Fragment mContent = new Fragment();// 记录下当前碎片 由于替换
    //  切换碎片
    //学生端
    private Fragment sign_fragment;
    private Fragment student_home_fragment;
    //教师端
    private Fragment call_fragment;
    private Fragment teacher_home_fragment;

    private FragmentTransaction transaction;

    private String userType="学生";//老师-教师端，学生-学生端

    //侧划栏
    private ImageView userImage,userIdCode;
    private Bitmap bitmap;
    private TextView userId,userName;
    private RelativeLayout accountManager,message,version,setting,for_us;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_signin:
                    switchContent(sign_fragment);
                    return true;
                case R.id.navigation_call:
                    switchContent(call_fragment);
                    return true;
                case R.id.navigation_home_student:
                    switchContent(student_home_fragment);
                    return true;
                case R.id.navigation_home_teacher:
                    switchContent(teacher_home_fragment);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        User user  = BmobUser.getCurrentUser(User.class);
        userType = user.getUserType();
        userType = user.getUserType();
        switch (userType){
            case "学生":
                setContentView(R.layout.activity_main_student);
                break;
            case "老师":
                setContentView(R.layout.activity_main_teacher);
                break;
            default:
                break;
        }

        //底部选择碎片切换
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //Toolbar代替ActionBar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        user = BmobUser.getCurrentUser(User.class);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_CODE_FINE_GPS);

        iniSideView();
//        int id=getIntent().getIntExtra("id",0);
//        if (id==1)
//        {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragment,new fragment_call())
//                    .addToBackStack(null)
//                    .commit();
//        }
    }
    private void iniSideView(){
        mDrawerLayout  = findViewById(R.id.drawer);
        //侧滑栏布局绑定
        accountManager = findViewById(R.id.layout_account_manager);
        message = findViewById(R.id.layout_message);
        version = findViewById(R.id.layout_version);
        setting = findViewById(R.id.layout_setting);
        for_us =findViewById(R.id.layout_for_us);

        //侧滑栏头部
        userImage = findViewById(R.id.userImage);
        userId = findViewById(R.id.info_id);
        userName = findViewById(R.id.info_name);

        userImage.setOnClickListener(this);
        userName.setOnClickListener(this);

        message.setOnClickListener(this);
        //version.setOnClickListener(this);
        accountManager.setOnClickListener(this);
        for_us.setOnClickListener(this);
        setting.setOnClickListener(this);
//通过用户ID切换不同的主界面
        switch (userType){
            case "老师":
                iniFragment_teacher();
                break;
            case "学生":
                iniFragment_student();
                break;
            default:
                break;
        }

//        iniFragment();
    }
    //初始化碎片
    //初始化学生端主界面
    private void iniFragment_student(){
        sign_fragment = new fragment_signin();
        student_home_fragment =new fragment_home_student();

        fragmentManager = getSupportFragmentManager();
        mContent=student_home_fragment;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment, mContent).commit();
        navigation.setSelectedItemId(R.id.navigation_home_student);
    }
    //初始化教师端主界面
    private void iniFragment_teacher(){
        call_fragment  =new fragment_call();
        teacher_home_fragment =new fragment_home();

        fragmentManager = getSupportFragmentManager();
        mContent=teacher_home_fragment;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment, mContent).commit();
        navigation.setSelectedItemId(R.id.navigation_home_teacher);
    }
    /**
     * 修改显示的内容 不会重新加载
     * to 下一个fragment
     * mContent 当前的fragment
     */
    private void switchContent(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) { // 判断是否被add过
                // 隐藏当前的fragment，将 下一个fragment 添加进去
                transaction.hide(mContent).add(R.id.fragment, to).commit();
            } else {
                // 隐藏当前的fragment，显示下一个fragment
                transaction.hide(mContent).show(to).commit();
            }
            mContent = to;
        }

    }

    @Override
    public void onClick(View view) {
        accountManager.setBackgroundResource(R.color.white);
        message.setBackgroundResource(R.color.white);
        version.setBackgroundResource(R.color.white);
        for_us.setBackgroundResource(R.color.white);
        setting.setBackgroundResource(R.color.white);
        switch (view.getId()){
            case R.id.userImage:
                changeHeadImage();
                break;
            case R.id.info_name:
                Intent thisIntent = new Intent(MainActivity.this, ChangeInfoActivity.class);
                thisIntent.putExtra("function","name");
                startActivityForResult(thisIntent,0);
                break;
            case R.id.layout_account_manager:
                accountManager.setBackgroundResource(R.color.gray);
                Intent intent = new Intent(MainActivity.this,AccountActivity.class);
                startActivity(intent);
                break;
            case R.id.layout_message:
                message.setBackgroundResource(R.color.gray);
                Intent messageIntent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(messageIntent);
                break;
            case R.id.layout_version:
                version.setBackgroundResource(R.color.gray);
                Intent versionIntent = new Intent(MainActivity.this, VersionControlActivity.class);
                startActivity(versionIntent);
                break;
            case R.id.layout_for_us:
                for_us.setBackgroundResource(R.color.gray);
                Intent forUsIntent = new Intent(MainActivity.this, ForUsActivity.class);
                startActivity(forUsIntent);
                break;
            case R.id.layout_setting:
                setting.setBackgroundResource(R.color.gray);
                Intent settingIntent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(settingIntent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            if (resultCode == RESULT_OK){
                assert data != null;
                userName.setText(data.getStringExtra("name"));
            }
        }
    }

    private void changeName(String name) {
    }

    private void changeUserName() {
    }

    private void changeId() {

    }


    private void changeHeadImage() {
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}

































