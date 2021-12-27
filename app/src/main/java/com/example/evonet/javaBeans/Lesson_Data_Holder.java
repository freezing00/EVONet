package com.example.evonet.javaBeans;

public class Lesson_Data_Holder {
    private String name;
    private  String number;
    private String sum;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    private static final Lesson_Data_Holder lesson_data_holder = new Lesson_Data_Holder();
    public static Lesson_Data_Holder getInstance() {return lesson_data_holder;}
}
