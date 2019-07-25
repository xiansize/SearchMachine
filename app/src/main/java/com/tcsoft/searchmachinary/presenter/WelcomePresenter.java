package com.tcsoft.searchmachinary.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.tcsoft.searchmachinary.activity.WelcomeActivity;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.model.action.TokenAction;
import com.tcsoft.searchmachinary.model.action.TokenActionImpl;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.utils.FileUtil;
import com.tcsoft.searchmachinary.utils.SpUtil;
import com.tcsoft.searchmachinary.view.WelcomeView;


public class WelcomePresenter extends BasePresenter<WelcomeView> {

    private WelcomeView welcomeView;
    private Context context;
    private TokenAction tokenAction;
    private SpUtil spUtil;

    public WelcomePresenter(WelcomeView welcomeView, Context context) {
        this.tokenAction = new TokenActionImpl();
        this.context = context;
        this.welcomeView = welcomeView;
        spUtil = new SpUtil(context);
    }


    public void initSp() {
        Constant.libCode = spUtil.getContent(Constant.KEY_LIBCODE, Constant.libCode);
        Constant.cityCode = spUtil.getContent(Constant.KEY_CITYCODE, Constant.cityCode);

        Constant.libName = spUtil.getContent(Constant.KEY_LIBNAME, Constant.libName);
        Constant.noticeDate = spUtil.getContent(Constant.KEY_NOTICE_DATE, Constant.noticeDate);
        Constant.noticeContent = spUtil.getContent(Constant.KEY_NOTICE_CONTENT, Constant.noticeContent);
        Constant.shelfNoA = spUtil.getContent(Constant.KEY_SHELFNO_A, Constant.shelfNoA);
        Constant.callNoA = spUtil.getContent(Constant.KEY_CALLNO_A, Constant.callNoA);
        Constant.shelfADesp = spUtil.getContent(Constant.KEY_SHELF_DESP_A, Constant.shelfADesp);
        Constant.shelfNoB = spUtil.getContent(Constant.KEY_SHELFNO_B, Constant.shelfNoB);
        Constant.callNoB = spUtil.getContent(Constant.KEY_CALLNO_B, Constant.callNoB);
        Constant.shelfBDesp = spUtil.getContent(Constant.KEY_SHELF_DESP_B, Constant.shelfBDesp);

    }


    public void initFile() {
        FileUtil.initFile();
    }


    public void getToken() {
        tokenAction.getToken(new ActionListener<String>() {
            @Override
            public void onSuccess(String result) {
                welcomeView.initSuc();
            }

            @Override
            public void onFailure(String tag, String msg) {
                welcomeView.initFailed(msg);
            }
        });
    }


    public void getPermission() {
        String[] PERMISSION_STORAGE = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            PERMISSION_STORAGE = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(context, android.Manifest.permission.RECORD_AUDIO)) {
                ActivityCompat.requestPermissions((WelcomeActivity) context, PERMISSION_STORAGE, 0x001);
            } else {
                initFile();
                getToken();
            }
        } else {
            initFile();
            getToken();
        }
    }





}
