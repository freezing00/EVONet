package com.example.evonet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.evonet.R;

public class ChangeInfoActivity extends AppCompatActivity implements ActivityInterface{
    private Intent intent;
    private EditText editText;
    private Button finishButton;
    private TextView toolBarFunction;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        bindView();

    }

    @Override
    public void bindView() {
        editText = findViewById(R.id.edit);
        finishButton = findViewById(R.id.finish);
        toolBarFunction = findViewById(R.id.tool_bar);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishChange();
            }
        });
        intent =getIntent();
    }

    private void finishChange() {
        switch (intent.getStringExtra("function")){
            case "email":
                intent =new Intent();
                intent.putExtra("email",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
            case "phoneNumber":
                intent =new Intent();
                intent.putExtra("phoneNumber",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
            case "password":
                intent =new Intent();
                intent.putExtra("password",editText.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
            default:
                break;
        }
    }
}
