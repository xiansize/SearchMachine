package com.tcsoft.searchmachinary.presenter;


import android.content.Context;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.model.action.BookAction;
import com.tcsoft.searchmachinary.model.action.BookActionImpl;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import com.tcsoft.searchmachinary.view.SearchView;

import java.util.List;
import java.util.Map;


public class SearchPresenter extends BasePresenter<SearchView> {

    private SearchView searchView;
    private BookAction bookAction;
    private Context context;
    private int page;

    public SearchPresenter(SearchView searchView, Context context) {
        this.searchView = searchView;
        this.bookAction = new BookActionImpl();
        this.context = context;
        this.page = 1;
    }

    public void showWeather() {
        searchView.showWeather();
    }


    public void getList(String title, boolean canLoan) {
        if (title.contains(context.getResources().getString(R.string.key_search))) {
            title = title.split(context.getResources().getString(R.string.key_search))[1];
            getSearchList(title, page, canLoan);
        } else if (title.equals(context.getResources().getString(R.string.new_book_title)))
            getNewBookList();
        else if (title.equals(context.getResources().getString(R.string.hot_book_title)))
            getHotBookList();
    }


    private void getSearchList(String title, int page, boolean canLoan) {
        if (!isViewAttached()) return;
        bookAction.searchBook(title, String.valueOf(page), canLoan, new ActionListener<List<Book>>() {
            @Override
            public void onSuccess(List<Book> list) {
                getBookCover(list);
            }

            @Override
            public void onFailure(String tag, String msg) {
                searchView.showToast(msg);
            }
        });
    }


    private void getBookCover(final List<Book> list) {
        StringBuilder isbn = new StringBuilder();
        for (Book book : list) {
            if (book.getIsbn() != null) isbn.append(book.getIsbn().replaceAll("-", "")).append(",");
        }
        bookAction.getBookCover(isbn.toString(), new ActionListener<Map<String, String>>() {
            @Override
            public void onSuccess(Map<String, String> map) {
                for (Book book : list) book.setCover(map.get(book.getIsbn().replaceAll("-", "")));
                searchView.showList(list);
            }

            @Override
            public void onFailure(String tag, String msg) {
                searchView.showList(list);
            }
        });
    }


    private void getNewBookList() {

    }


    private void getHotBookList() {

    }


}
