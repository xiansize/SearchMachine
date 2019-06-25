package com.tcsoft.searchmachinary.presenter;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
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
import com.tcsoft.searchmachinary.view.MainView;
import java.util.List;


public class MainPresenter extends BasePresenter<MainView> {

    private static final String TAG = "MainPresenter";

    private MainView mainView;
    private Context context;
    private FileAction fileAction;
    private WeatherAction weatherAction;
    private TokenAction tokenAction;



    public MainPresenter(MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
        this.fileAction = new FileActionImpl();
        this.weatherAction = new WeatherActionImpl();
        this.tokenAction = new TokenActionImpl();
    }


    public void initFile() {
        FileUtil.initFile();
    }


    public void getNoticeContent() {
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
                mainView.showToast(msg);
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


    public void getToken(){
        tokenAction.getToken();
    }






    //动态获取权限
    public void getPermissionStorage() {
        String[] PERMISSION_STORAGE = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            PERMISSION_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((MainActivity) context, PERMISSION_STORAGE, 0x001);
            }
        }
    }


}
