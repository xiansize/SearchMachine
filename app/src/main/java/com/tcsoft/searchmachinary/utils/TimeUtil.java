package com.tcsoft.searchmachinary.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TimeUtil {

    public static String getTimeMin() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static String getTimeDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static String getAllTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static String getMonth() {
        Calendar calendar = Calendar.getInstance();
        int m = calendar.get(Calendar.MONTH);
        if (m > 9)
            return "" + m;
        else
            return "0" + m;
    }
}
