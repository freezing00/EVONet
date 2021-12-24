package com.example.evonet.activities;

import android.Manifest;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.evonet.R;
import com.example.evonet.fragment.fragment_home;
import com.example.evonet.fragment.fragment_signin;
import com.example.evonet.fragment.fragment_call;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.reactivex.annotations.NonNull;

public class MainActivity extends BaseActivities {
    //百度地图API调用
    private final int GPS_REQUEST_CODE = 10;
    private final int REQUEST_CODE_FINE_GPS = 2;

    private DrawerLayout mDrawerLayout;
//    private TextView mUserName;
//    private TextView mUserMotto;
//    private LinearLayout mArticleLayout;
//    private LinearLayout mVersionLayout;
//    private LinearLayout mReviewLayout;

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

}


































////创建继承自activity的activity
////重写需要的回调方法
////设置需要显示的视图 setContentView(R.layout.activity_main)
//public class MainActivity extends BaseActivities implements ActivityInterface {
//    private RelativeLayout layout1, layout2, layout3;//对应3个课程布局的点击事件
//    private ImageView functionBar, add;//对应侧拉工具栏，加入课程
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_main);
//        fragment_signin frag_sigin=new fragment_signin();
//        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
//        ft.add(R.id.,frag_sigin);
//        ft.commit();
//        bindView();
//    }
//    //解析菜单资源文件
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();//实例化一个MenuInflater
//        inflater.inflate(R.menu.menu,menu);//解析菜单文件
//
//
//        return super.onCreateOptionsMenu(menu);
//    }
//
////    public void onClick(View view) {
////        super.onClick(view);
////        switch (view.getId()) {
////            //点击课程事件
////            case R.id.layout1:
////            case R.id.layout2:
////            case R.id.layout3:
////                Intent intentlesson = new Intent(MainActivity.this, SignIn.class);// TODO 跳转到签到界面，activity_add_lesson.xml
////                startActivity(intentlesson);
////                break;
//////            case R.id.add:
//////                String[] add_lesson=new String[]{"扫一扫","加入课堂"};
//////                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,add_lesson);
//////                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//////                Spinner spinner=(Spinner) findViewById(R.id.)
//////
//////
//////                break;
////        }
////    }
//
//    //绑定组件对应的ID
////    @Override
////    public void bindView() {
////        layout1 = findViewById(R.id.layout1);
////        layout2 = findViewById(R.id.layout2);
////        layout3 = findViewById(R.id.layout3);
//////        add = findViewById(R.id.add_lesson);
//////        functionBar = findViewById(R.id.main_healine);
////
////        layout1.setOnClickListener(MainActivity.this);
////        layout2.setOnClickListener(MainActivity.this);
////        layout3.setOnClickListener(MainActivity.this);
////        functionBar.setOnClickListener(MainActivity.this);
////        add.setOnClickListener(MainActivity.this);
////
////    }
//}
