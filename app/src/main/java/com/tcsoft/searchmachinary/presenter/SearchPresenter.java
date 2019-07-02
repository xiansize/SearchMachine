package com.tcsoft.searchmachinary.presenter;


import android.content.Context;
import android.util.Log;

import com.tcsoft.searchmachinary.R;
import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.bean.Search;
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
    private String searchTitle;

    private String[] tList = {"title", "author", "isbn"};
    private int lTotal;
    private int pTotal;
    private int mPage = 1;


    public SearchPresenter(SearchView searchView, Context context) {
        this.searchView = searchView;
        this.bookAction = new BookActionImpl();
        this.context = context;
    }


    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }


    public void showWeather() {
        searchView.showWeather();
    }


    public void getList(boolean canLoan) {
        if (searchTitle.contains(context.getResources().getString(R.string.key_search))) {
            String sTitle = searchTitle.split(context.getResources().getString(R.string.key_search))[1];
            getSearchList(tList[0], sTitle, mPage, canLoan);
        } else if (searchTitle.equals(context.getResources().getString(R.string.new_book_title)))
            getNewBookList(mPage);
        else if (searchTitle.equals(context.getResources().getString(R.string.hot_book_title)))
            getHotBookList();
    }


    private void getNewBookList(int page) {
        bookAction.newBook(String.valueOf(page), new ActionListener<Search>() {
            @Override
            public void onSuccess(Search search) {
                pTotal = search.getTotal();
                if (search.getbList().size() > 0) {
                    if (lTotal < pTotal) {
                        mPage++;
                        lTotal += search.getbList().size();
                        getBookCover(search.getbList());
                    } else
                        searchView.showEnd();
                } else
                    searchView.showEnd();
            }

            @Override
            public void onFailure(String tag, String msg) {

            }
        });

    }


    private void getHotBookList() {
        bookAction.hotBook(new ActionListener<Search>() {
            @Override
            public void onSuccess(Search search) {
                if (pTotal == 99)
                    searchView.showEnd();
                else {
                    pTotal = search.getTotal();
                    getBookCover(search.getbList());
                }
            }

            @Override
            public void onFailure(String tag, String msg) {

            }
        });
    }


    private void getSearchList(String type, final String title, final int page, boolean canLoan) {
        if (!isViewAttached()) return;
        bookAction.searchBook(type, title, String.valueOf(page), canLoan, new ActionListener<Search>() {
            @Override
            public void onSuccess(Search search) {
                pTotal = search.getTotal();
                if (search.getbList().size() > 0) {
                    if (lTotal < pTotal) {
                        mPage++;
                        lTotal += search.getbList().size();
                        getBookCover(search.getbList());

                    } else
                        searchView.showEnd();
                } else
                    searchView.showNoResult(String.format(context.getResources().getString(R.string.search_no_result), title));
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
                if (searchTitle.contains(context.getResources().getString(R.string.key_search))) {
                    searchView.showList(list);
                    if (mPage == 2 && lTotal == pTotal)
                        searchView.showEnd();
                } else if (searchTitle.equals(context.getResources().getString(R.string.new_book_title)))
                    searchView.showNewBook(list);
                else if (searchTitle.equals(context.getResources().getString(R.string.hot_book_title)))
                    searchView.showHotBook(list);
            }

            @Override
            public void onFailure(String tag, String msg) {
                if (searchTitle.contains(context.getResources().getString(R.string.key_search))) {
                    searchView.showList(list);
                    if (mPage == 2 && lTotal == pTotal)
                        searchView.showEnd();
                } else if (searchTitle.equals(context.getResources().getString(R.string.new_book_title)))
                    searchView.showNewBook(list);
                else if (searchTitle.equals(context.getResources().getString(R.string.hot_book_title)))
                    searchView.showHotBook(list);
            }
        });
    }


}
