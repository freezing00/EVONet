package com.example.evonet.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.evonet.R;
import com.example.evonet.activities.SplashActivity;
import com.example.evonet.activities.StartActivity;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public class fragment_home_student extends Fragment {
    private Toolbar toolbar = null;//
    private TextView mTips;
    private Handler handler;

    Bundle bundle= new Bundle();
    //    private View view;
    public fragment_home_student(){}

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//显示菜单栏
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        setHasOptionsMenu(true);

        initToolbar(toolbar, "==", false);
        View view = inflater.inflate(R.layout.student_home_fragment,container,false);
        iniViews(view);
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_popmenu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            //4.0以上不显示icon，未解决（用反射）
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    //还要添加扫二维码和添加课程号事件，这里只做实验
                    case R.id.scan:
//                BmobUser.logOut();
                        startActivity(new Intent(getActivity(), StartActivity.class));
                        getActivity().finish();
                        break;
                    case R.id.add_lesson:
//                BmobUser.logOut();
                        startActivity(new Intent(getActivity(), SplashActivity.class));
                        getActivity().finish();
                        break;

                    default:
                        break;

                }
                return true;
            }
        });
        return view;
    }
    private void iniViews(View view){
        mTips = view.findViewById(R.id.person_tips);

        handler = new Handler();

    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_popmenu,menu);
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
    }

    /**
     * Fragment中初始化Toolbar
     * @param toolbar
     * @param title 标题
     * @param isDisplayHomeAsUp 是否显示返回箭头
     */
    public void initToolbar(Toolbar toolbar, String title, boolean isDisplayHomeAsUp) {
        AppCompatActivity appCompatActivity= (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(isDisplayHomeAsUp);
        }

    }


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    ////重写选项菜单
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.toolbar_popmenu,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }
//选项菜单的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            //还要添加扫二维码和添加课程号事件，这里只做实验
            case R.id.scan:
//                BmobUser.logOut();
                startActivity(new Intent(getActivity(), StartActivity.class));
                getActivity().finish();
                break;
            case R.id.add_lesson:
//                BmobUser.logOut();
                startActivity(new Intent(getActivity(), SplashActivity.class));
                getActivity().finish();
                break;

            default:
                break;

        }
        return true;
    }

}
