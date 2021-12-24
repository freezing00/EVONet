package com.example.evonet;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
//
//public class MyTabListener implements androidx.appcompat.app.ActionBar.TabListener {
//    private final Activity activity;//用于指定要加载的fragment
//    private final Class aClass;//用于指定要加载的fragment所对应的类
//    private Fragment fragment;//定义Fragment对象
//
//
//    //  初始化
//    public MyTabListener(Activity activity, Class aClass) {
//        this.activity = activity;
//        this.aClass = aClass;
//    }
//
//
//    @Override
//    //标签页被选择
//    public void onTabSelected(androidx.appcompat.app.ActionBar.Tab tab, FragmentTransaction ft) {
//        //fragment是否被初始化
//        if (fragment==null) {
//            fragment=Fragment.instantiate(activity,aClass.getName());//创建fragment对象
//            ft.add(R.id.content,fragment,null);
//        }
//        ft.attach(fragment);//显示新页面
//    }
//    //退出选择状态
//    @Override
//    public void onTabUnselected(androidx.appcompat.app.ActionBar.Tab tab, FragmentTransaction ft) {
//        if (fragment!=null) {
//            ft.detach(fragment);//删除旧页面
//        }
//    }
//
//    @Override
//    public void onTabReselected(androidx.appcompat.app.ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }
//}