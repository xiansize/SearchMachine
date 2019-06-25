package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.adapter.ConsultAdapter;
import com.tcsoft.searchmachinary.bean.Consult;
import com.tcsoft.searchmachinary.presenter.AdvisePresenter;
import com.tcsoft.searchmachinary.view.AdviseView;

import java.util.ArrayList;
import java.util.List;

import static com.tcsoft.searchmachinary.config.Constant.weather;


public class AdviseActivity extends BaseActivity implements View.OnClickListener, AdviseView {

    private AdvisePresenter advisePresenter;
    private List<Consult> consultList;
    private ConsultAdapter consultAdapter;
    private RecyclerView rvConsult;
    private EditText etClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advise);
        initView();
        init();
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
        Button btnBtnSend = findViewById(R.id.btn_send_advise);
        btnBtnSend.setOnClickListener(this);

        etClient = findViewById(R.id.et_question_advise);
        consultList = new ArrayList<>();
        consultAdapter = new ConsultAdapter(consultList, this);
        rvConsult = findViewById(R.id.rv_advise);
        rvConsult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvConsult.setAdapter(consultAdapter);
    }


    private void init() {
        advisePresenter = new AdvisePresenter(this, this);
        advisePresenter.attachView(this);
        advisePresenter.showWeather();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back_press_base:
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case R.id.btn_send_advise:
                advisePresenter.sendMsg(etClient.getText().toString().trim());
                break;
            case R.id.ll_question_advise:
                advisePresenter.question();
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


    @Override
    public void sendMsg(Consult consult) {
        etClient.setText("");
        addListItem(consult);
    }

    @Override
    public void getMsg(Consult consult) {
        addListItem(consult);
    }


    @Override
    public void getQuestion(Consult consult) {
        addListItem(consult);
    }


    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    private void addListItem(Consult consult) {
        consultList.add(consult);
        consultAdapter.notifyItemInserted(consultList.size() - 1);
        rvConsult.scrollToPosition(consultList.size() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        advisePresenter.detachView();
    }
}
