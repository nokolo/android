package com.ebookfrenzy.tabme;

/**
 * Created by Owner-PC on 9/16/2017.
 */
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Tab1location extends Fragment {

    private Button map;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       // Intent intent = new Intent(getActivity(), MapsActivity.class);
        //startActivity(intent);
        View rootView = inflater.inflate(R.layout.tab1location, container, false);
        map = (Button)rootView.findViewById(R.id.mapbtn);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMap();
            }
        });



        return rootView;
    }

    private void gotoMap()
    {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);

    }

}
