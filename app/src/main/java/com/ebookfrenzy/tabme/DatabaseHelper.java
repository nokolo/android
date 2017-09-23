package com.ebookfrenzy.tabme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Owner-PC on 9/9/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="register";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Mobile";
    public static final String COL_4 = "Password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE" + TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Mobile TEXT, Password TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }
}
