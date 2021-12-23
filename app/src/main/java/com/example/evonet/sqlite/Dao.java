package com.example.evonet.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.evonet.javaBeans.User;

import java.util.ArrayList;
import java.util.List;


public class Dao {
    private final String TAG = "Dao";

    public static final String USER = "User";
    private String[] USER_COLUMNS   = new String[]{"Date","Time","Type","Status"};
    private Context context;
    private MySQLiteHelper sqLiteHelper;

    public Dao(Context context) {
        this.context = context;
    }

    public boolean isDataExists(String table){
        int count = 0;
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = sqLiteHelper.getWritableDatabase();
            switch (table){
                case USER:
                    cursor = database.query(MySQLiteHelper.TABLE_USER, new String[]{"COUNT(NickName)"},
                            null, null, null, null, null);
                    break;
            }
            assert cursor !=null;
            if (cursor.moveToFirst()){
                count = cursor.getInt(0);
            }
            if (count != 0){
                cursor.close();
                database.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (cursor != null){
                cursor.close();
            }
            if (database != null){
                database.close();
            }
        }
        return false;
    }

    public boolean initTable(String table){
        SQLiteDatabase database = null;
        boolean flag = false;
        try {
            database = sqLiteHelper.getWritableDatabase();
            database.beginTransaction();
            for (int i = 0 ; i < 5 ; i ++){
                switch (table){
                    case Dao.USER:
                        database.execSQL("insert into "+sqLiteHelper.TABLE_USER+" (Date,Time,Type,Status) values ('2021/12/22','20:56','数字考勤','出勤')");
                        break;
                }
                
            }
            database.setTransactionSuccessful();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (database!=null){
                database.endTransaction();
                database.close();
            }
        }
        return flag;
    }

    public boolean insertAllUsers(List<User> lists){
        boolean flag = true;

        for (User user : lists){
            flag = flag && insertUser(user);
        }

        return flag;
    }

    private boolean insertUser(User user) {
        SQLiteDatabase database = null;
        boolean flag = false;

        try {
            database = sqLiteHelper.getWritableDatabase();
            database.beginTransaction();

            ContentValues values = new ContentValues();
            values.put("Date",user.getDate());
            values.put("Time", user.getTime());
            values.put("Type", user.getType());
            values.put("Status", user.getStatus());
            database.insertOrThrow(MySQLiteHelper.TABLE_USER, null, values);

            database.setTransactionSuccessful();
            flag = true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "---", e);
        }finally {
            if (database != null){
                database.endTransaction();
                database.close();
            }
        }
        return flag;
    }

    public void execSQL(String sql) {
        SQLiteDatabase db = null;

        try {
            if (sql.contains("select")){
//                Toast.makeText(context, R.string.strUnableSql, Toast.LENGTH_SHORT).show();
            }else if (sql.contains("insert") || sql.contains("update") || sql.contains("delete")){
                db = sqLiteHelper.getWritableDatabase();
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
//                Toast.makeText(context, R.string.strSuccessSql, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
//            Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    public List<User> getAllUsers(){
        SQLiteDatabase database = null;
        Cursor cursor = null;

        try {
            database = sqLiteHelper.getReadableDatabase();
            cursor = database.query(MySQLiteHelper.TABLE_USER, USER_COLUMNS,
                    null, null, null, null, null);

            if (cursor.getCount() > 0){
                List<User> list = new ArrayList<>(cursor.getCount());
                while (cursor.moveToNext()){
                    list.add(parseUser(cursor));
                }
                database.close();
                cursor.close();
                return list;
            }
        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }finally {
            if (database != null){
                database.close();
            }
            if (cursor != null){
                cursor.close();
            }
        }
        return null;
    }

    public void refleshTable( String table){
        SQLiteDatabase database ;

        try{
            database = sqLiteHelper.getWritableDatabase();

            database.delete(table, null, null);
        }catch (Exception e) {

        }
    }

    @SuppressLint("Range")
    private User parseUser(Cursor cursor) {
        User user = new User();
        user.setDate(cursor.getString(cursor.getColumnIndex(USER_COLUMNS[0])));
        user.setTime(cursor.getString(cursor.getColumnIndex(USER_COLUMNS[1])));
        user.setType(cursor.getString(cursor.getColumnIndex(USER_COLUMNS[2])));
        user.setStatus(cursor.getString(cursor.getColumnIndex(USER_COLUMNS[3])));
        return user;
    }
}
