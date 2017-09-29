package com.ebookfrenzy.tabme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterChildActivity extends AppCompatActivity {

    private static final String TAG = "RegisterChildActivity";
    DatabaseActivity myDBHandler;
    private Button subbtn, btnViewData, delData;
    private EditText name, age, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_child);
        subbtn = (Button) findViewById(R.id.subbtn);
        //delData = (Button) findViewById(R.id.delData);
        name = (EditText) findViewById(R.id.childname);
        age = (EditText) findViewById(R.id.age);
        mobile = (EditText) findViewById(R.id.mobile);
        btnViewData = (Button) findViewById(R.id.viewdata);
        myDBHandler = new DatabaseActivity(this);

        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = name.getText().toString();
                if(name.length() != 0)
                {
                    AddData(newEntry);
                }
                else
                {
                    toastMessage("Missing information in field");
                }
            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterChildActivity.this, ProjectList.class);
                startActivity(intent);
            }
        });
         /*
        delData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = name.getText().toString();
                if(name.length() != 0)
                {
                    deleteData(newEntry);
                }
                else
                {
                    toastMessage("Missing information in field");
                }
            }
        });*/


    }
    public void AddData(String newEntry)
    {
        boolean insertData = myDBHandler.addData(newEntry);
        if (insertData){
            toastMessage("Data Successfully Inserted!");
        }
        else {
            toastMessage("Oops sorry there is an issue.");
        }
    }
   /* public void deleteData(String newEntry)
    {
        boolean insertData = myDBHandler.deleteData(newEntry);
        if (insertData){
            toastMessage("Data Successfully Deleted!");
        }
        else {
            toastMessage("Oops sorry there is an issue.");
        }
    }*/

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
