package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.adapter.SearchBookAdapter;
import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.presenter.SearchPresenter;
import com.tcsoft.searchmachinary.view.SearchView;

import java.util.ArrayList;
import java.util.List;

import static com.tcsoft.searchmachinary.config.Constant.weather;

public class SearchActivity extends BaseActivity implements View.OnClickListener, SearchView, SearchBookAdapter.ItemOnClickListener {


    private SearchPresenter searchPresenter;
    private SearchBookAdapter searchBookAdapter;
    private List<Book> searchList;
    private TextView tvTitle;
    private CheckBox cbLoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        init();
    }


    private void initView() {
        LinearLayout llBackpress = findViewById(R.id.ll_back_press_base);
        llBackpress.setOnClickListener(this);
        cbLoan = findViewById(R.id.cb_can_loan);
        cbLoan.setVisibility(View.INVISIBLE);
        tvTitle = findViewById(R.id.tv_key_base);
        tvTitle.setText(getIntent().getStringExtra("TITLE"));
        RecyclerView rvSearch = findViewById(R.id.rv_search);
        if (getIntent().getStringExtra("TITLE").equals(getResources().getString(R.string.hot_book_title)))
            rvSearch.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        else
            rvSearch.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false));
        searchList = new ArrayList<>();
        searchBookAdapter = new SearchBookAdapter(searchList, this);
        searchBookAdapter.setItemOnClickListener(this);
        rvSearch.setAdapter(searchBookAdapter);
    }


    private void init() {
        searchPresenter = new SearchPresenter(this, this);
        searchPresenter.attachView(this);
        searchPresenter.showWeather();
        searchPresenter.getList(tvTitle.getText().toString().trim(), cbLoan.isChecked());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back_press_base:
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
    public void showList(List<Book> list) {
        searchList.addAll(list);
        searchBookAdapter.notifyDataSetChanged();
    }


    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void onItemClick(View view, int position) {
        switchActivity(this, BookActivity.class, "Book", searchList.get(position));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchPresenter.detachView();
    }
}
