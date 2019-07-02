package com.tcsoft.searchmachinary.presenter;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;
import com.tcsoft.searchmachinary.activity.MainActivity;
import com.tcsoft.searchmachinary.bean.Weather;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.action.FileAction;
import com.tcsoft.searchmachinary.model.action.FileActionImpl;
import com.tcsoft.searchmachinary.model.action.TokenAction;
import com.tcsoft.searchmachinary.model.action.TokenActionImpl;
import com.tcsoft.searchmachinary.model.action.WeatherAction;
import com.tcsoft.searchmachinary.model.action.WeatherActionImpl;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.utils.FileUtil;
import com.tcsoft.searchmachinary.utils.VoiceUtil;
import com.tcsoft.searchmachinary.view.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainPresenter extends BasePresenter<MainView> {

    private static final String TAG = "MainPresenter";

    private MainView mainView;
    private Context context;
    private FileAction fileAction;
    private WeatherAction weatherAction;
    private TokenAction tokenAction;
    private VoiceUtil voiceUtil;

    public MainPresenter(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
        this.fileAction = new FileActionImpl();
        this.weatherAction = new WeatherActionImpl();
        this.tokenAction = new TokenActionImpl();
        this.voiceUtil = new VoiceUtil(context);
    }


    public void initFile() {
        FileUtil.initFile();
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


    public void getToken() {
        tokenAction.getToken();
    }


    public void getPermission() {
        String[] PERMISSION_STORAGE = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            PERMISSION_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context, android.Manifest.permission.RECORD_AUDIO)) {
                ActivityCompat.requestPermissions((MainActivity) context, PERMISSION_STORAGE, 0x001);
            }
        }
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
