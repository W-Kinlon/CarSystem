package com.example.javaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.javaapplication.database.table_user.User;
import com.example.javaapplication.database.table_user.UserDB;
import com.example.javaapplication.database.DBhelper;

public class SignActivity extends AppCompatActivity {
    UserDB userDB;
    User user;

    EditText et_name;
    EditText et_pwd;
    RadioGroup rg;
    String ManagerOfRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        DBhelper dBcreate = new DBhelper(this,"JavaAPP.db",null,6);
        SQLiteDatabase db = dBcreate.getWritableDatabase();
        userDB = new UserDB(db);

        rg = findViewById(R.id.rg_check);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            //单选框监听事件
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = radioGroup.findViewById(i);
                ManagerOfRadioGroup = radioButton.getText().toString();
            }
        });
    }

    public void Sign(View view) {
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        if(!et_name.getText().equals("")){
            if(!et_pwd.getText().equals("")){
                if(!ManagerOfRadioGroup.equals(null)){
                    user = new User();
                    user.setName(et_name.getText().toString());
                    user.setPwd(et_pwd.getText().toString());
                    user.setManager(ManagerOfRadioGroup);

                    boolean isSigning = userDB.add(user);
                    if(isSigning){
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(SignActivity.this,"注册成功!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(SignActivity.this,"注册失败!",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(SignActivity.this,"请填写是否管理员!",Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(SignActivity.this,"密码不能为空!",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(SignActivity.this,"用户名不能为空!",Toast.LENGTH_LONG).show();
        }
    }
}