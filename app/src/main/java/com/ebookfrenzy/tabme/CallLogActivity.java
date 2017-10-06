package com.ebookfrenzy.tabme;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CallLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        if(ContextCompat.checkSelfPermission(CallLogActivity.this,
                Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(CallLogActivity.this,
                    Manifest.permission.READ_CALL_LOG))
            {
                ActivityCompat.requestPermissions(CallLogActivity.this,
                        new String[] {Manifest.permission.READ_CALL_LOG}, 1);
            }
            else
            {
                ActivityCompat.requestPermissions(CallLogActivity.this,
                        new String[] {Manifest.permission.READ_CALL_LOG}, 1);
            }
        }
        else
        {
            TextView textView = (TextView) findViewById(R.id.logView);
            textView.setText(getCallDetails());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String [] permissions, int [] grantResults)
    {
       switch (requestCode)
       {
           case 1: {
               if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
               {
                   if(ContextCompat.checkSelfPermission(CallLogActivity.this,
                           Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED)
                   {
                       Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                       TextView textView = (TextView) findViewById(R.id.logView);
                       textView.setText(getCallDetails());
                   }
                   else
                   {
                       Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
                   }
                   return;
               }
           }
       }
    }

    private String getCallDetails()
    {
        StringBuffer sb = new StringBuffer();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

        sb.append("Call Log Information:\n\n");

        while(cursor.moveToNext())
        {
            String phoneNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm");
            String dateString = formatter.format(callDayTime);
            String callDuration = cursor.getString(duration);
            String dir =  null;
            int dircode = Integer.parseInt(callType);
            switch (dircode)
            {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            sb.append("\nPhone Number: " + phoneNumber + " \nCallType: " + dir + " \nCall Date: " + dateString +
                    " \nCall Duration" +  callDuration);

            sb.append("\n---------------------------------------------------");
        }
        cursor.close();
        return sb.toString();
    }

}
