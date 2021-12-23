package com.example.evonet.javaBeans;

import android.graphics.Bitmap;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {
    /*
    * 数据成员
    * */

    private String date;//日期
    private String time;//时间
    private String type;//考勤类型
    private String status;//考勤状态
    private BmobFile imageFile;//头像
    private Bitmap icon;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BmobFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(BmobFile imageFile) {
        this.imageFile = imageFile;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
