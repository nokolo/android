package com.ebookfrenzy.tabme;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _btnreg;
    EditText _txtname, _txtmobile, _txtpassword, _txtconfirm;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        openHelper=new DatabaseHelper(this);
        _btnreg = (Button)(findViewById(R.id.btnSubmit));

        _txtname = (EditText)findViewById(R.id.regname);
        _txtpassword = (EditText)findViewById(R.id.txtconfirm);
        _txtconfirm = (EditText)findViewById(R.id.txtpass);
        _txtmobile = (EditText)findViewById(R.id.regmobile);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = _txtname.getText().toString();
                String mobile = _txtmobile.getText().toString();
                String pass = _txtpassword.getText().toString();
                String pass2 =_txtconfirm.getText().toString();

                if (!pass.equals(pass2)) {
                    Toast passValue = Toast.makeText(RegisterActivity.this, "Passwords don't match!" , Toast.LENGTH_SHORT);
                    passValue.show();
                } else {
                    //insert details in database
                    Contacts c = new Contacts();
                    c.setName(name);
                    c.setMobile(mobile);
                    c.setPassword(pass);
                    helper.insertContact(c);
                }
            };
        });
    }

    public void insertData(String name, String mobile, String password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, name);
    }

}
