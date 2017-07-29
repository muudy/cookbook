package com.example.silence.eat.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.silence.eat.model.User;

/**
 * Created by silence on 2017/7/15.
 */

public class UserService{
    private UserHelper dbHelper;
    private SQLiteDatabase database = null;
    public UserService(Context context){

        dbHelper=new UserHelper(context);
        openDatabase();//打开数据库（没有则新建）
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
        ContentValues values = new ContentValues();
        values.putNull(UserHelper.FIELD_ID);
        values.put(UserHelper.FIELD_Name, user.getUsername());
        values.put(UserHelper.FIELD_Pwd, user.getPassword());
        //Toast.makeText(MainActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
        long id = database.insert(UserHelper.TABLE_NAME, null, values);
        return true;
    }
    public void openDatabase() {
        if (database == null) {
            //创建一个可以写的数据库
            database = dbHelper.getWritableDatabase();
            //Toast.makeText(MainActivity.this, "创建成功！", Toast.LENGTH_SHORT).show();
        }
    }
}