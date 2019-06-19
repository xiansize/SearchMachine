package com.tcsoft.searchmachinary.model.api;

import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.network.HttpUtil;

/**
 * Created by Admin on 2019/6/18.
 */

public class WeatherApiImpl implements WeatherApi{


    @Override
    public String getWeatherInfo() {
        return HttpUtil.httpGet("http://co.moji.com/api/weather2/weather?city="+ Constant.cityCode+"&lang=zh");
    }
}
