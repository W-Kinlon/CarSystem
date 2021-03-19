package com.example.javaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.javaapplication.database.DBhelper;
import com.example.javaapplication.pages.ProfitActivity;
import com.example.javaapplication.pages.dbPage;

public class MenuActivity extends AppCompatActivity {
    DBhelper dbc;

    public static String getTable_name_CN() {
        return table_name_CN;
    }


    public static String getTable_name_EN() {
        return table_name_EN;
    }


    public static String[] getTable_column_EN() {
        return table_column_EN;
    }


    public static String[] getTable_column_CN() {
        return table_column_CN;
    }

    public static String table_name_CN;
    public static String table_name_EN;
    public static String[] table_column_EN;
    public static String[] table_column_CN;

    public static SQLiteDatabase getDb() {
        return db;
    }

    static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //连接数据库
        dbc = new DBhelper(this,"JavaAPP.db",null,6);
        db = dbc.getWritableDatabase();
    }

    public void carInfoManage(View view) {
        table_name_CN = "车辆表";
        table_name_EN = "car";
        table_column_EN = new String[]{"id", "name", "color", "type", "price"};
        table_column_CN = new String[]{"车辆编号", "车辆名称", "车辆颜色", "车辆类型", "车辆计价/时"};
        //创建表
        String Create_Table="CREATE TABLE IF NOT EXISTS " +table_name_EN+
                "(" +
                "id INT NOT NULL PRIMARY KEY," +
                "name VARCHAR(20)," +
                "color VARCHAR(20)," +
                "type VARCHAR(20)," +
                "price DOUBLE" +
                ")";
        db.execSQL(Create_Table);
        Log.d("onCreate","car表创建成功");

        Intent intent = new Intent(MenuActivity.this, dbPage.class);
        startActivity(intent);
    }

    public void rentInfoManage(View view) {
        table_name_CN = "租车表";
        table_name_EN = "rent";
        table_column_EN = new String[]{"id", "time", "user", "car_id", "price"};
        table_column_CN = new String[]{"租车编号", "租车时间", "租车用户","车辆编号", "租车费用"};
        //创建表
        String Create_Table="CREATE TABLE IF NOT EXISTS " +table_name_EN+
                "(" +
                "id INT NOT NULL PRIMARY KEY," +
                "time VARCHAR(20)," +
                "user VARCHAR(20)," +
                "car_id INT NOT NULL," +
                "price DOUBLE," +
                "FOREIGN KEY (car_id) REFERENCES car (id)" +     //外键参照car表
                ")";
        db.execSQL(Create_Table);
        Log.d("onCreate",table_name_CN+"创建成功");

        Intent intent = new Intent(MenuActivity.this, dbPage.class);
        startActivity(intent);
    }

    public void returnInfoManage(View view) {
        table_name_CN = "还车表";
        table_name_EN = "return";
        table_column_EN = new String[]{"id", "time", "user", "car_id", "state"};
        table_column_CN = new String[]{"还车编号", "还车时间", "还车用户","车辆编号", "车辆状态"};
        //创建表
        String Create_Table="CREATE TABLE IF NOT EXISTS " +table_name_EN+
                "(" +
                "id INT NOT NULL PRIMARY KEY," +
                "time VARCHAR(20)," +
                "user VARCHAR(20)," +
                "car_id INT NOT NULL," +
                "state VARCHAR(20)," +
                "FOREIGN KEY (car_id) REFERENCES car (id)" +     //外键参照car表
                ")";
        db.execSQL(Create_Table);
        Log.d("onCreate",table_name_CN+"创建成功");
        Intent intent = new Intent(MenuActivity.this, dbPage.class);
        startActivity(intent);
    }

    public void fixInfoManage(View view) {
        table_name_CN = "修车表";
        table_name_EN = "fix";
        table_column_EN = new String[]{"id", "time", "problem", "car_id", "price"};
        table_column_CN = new String[]{"修车编号", "修车时间", "车辆故障","车辆编号", "修车费用"};
        //创建表
        String Create_Table="CREATE TABLE IF NOT EXISTS " +table_name_EN+
                "(" +
                "id INT NOT NULL PRIMARY KEY," +
                "time VARCHAR(20)," +
                "problem VARCHAR(20)," +
                "car_id INT NOT NULL," +
                "price DOUBLE," +
                "FOREIGN KEY (car_id) REFERENCES car (id)" +     //外键参照car表
                ")";
        db.execSQL(Create_Table);
        Log.d("onCreate",table_name_CN+"创建成功");
        Intent intent = new Intent(MenuActivity.this, dbPage.class);
        startActivity(intent);
    }

    public void getProfit(View view) {
        Intent intent = new Intent(MenuActivity.this, ProfitActivity.class);
        startActivity(intent);
    }


//    public void getJson(View view) {
//        final TextView tv1 = (TextView) findViewById(R.id.tv1);
//        final String[] html = {""};
//        String content = "";
//
//        DBcreate dBcreate = new DBcreate(this,"JavaAPP.db",null,5);
//        SQLiteDatabase db = dBcreate.getWritableDatabase();
//        UserDB userDB = new UserDB(db);
//
//        LinkedList<User> users = userDB.getUser();
//        Iterator<User> userIterator = users.iterator();
//        while (userIterator.hasNext())
//        {
//            content += userIterator.next().getName()+userIterator.next().getPwd();
//        }
//        tv1.setText(content);
//
//        //用子线程处理网络活动
////        JsonThreadService jts = new JsonThreadService("https://api.kinlon.work/winform/DBselect?pwd=winform&sql=select%20*%20FROM%20qquser");
////        jts.start();
////        try {
////            jts.join();
////            tv1.setText(jts.getJson());
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//    }


}

