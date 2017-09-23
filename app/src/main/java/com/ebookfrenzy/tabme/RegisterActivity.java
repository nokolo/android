package com.ebookfrenzy.tabme;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    EditText _txtname, _txtmobile, _txtpassword;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        openHelper=new DatabaseHelper(this);
        _btnreg = (Button)(findViewById(R.id.btnSubmit));
        _txtname = (EditText)findViewById(R.id.childname);
        _txtpassword = (EditText)findViewById(R.id.txtpass);
        _txtmobile = (EditText)findViewById(R.id.age);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db= openHelper.getWritableDatabase();
                String name = _txtname.getText().toString();
                String mobile = _txtmobile.getText().toString();
                String pass = _txtpassword.getText().toString();


            }
        });
    }
    public void insertData(String name, String mobile, String password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, name);
    }
}
