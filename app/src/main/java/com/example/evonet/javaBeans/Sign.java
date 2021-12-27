package com.example.evonet.javaBeans;

import cn.bmob.v3.BmobObject;

public class Sign extends BmobObject {
    private String time;//签到发起时间
    private String courseNumber;//课程号
    private int realSignNumber;//实际签到数目
    private String signType;//签到种类
    private String signId;//签到码
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getRealSignNumber() {
        return realSignNumber;
    }

    public void setRealSignNumber(int realSignNumber) {
        this.realSignNumber = realSignNumber;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
