package com.example.evonet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evonet.R;
import com.example.evonet.javaBeans.Lesson_Data_Holder;

public class Start_sign_Activity extends AppCompatActivity {
    private Button pause;
    private Button end;
    private ImageView back;
    private TextView show_name,show_number,show_sum;//显示课程名，课程号，课程总人数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_sign);
        show_name=(TextView)findViewById(R.id.sign_lesson_name);
        show_number=(TextView)findViewById(R.id.sign_lesson_number);
        show_sum=(TextView)findViewById(R.id.sign_lesson_sum);
        pause=(Button)findViewById(R.id.pause);
        end=(Button)findViewById(R.id.end);
        back=(ImageView)findViewById(R.id.back_sign);

        String name= Lesson_Data_Holder.get().getName();
        String number=Lesson_Data_Holder.get().getNumber();
        String sum="应到"+Lesson_Data_Holder.get().getSum()+"人";//从teach_lessons获取数据
        show_name.setText(name);
        show_number.setText(number);
        show_sum.setText(sum);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back=new Intent(Start_sign_Activity.this,teach_lessons.class);
                startActivity(intent_back);//回到列表界面
            }
        });

        //还有按钮的点击事件


    }
}