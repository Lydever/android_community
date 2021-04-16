package com.lyx.mycommunity.dao;

/**
 * @author create by liyingxia
 * 创建日期：2020/12/17 11:42
 * 包名： com.lyx.mynotepad.db
 * 描述：
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//创建数据库

public class NoteDataBaseHelper extends SQLiteOpenHelper {
    public NoteDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public static interface TableCreateInterface {  //表创建接口 有多张表时 方便统一调用
        //创建表
        public void onCreate(SQLiteDatabase db);
        //更新表
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //具体表的创建
        Note.getInstance().onCreate(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //具体表的更新
        Note.getInstance().onUpgrade(db, oldVersion, newVersion);
    }
}
