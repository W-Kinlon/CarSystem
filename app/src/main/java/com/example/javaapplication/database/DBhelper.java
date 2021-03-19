package com.example.javaapplication.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {
    private Context context;

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //创建用户表
        String Create_User="CREATE TABLE IF NOT EXISTS " +
                "user(" +
                "name VARCHAR(20)NOT NULL PRIMARY KEY," +
                "pwd VARCHAR(20)," +
                "isManager VARCHAR(20)" +
                ")";
        db.execSQL(Create_User);
        Log.d("onCreate","用户表创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}