package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Weather;
import com.tcsoft.searchmachinary.utils.TimeUtil;

import static com.tcsoft.searchmachinary.config.Constant.weather;


public  class AdviseActivity extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advise);
        initView();
    }

    private void initView() {

        TextView tvTitle = findViewById(R.id.tv_key_base);
        tvTitle.setText(getIntent().getStringExtra("TITLE"));
        CheckBox cbCanloan = findViewById(R.id.cb_can_loan);
        cbCanloan.setVisibility(View.INVISIBLE);

        LinearLayout llBackpress = findViewById(R.id.ll_back_press_base);
        llBackpress.setOnClickListener(this);
        LinearLayout llQuestion = findViewById(R.id.ll_question_advise);
        llQuestion.setOnClickListener(this);
        RelativeLayout rlBtnSend = findViewById(R.id.rl_btn_send_advise);
        rlBtnSend.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back_press_base:
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.rl_btn_send_advise:

            case R.id.ll_question_advise:

                break;
        }
    }

    @Override
    public void showWeather() {
        TextView tvTemperature, tvWeather, tvCityName;
        ImageView ivWeather;
        tvTemperature = findViewById(R.id.tv_temp_base);
        tvWeather = findViewById(R.id.tv_weather_base);
        tvCityName = findViewById(R.id.tv_location_base);
        ivWeather = findViewById(R.id.iv_weather_base);
        tvTemperature.setText(weather.getTemperature());
        tvWeather.setText(weather.getWeather());
        tvCityName.setText(weather.getCityName());
        ivWeather.setImageResource(weather.getIcon());
    }
}
