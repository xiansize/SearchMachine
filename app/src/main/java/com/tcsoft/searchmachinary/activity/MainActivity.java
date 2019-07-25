package com.tcsoft.searchmachinary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.presenter.MainPresenter;
import com.tcsoft.searchmachinary.view.MainView;
import com.tcsoft.searchmachinary.widget.NotificationDialog;


import static com.tcsoft.searchmachinary.config.Constant.weather;

public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, MainView {

    private EditText etSearch;
    private MainPresenter mainPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.getWeatherInfo();
    }


    private void init() {
        mainPresenter = new MainPresenter(this, this);
        mainPresenter.attachView(this);
        mainPresenter.getFileContent();
    }


    private void initView() {
        etSearch = findViewById(R.id.et_search_main);

        TextView tvTitleBar = findViewById(R.id.tv_titlebar_device_name);
        tvTitleBar.setOnClickListener(this);

        Spinner spType = findViewById(R.id.sp_type_main);
        ArrayAdapter<String> selectedAdapter = new ArrayAdapter<>(this, R.layout.layout_spinner_selected, getResources().getStringArray(R.array.type));
        selectedAdapter.setDropDownViewResource(R.layout.layout_spinner_drop);
        spType.setAdapter(selectedAdapter);
        spType.setOnItemSelectedListener(this);

        TextView tvShelfNoA = findViewById(R.id.tv_shelfno_a_main);
        tvShelfNoA.setText(Constant.shelfNoA);
        TextView tvCallNoA = findViewById(R.id.tv_callno_a_main);
        tvCallNoA.setText(Constant.callNoA);
        TextView tvDespA = findViewById(R.id.tv_shelfno_a_desp_main);
        tvDespA.setText(Constant.shelfADesp);

        TextView tvShelfNoB = findViewById(R.id.tv_shelfno_b_main);
        tvShelfNoB.setText(Constant.shelfNoB);
        TextView tvCallNoB = findViewById(R.id.tv_callno_b_main);
        tvCallNoB.setText(Constant.callNoB);
        TextView tvDespB = findViewById(R.id.tv_shelfno_b_desp_main);
        tvDespB.setText(Constant.shelfBDesp);


        RelativeLayout rlNewBook = findViewById(R.id.rl_new_book_main);
        rlNewBook.setOnClickListener(this);
        RelativeLayout rlHotBook = findViewById(R.id.rl_hot_book_main);
        rlHotBook.setOnClickListener(this);
        RelativeLayout rlConsult = findViewById(R.id.rl_consult_main);
        rlConsult.setOnClickListener(this);
        RelativeLayout llSearch = findViewById(R.id.rl_btn_search_main);
        llSearch.setOnClickListener(this);
        TextView tvNotification = findViewById(R.id.tv_notification_main);
        ImageView ivVoice = findViewById(R.id.iv_voice_input_main);
        ivVoice.setOnClickListener(this);
        tvNotification.setText(Constant.noticeContent);
        tvNotification.setSelected(true);
        tvNotification.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_titlebar_device_name:
                switchActivity(this, ConfigActivity.class);
                break;
            case R.id.rl_new_book_main:
                switchActivity(this, SearchActivity.class, "TITLE", getString(R.string.new_book_title));
                break;
            case R.id.rl_hot_book_main:
                switchActivity(this, SearchActivity.class, "TITLE", getString(R.string.hot_book_title));
                break;
            case R.id.rl_consult_main:
                switchActivity(this, AdviseActivity.class, "TITLE", getString(R.string.advise_book_title));
                break;
            case R.id.iv_voice_input_main:
                mainPresenter.showTalkDialog();
                break;
            case R.id.tv_notification_main:
                NotificationDialog notificationDialog = new NotificationDialog(this, R.layout.layout_dialog_notification, Constant.noticeContent, Constant.libName, Constant.noticeDate);
                notificationDialog.show();
                break;
            case R.id.rl_btn_search_main:
                String search = etSearch.getText().toString().trim();
                if (!search.equals(""))
                    switchActivity(this, SearchActivity.class, "TITLE", getString(R.string.key_search) + search, "POSITION", mainPresenter.getSearchTypeIndex());
                break;
        }
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


    @Override
    public void voiceInput(String title) {
        etSearch.setText(title);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mainPresenter.setSearchTypeIndex(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        mainPresenter.setSearchTypeIndex(0);
    }


}
