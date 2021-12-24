package com.example.evonet.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.evonet.R;


public class RecordActivity extends AppCompatActivity {
    private ImageView back;//跳转到考勤界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        back=(ImageView)findViewById(R.id.back);
        //点击返回-跳转到主界面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecordActivity.this, SignActivity.class);
                startActivity(intent);
            }
        });

    }

}