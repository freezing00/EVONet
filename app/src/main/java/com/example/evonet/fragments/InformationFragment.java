package com.example.evonet.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Fragment;

import com.example.evonet.R;

import org.jetbrains.annotations.NotNull;

@SuppressLint("ValidFragment")
public class InformationFragment extends Fragment{
    private DrawerLayout drawerLayout;
    private String content;

    public InformationFragment(int contentLayoutId, String content) {
        this.content = content;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_information,container,false);
        return view;
    }
}
