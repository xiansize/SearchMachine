package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Weather;

import static com.tcsoft.searchmachinary.config.Constant.weather;

public class SearchActivity extends BaseActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        LinearLayout llBackpress = findViewById(R.id.ll_back_press_base);
        llBackpress.setOnClickListener(this);
        TextView tvTitle = findViewById(R.id.tv_key_base);
        tvTitle.setText(getIntent().getStringExtra("TITLE"));

        RecyclerView rvSearch = findViewById(R.id.rv_search);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);
        rvSearch.setLayoutManager(layoutManager);


    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back_press_base:
                finish();
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
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
