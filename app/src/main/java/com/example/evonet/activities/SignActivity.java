package com.example.evonet.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.evonet.R;
import com.example.evonet.javaBeans.DataHolder;

import java.text.SimpleDateFormat;
import java.util.Date;

//对应activity_add_lesson.xml中签到打卡和考勤记录两个按钮的点击事件
public class SignActivity extends AppCompatActivity {
    private Button button_kaoqin, button_qiandao;//全局变量
    private ImageView fanhui;//跳转到主界面
    private TextView gps;//显示GPS定位信息
//    private MapView mapView;//初始化全局变量地图组件
//    private Bundle bundle;//传递签到信息给RecordActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //初始化地图SDK
//        SDKInitializer.initialize(getApplicationContext());
//        mapView=findViewById(R.id.mapview);//获取地图组件


        button_kaoqin = (Button) findViewById(R.id.button_kaoqin);
        button_qiandao = (Button) findViewById(R.id.button_qiandao);
        fanhui = (ImageView) findViewById(R.id.back);
        gps = (TextView) findViewById(R.id.show_gps);
        //点击签到事件-这未做GPS
        // （GPS定位，定位准确则变成绿色，显示文本为签到成功；定位错误为签到失败）
//        ,通过Intent借助Bundle和RecordActivity之间传递数据
        //出勤或者缺勤
        button_qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GPS定位
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//                Toast.makeText(SignIn.this,"签到成功！",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(SignActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SignActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        1000,//间隔时间);
                        1,//位置间隔
                        new LocationListener() {//监听GOS信息是否改变
                            @Override
                            public void onLocationChanged(@NonNull Location location) {
                                //GPS信息发生改变时回调

                            }

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {
                                //GPS状态改变回调
                            }

                            @Override
                            public void onProviderEnabled(@NonNull String provider) {
                                //定位提供者启动时回调
                            }

                            @Override
                            public void onProviderDisabled(@NonNull String provider) {
                                //定位提供者关闭时回调
                            }
                        });
                Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//获取最新定位信息
                locationUpdaes(location);//将最新定位信息传递给该方法


            }

        });
        //点击考勤事件-跳转到考勤记录界面
        button_kaoqin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status=button_qiandao.getText().toString();//获取当前签到成功与否的状态
                String time=GetTime();//获取签到时间
                DataHolder.getInstance().setLoc_time(time);
                DataHolder.getInstance().setStatus(status);
                Intent intent=new Intent(SignActivity.this,RecordActivity.class);
                startActivity(intent);
            }
        });
        //点击返回-跳转到主界面
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//关闭活动，回到签到界面
            }
        });
    }

    //获取当前系统运行时间，作为Bundle所要传递的数据传递给Record
    @SuppressLint("SimpleDateFormat")
    public static String GetTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    public void locationUpdaes(Location location){
        System.out.println("进入locationUpdates（）方法");
        if(location!=null)
        {
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("您处于");
            stringBuilder.append("经度：");
            stringBuilder.append(location.getLongitude());
            stringBuilder.append("，纬度：");
            stringBuilder.append(location.getLatitude());
            gps.setText(stringBuilder.toString());
            button_qiandao.setText("签到成功");
            button_qiandao.setBackgroundColor(Color.parseColor("#FF03DAC5"));

        }else {
            gps.setText("您没有获取到定位信息");
            button_qiandao.setText("签到失败");
            button_qiandao.setBackgroundColor(Color.parseColor("#ff6347"));
        }

    }

}