package com.example.evonet.javaBeans;
public class Lesson_Number_Holder {
    private String l;
    public String getData() {return l;}
    public void setData(String l) {this.l = l;}
    private static final Lesson_Number_Holder lesson_number_holder = new Lesson_Number_Holder();
    public static Lesson_Number_Holder getInstance() {return lesson_number_holder;}
}