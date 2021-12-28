package com.example.evonet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.evonet.R;
import com.example.evonet.activities.CreateLessonActivity;
import com.example.evonet.activities.TeachLessonsActivity;

import org.jetbrains.annotations.NotNull;

public class fragment_call extends Fragment {
    Bundle bundle= new Bundle();
    private View view;
    private Button teacher_call_start;
    private Button teacher_crate_lesson;
    public fragment_call(){}
    //构造函数
    public static fragment_call newInstance(){
        return new fragment_call();
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.call_fragment,null);
        teacher_call_start=view.findViewById(R.id.teacher_call_start);
        teacher_crate_lesson=view.findViewById(R.id.teacher_crate_lesson);
        //发布签到
        teacher_call_start.setOnClickListener(v -> {

            Intent intent_sign = new Intent(getActivity(), TeachLessonsActivity.class);
            startActivity(intent_sign);
        });
        //创建课程
        teacher_crate_lesson.setOnClickListener(v -> {
            Intent intent_create = new Intent(getActivity(), CreateLessonActivity.class);
            startActivity(intent_create);
        });
        return view;

    }

}
