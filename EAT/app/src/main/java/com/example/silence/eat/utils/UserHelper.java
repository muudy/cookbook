package com.example.silence.eat.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by silence on 2017/7/15.
 */

public class UserHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "User.db";
    public static String TABLE_NAME = "user";
    public static String FIELD_ID = "uid";
    public static String FIELD_Name = "username";
    public static String FIELD_Pwd = "password";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //数据库创建语句
        String createTableString = "create table " + TABLE_NAME + " (" +
                FIELD_ID + " integer primary key autoincrement," +
                FIELD_Name + " varchar(20) not null default ''," +
                FIELD_Pwd + " varchar(20) not null default ''"+
                ")";
        db.execSQL(createTableString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    /*
     默认的构造函数
  */
    public UserHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /*
    * 简化的构造方法
    * this(context, name, factory, version)
    * name：数据库的名称，factory：游标工厂null系统默认的游标方式，version：数据库版本号
    */
    public UserHelper(Context context) {
        this(context, DATABASE_NAME, null, 1);
    }

}
