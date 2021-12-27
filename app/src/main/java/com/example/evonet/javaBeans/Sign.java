package com.example.evonet.javaBeans;

import cn.bmob.v3.BmobObject;

public class Sign extends BmobObject {
    private String time;//签到发起时间
    private String course;//课程
    private String realSignNumber;//实际签到数目
    private String signType;//签到种类
    private String signId;//签到码

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getRealSignNumber() {
        return realSignNumber;
    }

    public void setRealSignNumber(String realSignNumber) {
        this.realSignNumber = realSignNumber;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
