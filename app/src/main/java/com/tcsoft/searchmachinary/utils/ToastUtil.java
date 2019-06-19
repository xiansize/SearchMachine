package com.tcsoft.searchmachinary.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Admin on 2019/6/19.
 */

public class ToastUtil {

    public static void showToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

}
