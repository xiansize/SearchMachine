package com.tcsoft.searchmachinary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.config.Constant;
import com.tcsoft.searchmachinary.utils.SpUtil;

public class ConfigActivity extends BaseActivity implements View.OnClickListener {

    private EditText etLibCode, etCityCode, etLibName, etNoticeDate, etNoticeContent, etShelfNoA, etCallNoA, etShelfDespA, etShelfNoB, etCallNoB, etShelfDespB;
    private SpUtil spUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        initView();
        init();
    }


    private void init() {
        spUtil = new SpUtil(this);

    }


    private void initView() {
        etLibCode = findViewById(R.id.et_libcode_config);
        etLibCode.setHint(Constant.libCode);
        Button btnLibCode = findViewById(R.id.btn_libcode_config);
        btnLibCode.setOnClickListener(this);

        etCityCode = findViewById(R.id.et_weather_city_code_config);
        etCityCode.setHint(Constant.cityCode);
        Button btnCityCode = findViewById(R.id.btn_weather_city_code_config);
        btnCityCode.setOnClickListener(this);

        etLibName = findViewById(R.id.et_lib_name_config);
        etLibName.setHint(Constant.libName);
        Button btnLibName = findViewById(R.id.btn_lib_name_config);
        btnLibName.setOnClickListener(this);

        etNoticeDate = findViewById(R.id.et_notice_date_config);
        etNoticeDate.setHint(Constant.noticeDate);
        Button btnNoticeDate = findViewById(R.id.btn_notice_date_config);
        btnNoticeDate.setOnClickListener(this);

        etNoticeContent = findViewById(R.id.et_notice_content_config);
        etNoticeContent.setHint(Constant.noticeContent);
        Button btnNoticeContent = findViewById(R.id.btn_notice_content_config);
        btnNoticeContent.setOnClickListener(this);

        etShelfNoA = findViewById(R.id.et_shelf_no_a_config);
        etShelfNoA.setHint(Constant.shelfNoA);
        Button btnShelfNoA = findViewById(R.id.btn_shelf_no_a_config);
        btnShelfNoA.setOnClickListener(this);

        etCallNoA = findViewById(R.id.et_callno_a_config);
        etCallNoA.setHint(Constant.callNoA);
        Button btnCallNoA = findViewById(R.id.btn_callno_a_config);
        btnCallNoA.setOnClickListener(this);

        etShelfDespA = findViewById(R.id.et_shelf_desp_a_config);
        etShelfDespA.setHint(Constant.shelfADesp);
        Button btnShelfDespA = findViewById(R.id.btn_shelf_desp_a_config);
        btnShelfDespA.setOnClickListener(this);

        etShelfNoB = findViewById(R.id.et_shelf_no_b_config);
        etShelfNoB.setHint(Constant.shelfNoB);
        Button btnShelfNoB = findViewById(R.id.btn_shelf_no_b_config);
        btnShelfNoB.setOnClickListener(this);

        etCallNoB = findViewById(R.id.et_callno_b_config);
        etCallNoB.setHint(Constant.callNoB);
        Button btnCallNoB = findViewById(R.id.btn_callno_b_config);
        btnCallNoB.setOnClickListener(this);


        etShelfDespB = findViewById(R.id.et_shelf_desp_b_config);
        etShelfDespB.setHint(Constant.shelfBDesp);
        Button btnShelfDespB = findViewById(R.id.btn_shelf_desp_b_config);
        btnShelfDespB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_libcode_config:
                if (etLibCode.getText().toString().trim().equals("")) return;
                Constant.libCode = etLibCode.getText().toString().trim();
                etLibCode.setText("");
                etLibCode.setHint(Constant.libCode);
                spUtil.putContent(Constant.KEY_LIBCODE, Constant.libCode);
                break;
            case R.id.btn_weather_city_code_config:
                if (etCityCode.getText().toString().trim().equals("")) return;
                Constant.cityCode = etCityCode.getText().toString().trim();
                etCityCode.setText("");
                etCityCode.setHint(Constant.cityCode);
                spUtil.putContent(Constant.KEY_CITYCODE, Constant.cityCode);
                break;
            case R.id.btn_lib_name_config:
                if (etLibName.getText().toString().trim().equals("")) return;
                Constant.libName = etLibName.getText().toString().trim();
                etLibName.setText("");
                etLibName.setHint(Constant.libName);
                spUtil.putContent(Constant.KEY_LIBNAME, Constant.libName);
                break;
            case R.id.btn_notice_date_config:
                if (etNoticeDate.getText().toString().trim().equals("")) return;
                Constant.noticeDate = etNoticeDate.getText().toString().trim();
                etNoticeDate.setText("");
                etNoticeDate.setHint(Constant.noticeDate);
                spUtil.putContent(Constant.KEY_NOTICE_DATE, Constant.noticeDate);
                break;
            case R.id.btn_notice_content_config:
                if (etNoticeContent.getText().toString().trim().equals("")) return;
                Constant.noticeContent = etNoticeContent.getText().toString().trim();
                etNoticeContent.setText("");
                etNoticeContent.setHint(Constant.noticeContent);
                spUtil.putContent(Constant.KEY_NOTICE_CONTENT, Constant.noticeContent);
                break;
            case R.id.btn_shelf_no_a_config:
                if (etShelfNoA.getText().toString().trim().equals("")) return;
                Constant.shelfNoA = etShelfNoA.getText().toString().trim();
                etShelfNoA.setText("");
                etShelfNoA.setHint(Constant.shelfNoA);
                spUtil.putContent(Constant.KEY_SHELFNO_A, Constant.shelfNoA);
                break;
            case R.id.btn_callno_a_config:
                if (etCallNoA.getText().toString().trim().equals("")) return;
                Constant.callNoA = etCallNoA.getText().toString().trim();
                etCallNoA.setText("");
                etCallNoA.setHint(Constant.callNoA);
                spUtil.putContent(Constant.KEY_CALLNO_A, Constant.callNoA);
                break;
            case R.id.btn_shelf_desp_a_config:
                if (etShelfDespA.getText().toString().trim().equals("")) return;
                Constant.shelfADesp = etShelfDespA.getText().toString().trim();
                etShelfDespA.setText("");
                etShelfDespA.setHint(Constant.shelfADesp);
                spUtil.putContent(Constant.KEY_SHELF_DESP_A, Constant.shelfADesp);
                break;
            case R.id.btn_shelf_no_b_config:
                if (etShelfNoB.getText().toString().trim().equals("")) return;
                Constant.shelfNoB = etShelfNoB.getText().toString().trim();
                etShelfNoB.setText("");
                etShelfNoB.setHint(Constant.shelfNoB);
                spUtil.putContent(Constant.KEY_SHELFNO_B, Constant.shelfNoB);
                break;
            case R.id.btn_callno_b_config:
                if (etCallNoB.getText().toString().trim().equals("")) return;
                Constant.callNoB = etCallNoB.getText().toString().trim();
                etCallNoB.setText("");
                etCallNoB.setHint(Constant.callNoB);
                spUtil.putContent(Constant.KEY_CALLNO_B, Constant.callNoB);
                break;
            case R.id.btn_shelf_desp_b_config:
                if (etShelfDespB.getText().toString().trim().equals("")) return;
                Constant.shelfBDesp = etShelfDespB.getText().toString().trim();
                etShelfDespB.setText("");
                etShelfDespB.setHint(Constant.shelfBDesp);
                spUtil.putContent(Constant.KEY_SHELF_DESP_B, Constant.shelfBDesp);
                break;
        }
    }
}
