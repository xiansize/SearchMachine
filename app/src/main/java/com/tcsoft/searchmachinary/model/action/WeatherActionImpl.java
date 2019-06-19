package com.tcsoft.searchmachinary.model.action;


import android.util.Log;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Weather;
import com.tcsoft.searchmachinary.model.api.WeatherApi;
import com.tcsoft.searchmachinary.model.api.WeatherApiImpl;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskAction;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskListener;
import com.tcsoft.searchmachinary.model.listener.ActionListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Admin on 2019/6/18.
 */
public class WeatherActionImpl implements WeatherAction {

    private static final String TAG = "WeatherActionImpl";
    private WeatherApi weatherApi;

    public WeatherActionImpl() {
        this.weatherApi = new WeatherApiImpl();
    }

    @Override
    public void getWeatherInfo(final ActionListener<Weather> actionListener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return weatherApi.getWeatherInfo();
            }

            @Override
            public void result(String result) {
                if (result != null) {
                    Log.d(TAG, result);
                    try {
                        Weather weather = new Weather();
                        JSONObject jsonObject = new JSONObject(result);
                        weather.setCityName("IN " + jsonObject.getJSONObject("data").getString("city"));
                        weather.setTemperature(jsonObject.getJSONObject("data").getJSONObject("temp").getString("value") + "℃");
                        String weatherDesc = jsonObject.getJSONObject("data").getString("weather_desc");
                        weather.setWeather(weatherDesc);
                        switch (weatherDesc) {
                            case "晴":weather.setIcon(R.drawable.icon_weather_sunny);
                                break;
                            case "雨":weather.setIcon(R.drawable.icon_weather_rainy);
                                break;
                            case "多云":weather.setIcon(R.drawable.icon_weather_cloudy);
                                break;
                        }
                        actionListener.onSuccess(weather);


                    } catch (JSONException e) {
                        actionListener.onFailure(TAG, "连接异常");
                    }
                } else {
                    actionListener.onFailure(TAG, "连接失败");
                }
            }
        });
        asyncTaskAction.execute();
    }
}
