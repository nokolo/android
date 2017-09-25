package com.ebookfrenzy.tabme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Owner-PC on 9/9/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME="contact.db";
    public static final String TABLE_NAME="CONTACT";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Mobile";
    public static final String COL_4 = "Password";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "CREATE TABLE CONTACT (id INTEGER PRIMARY KEY NOT NULL," +
            "Name TEXT NOT NULL , Mobile TEXT NOT NULL , Password TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void insertContact(Contacts c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM CONTACT";
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(COL_1, count);
        values.put(COL_2, c.getName());
        values.put(COL_3, c.getMobile());
        values.put(COL_4, c.getPassword());
        Log.d(TAG, "addData: Adding " + values + " to " + TABLE_NAME);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "No location";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(1);

                if(a.equals(uname))
                {
                    b = cursor.getString(2);
                    break;
                }
            }while(cursor.moveToNext());

        }
        return b;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      //  db.execSQL("CREATE TABLE " + TABLE_NAME+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Mobile TEXT, Password TEXT)");
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);

    }
}
