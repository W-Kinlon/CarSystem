package com.example.javaapplication.pages;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.javaapplication.MenuActivity;
import com.example.javaapplication.R;

import java.util.ArrayList;

public class dbPage extends AppCompatActivity {
    SQLiteDatabase db = MenuActivity.getDb();
    public String table_name_CN = MenuActivity.getTable_name_CN();
    public String table_name_EN = MenuActivity.getTable_name_EN();
    public  String[] table_column_EN = MenuActivity.getTable_column_EN();
    public String[] table_column_CN = MenuActivity.getTable_column_CN();


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_page);

        ArrayList<String[]> objs;
        objs = new ArrayList<String[]>();
        // 返回的数据都在Cursor里面
        Log.d("cursor", String.valueOf(table_column_EN.length));
        Cursor cursor = db.query(table_name_EN,
                null, null, null, null, null, "id");
        // 下面进行读取
        //把光标移向第一行
        if (cursor.moveToFirst()) {
            do {
                String[] obj = new String[table_column_EN.length];
                for(int i=0;i<table_column_EN.length;i++) {
                    Log.d("cursor",table_column_EN[i]);
                    obj[i] = cursor.getString(cursor.getColumnIndex(table_column_EN[i]));
                }
                objs.add(obj);
                //把光标移向下一行
            } while (cursor.moveToNext());
        }


        //可滑动布局
        ScrollView layout = (ScrollView) findViewById(R.id.layout);
        //创建表格
        CreateTable createTable = new CreateTable(
                this,
                table_column_CN,
                objs.size(),
                objs
        );
        TableLayout tableLayout = createTable.createTL();

        //布局参数
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,ScrollView.LayoutParams.WRAP_CONTENT);
        layout.addView(tableLayout,params);
    }

    //读取编辑框
    public ContentValues getEditText(){
        ContentValues edtexts = new ContentValues();

       String[] ets = new String[]{
               ((EditText) findViewById(R.id.et1)).getText().toString(),
               ((EditText) findViewById(R.id.et2)).getText().toString(),
               ((EditText) findViewById(R.id.et3)).getText().toString(),
               ((EditText) findViewById(R.id.et4)).getText().toString(),
               ((EditText) findViewById(R.id.et5)).getText().toString()
       };

        Log.d("getEditText1",ets[2]);

        try {
            edtexts.put(table_column_EN[0], Integer.parseInt(ets[0]));
            edtexts.put(table_column_EN[1], ets[1]);
            edtexts.put(table_column_EN[2], ets[2]);
            edtexts.put(table_column_EN[3], ets[3]);
            edtexts.put(table_column_EN[4], Integer.parseInt(ets[4]));
        } catch (Exception e) {
            e.printStackTrace();
        }


        Log.d("getEditText2",edtexts.getAsString(table_column_EN[2]));

        return edtexts;
    }

    public void Add(View view) {
        ContentValues edtexts = getEditText();

        try {
            db.insert(table_name_EN,null,edtexts);
            //刷新页面
            Refresh(view);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"输入有误！",Toast.LENGTH_LONG).show();
            Log.d(table_name_CN,"插入失败");
        }

        //增加租车表的同时增加还车表为未还
        if(table_name_EN.equals("rent")){
            ContentValues cv = edtexts;
            cv.remove("price");
            cv.put("state","未还");
            db.insert("return",null,edtexts);
        }
    }

    public void Upgrade(View view) {
        ContentValues edtexts = getEditText();
        try {
            String id =edtexts.get("id").toString();
            edtexts.remove("id");
            db.update(table_name_EN,edtexts,"id=?",new String[]{id});
            //刷新页面
            Refresh(view);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this,"输入有误！",Toast.LENGTH_LONG).show();
        }
    }

    public void Delete(View view) {
        ContentValues edtexts = getEditText();
        String id =edtexts.get("id").toString();
        try {
            db.delete(table_name_EN,"id=?",new String[]{id});
            //刷新页面
            Refresh(view);
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this,"输入有误！",Toast.LENGTH_LONG).show();
        }

        //删除还车表的同时删除租车表
        if(table_name_EN.equals("return")){
            db.delete("rent","id=?",new String[]{id});
        }
    }

    public void Refresh(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}