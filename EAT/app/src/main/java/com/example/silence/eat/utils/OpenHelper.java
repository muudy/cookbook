package com.example.silence.eat.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by silence on 2017/7/12.
 */

public class OpenHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "Food.db";
    public static String TABLE_NAME = "food";

    public static String FIELD_ID = "fid";
    public static String FIELD_NAME = "name";
    public static String FIELD_STYLE = "style";
    public static String FIELD_MATERIAL = "material";
    public static String FIELD_ACCESSORIES = "accessories";
    public static String FIELD_STEPS = "steps";
    public static String FIELD_PATH_IMAGE = "path_image";
    public static String FIELD_PATH_AUDIO = "path_audio";
    public static String FIELD_PATH_VIDEO = "path_video";

    /*
        默认的构造函数
     */
    public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*
     * 简化的构造方法
	 * this(context, name, factory, version)
	 * name：数据库的名称，factory：游标工厂null系统默认的游标方式，version：数据库版本号
	 */
    public OpenHelper(Context context) {
        this(context, DATABASE_NAME, null, 1);
    }

    /*
    创建时执行
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //数据库创建语句
        String createTableString = "create table " + TABLE_NAME + " (" +
                FIELD_ID + " integer primary key autoincrement," +
                FIELD_NAME + " varchar(20) not null default ''," +
                FIELD_STYLE + " varchar(20) not null default ''," +
                FIELD_MATERIAL + " varchar(30) not null default ''," +
                FIELD_ACCESSORIES + " varchar(30) not null default ''," +
                FIELD_STEPS + " varchar(300) not null default ''," +
                FIELD_PATH_IMAGE + " varchar(100) not null default ''," +
                FIELD_PATH_AUDIO + " varchar(100) not null default ''," +
                FIELD_PATH_VIDEO + " varchar(100) not null default ''" +
                ")";
        db.execSQL(createTableString);
        String insertTableString1 = "insert into "+TABLE_NAME+" values(1,'海参鸡蛋汤','家常','鸡蛋，海参','盐，香油','1. 海参切小片；2. 葱切花；3. 鸡蛋蛋搅匀4. 锅内放水烧开，放入海参煮一分钟，倒入鸡蛋搅成蛋花状，撒入葱花搅出香味，放适量盐调味，最后滴一滴香油提味儿即可。','','','')";
        db.execSQL(insertTableString1);
        String insertTableString2 = "insert into "+TABLE_NAME+" values(2,'咖喱鸡盖浇饭','咖喱','鸡肉，咖喱，土豆','大蒜，生姜','1..将土豆去皮切成滚刀块，青椒去蒂挖掉籽切成菱形块，子姜、老姜、大蒜切成片待用；2.将鸡肉切块放入沸水中焯水，倒入适量料酒去腥，去除血污和浮沫；3.锅内倒色拉油烧热至6-7成，下姜片、蒜片 爆香，随后放入鸡块、子姜片翻炒均匀；4.下入咖喱块至完全融化，加入适量清水煮熟；5临出锅前将青椒倒入锅中翻炒1-2分钟，撒盐调味即可出锅','','','')";
        db.execSQL(insertTableString2);
        String insertTableString3 = "insert into "+TABLE_NAME+" values(3,'红糖凉糕','家常','凉糕粉','红糖，水','1.调浆，将50g凉糕粉加入80g水中调成均匀浆状；2.熟化，在锅中先准备500g冷水，将调好的浆搅拌均匀倒入锅中，用大火加热熟化，在加热过程中不停搅拌，以免粘锅，当米浆变为粘稠糊状后，改为中火继续加热5分钟至熟 3.定型，将锅移火搅拌片刻，待大气泡消失后分装于器皿中放置冰箱冷藏3-4个小时口感更佳；4.将红糖加少量水放于蒸锅中蒸化至浓稠状态；5.将红糖加少量水放于蒸锅中蒸化至浓稠状态','','','')";
        db.execSQL(insertTableString3);
        String insertTableString4 = "insert into "+TABLE_NAME+" values(4,'麻婆豆腐','川菜','豆腐，肉末','酱油，盐','1.锅中再放余油烧至五成热，放入郫县豆瓣酱，煸炒出红油后，放入姜末、蒜末、剁细的豆豉炒匀；2.向锅中加入大约200毫升清水、白砂糖、家乐鸡精搅匀，煮至汤汁滚沸；3.将切好的豆腐、牛肉末倒入锅中旺火烧1分钟， 加少许水淀粉勾薄芡，待汤汁变浓稠即可关火；4. 出锅前撒上事先碾碎的花椒粉、青蒜叶碎即可','','','')";
        db.execSQL(insertTableString4);
        String insertTableString5 = "insert into "+TABLE_NAME+" values(5,'干炒牛河','粤菜','鲜河粉，里脊','洋葱，白糖','1. 小锅多放油，将牛肉片过油后，捞起沥油备用；2.炒锅烧热，放少许油将胡萝卜和洋葱先炒香；3.炒锅烧热，放少许油将胡萝卜和洋葱先炒香；4. 放入牛肉、韭黄、香葱，翻炒几下；5.最后放入备好的酱汁，炒到 收汁即可','','','')";
        db.execSQL(insertTableString5);



    }

    /*
        更新升级时执行
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
