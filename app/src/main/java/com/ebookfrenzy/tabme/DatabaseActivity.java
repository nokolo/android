package com.ebookfrenzy.tabme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Owner-PC on 9/20/2017.
 */

public class DatabaseActivity extends SQLiteOpenHelper
{
    private static final String TAG = "DatabaseActivity";

    private static final String TABLE_NAME = "child_table" ;
    private static final String COL1 = "ID";
    private static final String COL2 = "name";

    public DatabaseActivity(Context context)
    {
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate (SQLiteDatabase db)
    {
        String createTable = "CREATE TABLE "  + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "  +
        COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    public boolean addData(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);

        Log.d(TAG, "addData: Adding " + item + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }
   /* public boolean deleteData(String item)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.remove(item);

        Log.d(TAG, "delData: Removing " + item + " from " + TABLE_NAME);


    }*/

    public Cursor getData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;

    }

}
