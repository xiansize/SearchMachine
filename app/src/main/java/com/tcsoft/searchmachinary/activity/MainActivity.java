package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.utils.TimeUtil;
import com.tcsoft.searchmachinary.view.NotificationDialog;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        TextView tvMin = findViewById(R.id.tv_clock_base);
        tvMin.setText(TimeUtil.getTimeMin());
        TextView tvDate = findViewById(R.id.tv_date_base);
        tvDate.setText(TimeUtil.getTimeDate());
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
                NotificationDialog notificationDialog = new NotificationDialog(this, R.layout.layout_dialog_notification);
                notificationDialog.show();
                break;
            case R.id.tv_btn_search_main:
                String search = etSearch.getText().toString().trim();
                if (!search.equals(""))
                    switchActivity(this, SearchActivity.class, "TITLE", getString(R.string.key_search) + search);
                break;
        }
    }
}
