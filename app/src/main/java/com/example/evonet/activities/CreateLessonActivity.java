package com.example.evonet.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.evonet.R;
import com.example.evonet.javaBeans.LessonBean;
import com.example.evonet.javaBeans.LessonsAdapter;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class CreateLessonActivity extends BaseActivities implements ActivityInterface{


    private EditText et_name;
    private EditText et_number;
    private EditText et_sum_person;

    private Button createCourse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lesson);
        bindView();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.create) {
                String name = et_name.getText().toString();
                String number = et_number.getText().toString().trim();
                String sum_person = et_sum_person.getText().toString().trim();

                if (name.isEmpty() || number.isEmpty() || sum_person.isEmpty()) {
                    Toast.makeText(CreateLessonActivity.this, "信息必须填写完整", Toast.LENGTH_SHORT).show();
                    return;
                }

            LessonBean lesson = new LessonBean();
            lesson.setName(name);
            lesson.setNumber(number);
            lesson.setSum_person(sum_person);
            lesson.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(CreateLessonActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CreateLessonActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
            });
        }
    }

    @Override
    public void bindView() {
        et_name = findViewById(R.id.name);
        et_number = findViewById(R.id.number);
        et_sum_person = findViewById(R.id.sum_person);
        createCourse = findViewById(R.id.create);
        createCourse.setOnClickListener(this);

    }
}
