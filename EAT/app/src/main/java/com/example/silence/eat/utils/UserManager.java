package com.example.silence.eat.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silence.eat.model.User;

/**
 * Created by silence on 2017/7/15.
 */

public class UserManager{
    private UserHelper dbHelper;
    final String[] columns = {UserHelper.FIELD_ID, UserHelper.FIELD_Name, UserHelper.FIELD_Pwd};
    public UserManager(Context context){
        dbHelper=new UserHelper(context);
    }

    //登录用
    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    //注册用
    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,password) values(?,?,?,?)";
        Object obj[]={user.getUsername(),user.getPassword()};
        sdb.execSQL(sql, obj);
        return true;
    }
}