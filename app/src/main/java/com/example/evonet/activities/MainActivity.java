package com.example.evonet.activities;


import androidx.annotation.NonNull;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.constraintlayout.solver.state.helpers.BarrierReference;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.evonet.R;
import com.example.evonet.fragment.fragment_call;
import com.example.evonet.fragment.fragment_home;
import com.example.evonet.fragment.fragment_signin;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.widget.RelativeLayout;
import android.widget.TextView;


import org.jetbrains.annotations.NotNull;
public class MainActivity extends BaseActivities {
    //百度地图API调用
    private final int GPS_REQUEST_CODE = 10;
    private final int REQUEST_CODE_FINE_GPS = 2;

    private DrawerLayout mDrawerLayout;

    //    private Bitmap bitmap;//从相册获得图片
    private BottomNavigationView navigation;
    private FragmentManager fragmentManager;
    private String path ;//图片路径
    private Fragment mContent = new Fragment();// 记录下当前碎片 由于替换
    //  切换碎片
    private Fragment call_fragment;
    private Fragment sign_fragment;
    private Fragment home_fragment;

    private FragmentTransaction transaction;

    //侧划栏
    private ImageView userImage,userIdCode;
    private Bitmap bitmap;
    private TextView userId,userName;
    private RelativeLayout accountManager,message,major,courseTable,setting,for_us;

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
                case R.id.navigation_home:
                    switchContent(home_fragment);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

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
    }
    private void iniSideView(){
        mDrawerLayout  = findViewById(R.id.drawer);
        //侧滑栏布局绑定
        accountManager = findViewById(R.id.layout_account_manager);
        message = findViewById(R.id.layout_message);
        courseTable = findViewById(R.id.layout_course_table);
        major = findViewById(R.id.layout_major);
        setting = findViewById(R.id.layout_setting);
        for_us =findViewById(R.id.layout_for_us);
        //侧滑栏头部
        userImage = findViewById(R.id.userImage);
        userId = findViewById(R.id.info_id);
        userName = findViewById(R.id.info_name);

        iniFragment();
    }
    //初始化碎片

    private void iniFragment(){
        sign_fragment = new fragment_signin();
        call_fragment  =new fragment_call();
        home_fragment =new fragment_home();

        fragmentManager = getSupportFragmentManager();
        mContent=home_fragment;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment, mContent).commit();
        navigation.setSelectedItemId(R.id.navigation_home);
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
        switch (view.getId()){
            case R.id.userImage:
                changeHeadImage();
                break;
            case R.id.info_id:
                changeId();
                break;
            case R.id.info_name:
                changeUserName();
                break;
            case R.id.layout_account_manager:
                accountManager.setBackgroundResource(R.color.gray);
                Intent intent = new Intent(MainActivity.this,AccountActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
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

































