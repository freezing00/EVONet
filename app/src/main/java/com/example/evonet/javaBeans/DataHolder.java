package com.example.evonet.javaBeans;
//在SignActivity和RecordActivity之间传递数据，储存到数据库中
public class DataHolder {
    private String loc_time;
    private String status;
    public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
    public String getLoc_time() {return loc_time;}
    public void setLoc_time(String loc_time) {this.loc_time=loc_time;}
    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}
}