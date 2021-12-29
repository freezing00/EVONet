package com.example.evonet.javaBeans;

import android.graphics.Bitmap;

import java.sql.SQLTransactionRollbackException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class User extends BmobUser {
    /*
    * 数据成员
    * */
    private String id;//学号
    private String pass;//密码
    private String realEmail;//邮箱
    private String mobilePhone;//电话
    private String nickName;//昵称
    private String userType;//用户身份
    private BmobFile imageFile;//头像
    private Bitmap icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRealEmail() {
        return realEmail;
    }

    public void setRealEmail(String realEmail) {
        this.realEmail = realEmail;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
