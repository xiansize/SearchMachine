package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.presenter.MainPresenter;
import com.tcsoft.searchmachinary.view.MainView;
import com.tcsoft.searchmachinary.widget.NotificationDialog;
import static com.tcsoft.searchmachinary.config.Constant.weather;

public class MainActivity extends BaseActivity implements View.OnClickListener, MainView {

    private EditText etSearch;
    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }


    private void init() {
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.attachView(this);
        mainPresenter.getPermissionStorage();
        mainPresenter.initFile();
        mainPresenter.getNoticeContent();
        mainPresenter.getWeatherInfo();
    }


    private void initView() {
        etSearch = findViewById(R.id.et_search_main);

        RelativeLayout rlNewBook = findViewById(R.id.rl_new_book_main);
        rlNewBook.setOnClickListener(this);
        RelativeLayout rlHotBook = findViewById(R.id.rl_hot_book_main);
        rlHotBook.setOnClickListener(this);
        RelativeLayout rlConsult = findViewById(R.id.rl_consult_main);
        rlConsult.setOnClickListener(this);
        TextView tvSearch = findViewById(R.id.tv_btn_search_main);
        tvSearch.setOnClickListener(this);
        TextView tvNotification = findViewById(R.id.tv_notification_main);
        tvNotification.setText(Constant.noticeContent);
        tvNotification.setSelected(true);
        tvNotification.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_new_book_main:
                switchActivity(this, SearchActivity.class, "TITLE", getString(R.string.new_book_title));
                break;
            case R.id.rl_hot_book_main:
                switchActivity(this, SearchActivity.class, "TITLE", getString(R.string.hot_book_title));
                break;
            case R.id.rl_consult_main:
                switchActivity(this, AdviseActivity.class, "TITLE", getString(R.string.advise_book_title));
                break;
            case R.id.tv_notification_main:
                NotificationDialog notificationDialog = new NotificationDialog(this, R.layout.layout_dialog_notification, Constant.noticeContent, Constant.libName, Constant.noticeDate);
                notificationDialog.show();
                break;
            case R.id.tv_btn_search_main:
                String search = etSearch.getText().toString().trim();
                if (!search.equals(""))
                    switchActivity(this, SearchActivity.class, "TITLE", getString(R.string.key_search) + search);
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
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
