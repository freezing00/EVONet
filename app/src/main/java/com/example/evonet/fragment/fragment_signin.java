package com.example.evonet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.evonet.R;
import com.example.evonet.activities.IFragmentInterface;
import com.example.evonet.activities.MainActivity;
import com.example.evonet.activities.SignActivity;

import org.jetbrains.annotations.NotNull;

public class fragment_signin extends Fragment {
    Bundle bundle= new Bundle();
    private RelativeLayout layout1,layout2,layout3;//对应3个课程布局的点击事件
    private View view;
    public fragment_signin(){}
    //构造函数
    public static fragment_signin newInstance(){
        return new fragment_signin();
    }
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.siginfragment,null);
        layout1=view.findViewById(R.id.layout1);
        layout2=view.findViewById(R.id.layout2);
        layout3=view.findViewById(R.id.layout3);
        //点击课程列表中一项跳转到SignActivity页面定位签到
//        layout1.setOnClickListener(this);
//        layout2.setOnClickListener(this);
//        layout3.setOnClickListener(this);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
                startActivity(intentlesson);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
                startActivity(intentlesson);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlesson = new Intent(getActivity(), SignActivity.class);
                startActivity(intentlesson);
            }
        });

        return view;

    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.layout1:
//                Bundle bundle=new Bundle();
//                bundle.putString("message","安卓应用开发");
//                fragment_signin sig=new fragment_signin();
//                sig.setArguments(bundle);
//                IFragmentInterface Icall=new IFragmentInterface() {
//                    @Override
//                    public void sendMsgToActivity(String string) {
//
//                    }
//
//                    @Override
//                    public String getMsgFromActivity(String string) {
//                        return null;
//                    }
//                }
//        }
//
//    }
}