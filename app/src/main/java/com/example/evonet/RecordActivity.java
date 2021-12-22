package com.example.evonet;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.evonet.R;
import com.example.evonet.activities.MainActivity;
import com.example.evonet.activities.SignIn;


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
                Intent intent = new Intent(RecordActivity.this, SignIn.class);
                startActivity(intent);
            }
        });

    }

}