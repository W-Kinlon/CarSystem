package com.example.javaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.javaapplication.database.table_user.User;
import com.example.javaapplication.database.table_user.UserDB;
import com.example.javaapplication.database.DBhelper;

import java.util.Iterator;
import java.util.LinkedList;

public class LoginActivity extends AppCompatActivity {
    UserDB userDB;
    User user;
    EditText et_name;
    EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
    }

    public void Login(View view) {
        //登录逻辑实现
        if(!et_name.getText().equals("")){
            if(!et_pwd.getText().equals("")){

                DBhelper dBcreate = new DBhelper(this,"JavaAPP.db",null,6);
                SQLiteDatabase db = dBcreate.getWritableDatabase();
                userDB = new UserDB(db);
                LinkedList<User> fromDBusers = userDB.get();
                //迭代器
                Iterator<User> userIterator = fromDBusers.iterator();

                boolean isUser = false;
                //遍历数据库（用户名，密码）
                while (userIterator.hasNext()){
                    User uit = userIterator.next();
                    if(uit.getName().equals(et_name.getText().toString())){
                        String pwd = uit.getPwd();
                        if(et_pwd.getText().toString().equals(pwd)){
                            isUser = true;

                            //是否管理员 进行页面间传值
                            String isManager = uit.isManager();
                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                if(isUser){
                    Toast.makeText(LoginActivity.this,"登陆成功!",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"账号密码错误!",Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(LoginActivity.this,"密码不能为空!",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(LoginActivity.this,"用户名不能为空!",Toast.LENGTH_LONG).show();
        }
    }

    public void NewAccount(View view) {
        Intent intent = new Intent(LoginActivity.this,SignActivity.class);
        startActivity(intent);
    }
}