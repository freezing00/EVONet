package com.example.evonet.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evonet.R;
import com.example.evonet.javaBeans.Lesson_Data_Holder;
import com.example.evonet.javaBeans.Record;
import com.example.evonet.javaBeans.Sign;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class Start_sign_Activity extends BaseActivities implements ActivityInterface {
    private Button start;
    private Button end;
    private ImageView back;
    private Intent intent_back;
    private TextView show_name,show_number,show_sum;//显示课程名，课程号，课程总人数
    private RadioGroup rb_signType;
    private Sign sign = new Sign();
    private String signId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_sign);
        bindView();
        //还有按钮的点击事

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.start:
                int type = rb_signType.getCheckedRadioButtonId();
                switch (type){
                    case R.id.sign_number:
                        sign.setSignType("数字签到");
                        Intent intent = new Intent(Start_sign_Activity.this, ChangeInfoActivity.class);
                        intent.putExtra("function","signId");
                        startActivityForResult(intent,0);
                        break;
                    case R.id.sign_code:
                        sign.setSignType("二维码签到");
                        break;
                    default:
                        break;
                }
            case R.id.end:
                sign.setSignal(false);
                sign.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            Toast.makeText(Start_sign_Activity.this, "结束签到成功", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Start_sign_Activity.this, "结束签到失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if (resultCode==RESULT_OK){
                    assert data != null;
                    sign.setSignId(data.getStringExtra("signId"));
                    String time=getTime();//获取发布签到时间
                    sign.setTime(time);
                    sign.setCourseNumber(Lesson_Data_Holder.getInstance().getNumber());
                    setRealSign();
                    sign.setSignal(true);
                    sign.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                Toast.makeText(Start_sign_Activity.this, "发布签到成功", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Start_sign_Activity.this, "发布签到失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
        }
    }

    private void setRealSign() {
        final int[]sum = {0};
        BmobQuery<Record> recordBmobQuery = new BmobQuery<Record>();
        recordBmobQuery.addWhereEqualTo("lesn",sign.getCourseNumber());
        recordBmobQuery.findObjects(new FindListener<Record>() {
            @Override
            public void done(List<Record> list, BmobException e) {
                if(e==null){
                    for(Record record:list){
                        if(record.getStatus()==true){
                            sum[0]++;
                        }
                    }
                }
            }
        });
        sign.setRealSignNumber(sum[0]);
    }

    private static String getTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @Override
    public void bindView() {
        rb_signType = findViewById(R.id.sign_type);
        show_name=(TextView)findViewById(R.id.sign_lesson_name);
        show_number=(TextView)findViewById(R.id.sign_lesson_number);
        show_sum=(TextView)findViewById(R.id.sign_lesson_sum);
        start=(Button)findViewById(R.id.start);
        end=(Button)findViewById(R.id.end);
        back=(ImageView)findViewById(R.id.back_sign);

        String name= Lesson_Data_Holder.getInstance().getName();
        String number=Lesson_Data_Holder.getInstance().getNumber();
        String sum="应到"+Lesson_Data_Holder.getInstance().getSum()+"人";//从teach_lessons获取数据
        show_name.setText(name);
        show_number.setText(number);
        show_sum.setText(sum);

        start.setOnClickListener(this);
        end.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}