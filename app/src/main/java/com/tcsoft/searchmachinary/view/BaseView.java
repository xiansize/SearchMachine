package com.tcsoft.searchmachinary.view;

import android.content.Context;

import com.tcsoft.searchmachinary.bean.Weather;

/**
 * Created by Admin on 2019/6/18.
 */

public interface BaseView {

    void showToast(String msg);

    Context getContext();

    void showWeather();
}
