package com.example.javaapplication.database.table_user;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;

public class UserDB {
    SQLiteDatabase sdb;
    public UserDB(SQLiteDatabase db) {
        this.sdb = db;
    }

    //查询用户
    public LinkedList<User> get() {
        LinkedList<User> users = new LinkedList<User>();
        User user;
        // 返回的数据都在Cursor里面，
        Cursor cursor = sdb.query("user",
                null, null, null, null, null, null);
        // 下面进行读取
        //把光标移向第一行
        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setName(cursor.getString(cursor
                        .getColumnIndex("name")));
                user.setPwd(cursor.getString(cursor
                        .getColumnIndex("pwd")));
                user.setManager(cursor.getString(cursor
                        .getColumnIndex("isManager")));
                users.add(user);
                //把光标移向下一行
            } while (cursor.moveToNext());
        }
        return users;
    }


    //注册用户
    public Boolean add(User user){
        try {
            ContentValues cv = new ContentValues();
            cv.put("name", user.getName());
            cv.put("pwd", user.getPwd());
            cv.put("isManager", user.isManager());

            sdb.insert("user", null, cv);
            Log.d("注册用户",user.getName() + user.getPwd() + user.isManager());
            //注册成功
            return true;
        }catch (Exception e){
            e.printStackTrace();
            //注册失败
            return false;
        }
    }
    }

