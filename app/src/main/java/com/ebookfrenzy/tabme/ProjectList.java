package com.ebookfrenzy.tabme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectList extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ProjectList";
    DatabaseActivity mDatabaseActivity; // = new DatabaseActivity((getApplicationContext()));
    ListView mListView;
    Button testBtn;
    FloatingActionButton addBtn;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        mListView = (ListView) findViewById(R.id.listView);
      /*  mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(this, TabbedActivity.class);
                startActivity(intent);

            }
        });*/

       // testBtn=(Button)findViewById(R.id.list_test_btn);
        addBtn=(FloatingActionButton)findViewById(R.id.fab);

//        testBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);

        mDatabaseActivity = new DatabaseActivity(this);

    }
    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView. ");
        //append data to list

            Cursor cursor = mDatabaseActivity.getData();
                ArrayList<String> listData = new ArrayList<>();

                int numResults = cursor.getCount();
                Log.d(TAG, "Cursor items count - " + numResults);

                ////////////////////////

            while(cursor.moveToNext())
            {
                //list through rows then add the data
                Log.d(TAG, "Item data - " + cursor.getString(0) + " | " + cursor.getString(1));
                listData.add(cursor.getString(0));
            }
                ////Create a list adapter
               //  adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listData);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
                 mListView.setAdapter(adapter);




    }
    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        populateListView();
    }


    @Override
    public void onClick(View v)
    {
        if(v==addBtn)
        {
            Intent intent = new Intent(this, RegisterChildActivity.class);
            startActivity(intent);
        }
     /*   else if (v==testBtn)
        {
            Intent intent = new Intent(this, TabbedActivity.class);
            startActivity(intent);
        }*/
    }

}
