package com.tcsoft.searchmachinary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tcsoft.searchmachinary.service.TokenService;


public class TokenReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent i) {
        Intent intent = new Intent(context, TokenService.class);
        context.startService(intent);

    }
}
