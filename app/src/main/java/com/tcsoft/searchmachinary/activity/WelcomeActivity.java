package com.tcsoft.searchmachinary.activity;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.presenter.WelcomePresenter;
import com.tcsoft.searchmachinary.view.WelcomeView;


public class WelcomeActivity extends BaseActivity implements WelcomeView {

    private WelcomePresenter welcomePresenter;
    private TextView tvTips;
    private static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        init();

    }



    private void initView() {
        tvTips = findViewById(R.id.tv_tips_welcome);
    }


    private void init() {
        welcomePresenter = new WelcomePresenter(this, this);
        welcomePresenter.attachView(this);
        welcomePresenter.getPermission();
        welcomePresenter.initSp();
    }


    @Override
    public void initSuc() {
        switchActivity(this, MainActivity.class);
        finish();
    }

    @Override
    public void initFailed(String msg) {
        tvTips.setText(msg);
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x001)
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED && i == 2) {
                    welcomePresenter.initFile();
                    welcomePresenter.getToken();
                }
            }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        welcomePresenter.detachView();
    }
}
