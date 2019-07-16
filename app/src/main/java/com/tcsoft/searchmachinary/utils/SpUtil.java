package com.tcsoft.searchmachinary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Admin on 2019/7/10.
 */

public class SpUtil {

    private SharedPreferences sharedPreferences;

    public SpUtil(Context context) {
        this.sharedPreferences = context.getSharedPreferences("CONFIG", Context.MODE_PRIVATE);
    }

    public void putContent(String key, String content) {
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, content);
        editor.apply();
    }

    public String getContent(String key, String def) {
        return sharedPreferences.getString(key, def);
    }


}
