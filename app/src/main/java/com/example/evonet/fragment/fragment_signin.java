package com.example.evonet.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.evonet.R;

import org.jetbrains.annotations.NotNull;

public class fragment_signin extends Fragment {
    Bundle bundle= new Bundle();
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
        return view;

    }
}