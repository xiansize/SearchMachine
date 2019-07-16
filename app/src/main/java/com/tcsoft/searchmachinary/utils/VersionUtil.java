package com.tcsoft.searchmachinary.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by Admin on 2019/7/11.
 */

public class VersionUtil {

    public static String getVerName(Context context) {
        String verName = null;
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
