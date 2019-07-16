package com.tcsoft.searchmachinary.presenter;


import android.content.Context;
import android.util.Log;

import com.tcsoft.searchmachinary.bean.Weather;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.action.FileAction;
import com.tcsoft.searchmachinary.model.action.FileActionImpl;
import com.tcsoft.searchmachinary.model.action.WeatherAction;
import com.tcsoft.searchmachinary.model.action.WeatherActionImpl;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.utils.VoiceUtil;
import com.tcsoft.searchmachinary.view.MainView;

import java.util.List;


public class MainPresenter extends BasePresenter<MainView> {

    private static final String TAG = "MainPresenter";

    private MainView mainView;
    private FileAction fileAction;
    private WeatherAction weatherAction;
    private VoiceUtil voiceUtil;
    private int searchTypeIndex;

    public int getSearchTypeIndex() {
        return searchTypeIndex;
    }

    public void setSearchTypeIndex(int searchTypeIndex) {
        this.searchTypeIndex = searchTypeIndex;
    }


    public MainPresenter(MainView mainView, Context context) {
        this.mainView = mainView;
        this.fileAction = new FileActionImpl();
        this.weatherAction = new WeatherActionImpl();
        this.voiceUtil = new VoiceUtil(context);
    }


    public void getFileContent() {
        fileAction.getFileContent(new ActionListener<List<String>>() {
            @Override
            public void onSuccess(List<String> list) {
                Constant.libCode = list.get(0);
                Constant.libName = list.get(1);
                Constant.noticeContent = list.get(2);
                Constant.noticeDate = list.get(3);
                Constant.cityCode = list.get(4);
            }

            @Override
            public void onFailure(String tag, String msg) {

            }
        });
    }


    public void getWeatherInfo() {
        if (!isViewAttached()) return;
        weatherAction.getWeatherInfo(new ActionListener<Weather>() {
            @Override
            public void onSuccess(Weather weather) {
                Constant.weather = weather;
                mainView.showWeather();
            }

            @Override
            public void onFailure(String tag, String msg) {
                Log.d(TAG, msg);
            }
        });
    }



    public void showTalkDialog() {
        voiceUtil.showTalkDialog(new VoiceUtil.InputVoiceListener() {
            @Override
            public void inputVoiceListener(String title) {
                mainView.voiceInput(title);
            }
        });
    }


}
