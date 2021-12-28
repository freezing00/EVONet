package com.example.evonet.javaBeans;

import cn.bmob.v3.BmobObject;

//课程列表的每一个Item
public class Lesson extends BmobObject {
    String name;//课程名
    String number;//课程号
    String sum_person;//课程总人数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getSum_person() {
        return sum_person;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSum_person(String sum_person) {
        this.sum_person = sum_person;
    }
}
