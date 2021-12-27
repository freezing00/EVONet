package com.example.evonet.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteHelper extends SQLiteOpenHelper {
    private final String TAG = "mySQLiteHelper";
    private static final int VERSION_CODE = 3;
    private static final String DB_NAME = "EVONet.db";
    public static final String TABLE_USER = "User";

    public MySQLiteHelper(Context context) {
        super(context,DB_NAME,null,VERSION_CODE);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String sql = "create table if not exists " + TABLE_USER + " (NickName text, Groups text, Motto text)";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "succeed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_USER;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

}
