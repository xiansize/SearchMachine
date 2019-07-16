package com.tcsoft.searchmachinary.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.utils.ToastUtil;
import com.tcsoft.searchmachinary.view.BaseView;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sendBroadcast(new Intent("android.intent.action.STATUSBAR_DISABLE"));
        super.onCreate(savedInstanceState);
    }


    public void switchActivity(Context context, Class<? extends Activity> aClass) {
        Intent intent = new Intent(context, aClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void switchActivity(Context context, Class<? extends Activity> aClass, String title, String content) {
        Intent intent = new Intent(context, aClass);
        intent.putExtra(title, content);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void switchActivity(Context context, Class<? extends Activity> aClass, String title, Book book) {
        Intent intent = new Intent(context, aClass);
        intent.putExtra(title, book);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public void switchActivity(Context context, Class<? extends Activity> aClass, String title, String content, String tag, int position) {
        Intent intent = new Intent(context, aClass);
        intent.putExtra(title, content);
        intent.putExtra(tag, position);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg);
    }


    @Override
    public void showWeather() {

    }
}
