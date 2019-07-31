package com.tcsoft.searchmachinary.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import com.tcsoft.searchmachinary.model.action.TokenAction;
import com.tcsoft.searchmachinary.model.action.TokenActionImpl;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.receiver.TokenReceiver;

public class TokenService extends Service {



    @Override
    public int onStartCommand(Intent i, int flags, int startId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                TokenAction tokenAction = new TokenActionImpl();
                tokenAction.getToken(new ActionListener<String>() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onFailure(String tag, String msg) {
                        Log.d(tag, msg);
                    }
                });
            }
        });
        thread.start();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int time = 1000 * 60 * 60;
        long triggerAtTime = SystemClock.elapsedRealtime() + time;
        Intent intent = new Intent(this, TokenReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (alarmManager != null)
                alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        } else {
            if (alarmManager != null)
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        }
        return super.onStartCommand(i, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
