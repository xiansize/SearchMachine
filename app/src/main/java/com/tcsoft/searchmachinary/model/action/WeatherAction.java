package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.bean.Weather;
import com.tcsoft.searchmachinary.model.listener.ActionListener;

/**
 * Created by Admin on 2019/6/18.
 */

public interface WeatherAction {

    void getWeatherInfo(ActionListener<Weather> actionListener);
}
