package com.example.javaapplication.database.table_user;

public class User {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String isManager() {
        return isManager;
    }

    public void setManager(String manager) {
        isManager =manager;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    private String name;
    private String pwd;
    private String isManager;
}
