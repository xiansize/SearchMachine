package com.tcsoft.searchmachinary.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.adapter.HoldingAdapter;
import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.presenter.BookPresenter;
import com.tcsoft.searchmachinary.view.BookView;

import static com.tcsoft.searchmachinary.config.Constant.weather;

public class BookActivity extends BaseActivity implements View.OnClickListener, BookView {

    private BookPresenter bookPresenter;
    private TextView tvBookTitle, tvAuthor, tvPublisher, tvIsbn, tvPrice, tvShape, tvSummary;
    private TextView tvCanLoan, tvShelfNum, tvReadingNum;
    private ImageView ivCover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();
        init();
    }


    private void initView() {
        LinearLayout llBackpress = findViewById(R.id.ll_back_press_base);
        llBackpress.setOnClickListener(this);
        TextView tvTitle = findViewById(R.id.tv_key_base);
        tvTitle.setText(R.string.book_details);


        ivCover = findViewById(R.id.iv_cover_book_details);
        tvBookTitle = findViewById(R.id.tv_title_book_details);
        tvAuthor = findViewById(R.id.tv_author_book_details);
        tvPublisher = findViewById(R.id.tv_publisher_book_details);
        tvIsbn = findViewById(R.id.tv_isbn_book_details);
        tvPrice = findViewById(R.id.tv_price_book_details);
        tvShape = findViewById(R.id.tv_shape_book_details);
        tvSummary = findViewById(R.id.tv_shape_book_summary);

        tvCanLoan = findViewById(R.id.tv_canloan_book_details);
        tvShelfNum = findViewById(R.id.tv_shelf_num_book_details);
        tvReadingNum = findViewById(R.id.tv_reading_num_book_details);
    }


    private void init() {
        Book book = (Book) getIntent().getSerializableExtra("Book");
        bookPresenter = new BookPresenter(this);
        bookPresenter.attachView(this);
        bookPresenter.showWeather();
        bookPresenter.getBookHolding(book);
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
    public void showBookDetails(Book book) {
        Glide.with(this).load(book.getCover()).placeholder(R.drawable.icon_nocover).error(R.drawable.icon_nocover).into(ivCover);
        tvBookTitle.setText(book.getTitle());
        tvAuthor.setText(String.format(getResources().getString(R.string.book_author), book.getAuthor()));
        tvPublisher.setText(String.format(getResources().getString(R.string.book_publisher), book.getPublisher(), book.getPubDate()));
        tvIsbn.setText(String.format(getResources().getString(R.string.book_isbn), book.getIsbn()));
        tvPrice.setText(String.format(getResources().getString(R.string.book_price), book.getPrice()));
        tvShape.setText(String.format(getResources().getString(R.string.book_shape), book.getPage(), book.getSize()));
        tvSummary.setText(String.format(getResources().getString(R.string.book_summary), book.getSummary()));

        tvCanLoan.setText(String.format(getResources().getString(R.string.book_canloan), book.getCanLoanNum() - 1));
        tvShelfNum.setText(String.format(getResources().getString(R.string.book_shelf_num), book.getShelfNum()));
        tvReadingNum.setText(String.format(getResources().getString(R.string.book_reading_num), book.getLoanNum()));


        RecyclerView rvHolding = findViewById(R.id.rv_holding_book_details);
        rvHolding.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        HoldingAdapter holdingAdapter = new HoldingAdapter(book.gethList(), this);
        rvHolding.setAdapter(holdingAdapter);
    }


    @Override
    public void showBookHolding() {
        LinearLayout llBookHolding = findViewById(R.id.ll_book_holding_item);
        llBookHolding.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bookPresenter.detachView();
    }
}
