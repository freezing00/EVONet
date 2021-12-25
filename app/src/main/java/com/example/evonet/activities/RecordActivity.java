package com.example.evonet.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evonet.R;
import com.example.evonet.javaBeans.DataHolder;
import com.example.evonet.javaBeans.Record;


public class RecordActivity extends AppCompatActivity {
    private ImageView back;//跳转到考勤界面
    private TextView show_time,show_status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        show_status=findViewById(R.id.show_status);
        show_time=findViewById(R.id.show_time);
        String time= DataHolder.getInstance().getLoc_time();
        String status=DataHolder.getInstance().getStatus();//从SignActivity中获取数据
        show_time.setText(time);
        show_status.setText(status);
//        Record record=new Record();//新增一条考勤记录


        back=(ImageView)findViewById(R.id.back);
        //点击返回-跳转到主界面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent = new Intent(RecordActivity.this, SignActivity.class);
//                startActivity(intent);
            }
        });

    }

}