package com.example.evonet.javaBeans;
import android.graphics.Bitmap;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
//考勤记录
public class Record extends BmobObject {
    private String time;//签到时间
    private String lesn;//课程号
    private String status;//签到状态（出勤或缺勤）

    public String getTime(){
        return time;
    }
    public String getLesn(){
        return lesn;
    }
    public String getStatus(){
        return status;
    }
    public void setTime(String time){
        this.time=time;
    }
    public void setLesn(String lesn){
        this.lesn=lesn;
    }
    public void setStatus(String status){
        this.status=status;
    }

    public Record() {
    }
}
