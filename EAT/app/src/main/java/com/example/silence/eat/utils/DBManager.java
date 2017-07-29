package com.example.silence.eat.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.silence.eat.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * 增删改查的工具类
 */

public class DBManager {
    private OpenHelper helper;
    final String[] columns = {OpenHelper.FIELD_ID, OpenHelper.FIELD_NAME, OpenHelper.FIELD_STYLE,
            OpenHelper.FIELD_MATERIAL, OpenHelper.FIELD_ACCESSORIES, OpenHelper.FIELD_STEPS,OpenHelper.FIELD_PATH_IMAGE,OpenHelper.FIELD_PATH_AUDIO,OpenHelper.FIELD_PATH_VIDEO};
    private SQLiteDatabase database = null;
    private List<Food> foodList = null;

    public DBManager(OpenHelper helper) {
        this.helper = helper;
        openDatabase();//打开数据库（没有则新建）
    }

    //查询所有Food
    public List<Food> selectFood() {
        //Log.e("查询结果", "进入查询");
        String table = OpenHelper.TABLE_NAME;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = OpenHelper.FIELD_ID + " DESC";
        //按照ID降序排序
        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        foodList = new ArrayList<Food>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int fid = cursor.getInt(0);
                String name = cursor.getString(1);
                String style = cursor.getString(2);
                String material = cursor.getString(3);
                String accessories = cursor.getString(4);
                String steps = cursor.getString(5);
                String path_image = cursor.getString(6);
                String path_audio = cursor.getString(7);
                String path_video = cursor.getString(8);
                Food food = new Food(fid, name, style, material, accessories, steps ,path_image,path_audio,path_video);
                foodList.add(food);
                Log.e("查询结果", food + "");
            }
        } else {
            Log.e("查询结果", "查询结果为空！");
        }
        return foodList;
    }

    public void insertFood(Food food) {
        ContentValues values = new ContentValues();
        values.putNull(OpenHelper.FIELD_ID);
        values.put(OpenHelper.FIELD_NAME, food.getName());
        values.put(OpenHelper.FIELD_STYLE, food.getStyle());
        values.put(OpenHelper.FIELD_MATERIAL, food.getMaterial());
        values.put(OpenHelper.FIELD_ACCESSORIES, food.getAccessories());
        values.put(OpenHelper.FIELD_STEPS, food.getSteps());
        values.put(OpenHelper.FIELD_PATH_IMAGE, food.getPath_image());
        values.put(OpenHelper.FIELD_PATH_AUDIO, food.getPath_audio());
        values.put(OpenHelper.FIELD_PATH_VIDEO, food.getPath_video());
        //Toast.makeText(MainActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
        long id = database.insert(OpenHelper.TABLE_NAME, null, values);
        Log.e("值", "新增ID：" + id);
    }

    public void updateFood(int fid,Food food){
        String selection = "fid = ?";
        String[] selectionArgs = {fid + ""};
        ContentValues values = new ContentValues();
        values.put(OpenHelper.FIELD_NAME, food.getName());
        values.put(OpenHelper.FIELD_STYLE, food.getStyle());
        values.put(OpenHelper.FIELD_MATERIAL, food.getMaterial());
        values.put(OpenHelper.FIELD_ACCESSORIES, food.getAccessories());
        values.put(OpenHelper.FIELD_STEPS, food.getSteps());
        values.put(OpenHelper.FIELD_PATH_IMAGE, food.getPath_image());
        values.put(OpenHelper.FIELD_PATH_AUDIO, food.getPath_audio());
        values.put(OpenHelper.FIELD_PATH_VIDEO, food.getPath_video());
        //Toast.makeText(MainActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
        long id = database.update(OpenHelper.TABLE_NAME,values,selection,selectionArgs);
    }


    public void openDatabase() {
        if (database == null) {
            //创建一个可以写的数据库
            database = helper.getWritableDatabase();
            //Toast.makeText(MainActivity.this, "创建成功！", Toast.LENGTH_SHORT).show();
        }
    }


    //通过name查询并返回list
    public List<Food> queryFood(String nameString) {
        String table = OpenHelper.TABLE_NAME;
        String selection = "name like ?";
        String[] selectionArgs = {nameString + "%"};
        String groupBy = null;
        String having = null;
        String orderBy = OpenHelper.FIELD_ID + " DESC";
        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        //Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        foodList = new ArrayList<Food>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int fid = cursor.getInt(0);
                String name = cursor.getString(1);
                String style = cursor.getString(2);
                String material = cursor.getString(3);
                String accessories = cursor.getString(4);
                String steps = cursor.getString(5);
                String path_image = cursor.getString(6);
                String path_audio = cursor.getString(7);
                String path_video = cursor.getString(8);
                Food food = new Food(fid, name, style, material, accessories, steps ,path_image,path_audio,path_video);
                foodList.add(food);
                //Log.e("值：",s.getSid()+"  "+s.getName()+"  "+s.getAge());
                //Log.e("查询结果", food + "");
            }
        } else {
            //Log.e("查询结果", "查询结果为空！");
        }
        return foodList;
    }

    //通过id删除
    public void deleteFoodById(int id) {
        Food food = null;
        String table = OpenHelper.TABLE_NAME;
        String selection = "fid = ?";
        String[] selectionArgs = {id + ""};
        database.delete(table,selection,selectionArgs);
    }

    //通过id查询并返回对象
    public Food queryFoodById(int id) {
        Food food = null;
        String table = OpenHelper.TABLE_NAME;
        String selection = "fid = ?";
        String[] selectionArgs = {id + ""};
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        //Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        foodList = new ArrayList<Food>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int fid = cursor.getInt(0);
                String name = cursor.getString(1);
                String style = cursor.getString(2);
                String material = cursor.getString(3);
                String accessories = cursor.getString(4);
                String steps = cursor.getString(5);
                String path_image = cursor.getString(6);
                String path_audio = cursor.getString(7);
                String path_video = cursor.getString(8);
                food = new Food(fid, name, style, material, accessories, steps ,path_image,path_audio,path_video);
                foodList.add(food);
            }
        } else {
            Log.e("查询结果", "查询结果为空！");
        }
        return food;
    }

    public void CloseDatabase() {
        database.close();
    }

}
