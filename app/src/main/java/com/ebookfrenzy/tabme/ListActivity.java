package com.ebookfrenzy.tabme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity{

  //  ArrayList<String> listItems = new ArrayList<>();
  //  ArrayAdapter<String> adapter;
    private ListView myListView;


    FloatingActionButton addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

      //  myListView = (ListView) findViewById(R.id.listView);
     //   adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
     //   myListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addListItem();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
