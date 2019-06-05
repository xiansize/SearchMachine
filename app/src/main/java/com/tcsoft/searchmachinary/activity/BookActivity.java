package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tcsoft.searchmachinary.R;

public class BookActivity extends BaseActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();
    }

    private void initView() {
        LinearLayout llBackpress = findViewById(R.id.ll_back_press_base);
        llBackpress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back_press_base:
                finish();
                break;
        }
    }
}
