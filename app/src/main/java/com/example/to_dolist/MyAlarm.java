package com.example.to_dolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class MyAlarm extends BroadcastReceiver {

    //the method will be fired when the alarm is triggerred
    @Override
    public void onReceive(Context context, Intent intent) {

        MediaPlayer mediaPlayer=MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();
        //you can check the log that it is fired
        //Here we are actually not doing anything
        //but you can do any task here that you want to be done at a specific time everyday
        Log.d("MyAlarmBelal", "Alarm just fired");
        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }

}