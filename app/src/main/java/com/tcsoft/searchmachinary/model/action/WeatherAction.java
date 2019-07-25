package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.bean.Weather;
import com.tcsoft.searchmachinary.model.listener.ActionListener;


public interface WeatherAction {

    void getWeatherInfo(ActionListener<Weather> actionListener);
}
