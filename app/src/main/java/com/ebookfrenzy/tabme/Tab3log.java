package com.ebookfrenzy.tabme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Owner-PC on 9/16/2017.
 */

public class Tab3log extends Fragment {

    private Button log;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3log, container, false);

        log = (Button)rootView.findViewById(R.id.logbtn);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLog();
            }
        });

        return rootView;
    }
    private void gotoLog()
    {
        Intent intent = new Intent(getActivity(), CallLogActivity.class);
        startActivity(intent);

    }
}
