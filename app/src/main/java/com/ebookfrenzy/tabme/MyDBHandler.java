package com.ebookfrenzy.tabme;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Owner-PC on 9/20/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "childDB.db";
    public static final String TABLE_CHILD = "child";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_MOBILE = "mobile";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, DATABASE_NAME, factory,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CHILD_TABLE = "CREATE TABLE " + TABLE_CHILD + "(" + COLUMN_NAME +
                " INTEGER PRIMARY KEY," + COLUMN_AGE + " TEXT " + COLUMN_MOBILE +
                " INTEGER" + ")";
        db.execSQL(CREATE_CHILD_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_CHILD);
        onCreate(db);
    }

    public void addChild(Child child)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, child.getName());
        values.put(COLUMN_MOBILE, child.getMobile());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CHILD, null, values);
        db.close();
    }

    public Child findChild (String childName)
    {
        String query = "SELECT * FROM " + TABLE_CHILD + " WHERE " +
                COLUMN_NAME + " = \"" + childName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Child child = new Child();

        if (cursor.moveToFirst())
        {
            cursor.moveToFirst();
            child.setAge(Integer.parseInt(cursor.getString(0)));
            child.setName(cursor.getString(1));
            child.setMobile(Integer.parseInt((cursor.getString(2))));

            cursor.close();
        }
        else
        {
            child = null;
        }
        db.close();
        return child;
    }

    public boolean deleteChild(String childName)
    {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_CHILD + " WHERE " + COLUMN_NAME +
                " = \"" + childName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Child child = new Child();

        if (cursor.moveToFirst())
        {
            child.setAge(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_CHILD, COLUMN_AGE + " =?", new String[] { String.valueOf(child.getAge()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }



}
