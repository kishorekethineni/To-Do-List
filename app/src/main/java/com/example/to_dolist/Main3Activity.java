package com.example.to_dolist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Main3Activity extends AppCompatActivity {

    //the timepicker object
    TimePicker timePicker;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar2=findViewById(R.id.toolbaralarm);
        setSupportActionBar(toolbar2);
        //getting the timepicker object
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        Button everyday=findViewById(R.id.button3);
        //attaching clicklistener on button

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We need a calendar object to get the specified time in millis
                //as the alarm manager method takes time in millis to setup the alarm
                Calendar calendar = Calendar.getInstance();
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }

                setAlarm(calendar.getTimeInMillis());
            }
        });
        everyday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                if (android.os.Build.VERSION.SDK_INT >= 23) {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(), timePicker.getMinute(), 0);
                } else {
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
                }
                setAlarmeveryday(calendar.getTimeInMillis());
            }
        });
    }
    private void setAlarm(long time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent i = new Intent(this, MyAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
    }

public void setAlarmeveryday(long time)
{
    AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent i = new Intent(this, MyAlarm.class);
    PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
    am.setRepeating(AlarmManager.RTC, time, AlarmManager.INTERVAL_DAY, pi);
    Toast.makeText(this, "Alarm repeats everyday", Toast.LENGTH_SHORT).show();
}
}