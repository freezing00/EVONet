package com.example.evonet.javaBeans;
import android.graphics.Bitmap;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
//考勤记录
public class Record extends BmobObject {
    private String time;//签到时间
    private String lesn;//课程号
    private String signId;//签到码
    private boolean status;//签到状态（出勤或缺勤）
    private boolean signal;//签到信号

    public boolean isSignal() {
        return signal;
    }

    public void setSignal(boolean signal) {
        this.signal = signal;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public boolean isStatus() {
        return status;
    }

    public String getTime(){
        return time;
    }
    public String getLesn(){
        return lesn;
    }
    public boolean getStatus(){
        return status;
    }
    public void setTime(String time){
        this.time=time;
    }
    public void setLesn(String lesn){
        this.lesn=lesn;
    }
    public void setStatus(boolean status){
        this.status=status;
    }

    public Record() {
    }
}
