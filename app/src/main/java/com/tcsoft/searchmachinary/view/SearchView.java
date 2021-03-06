package com.tcsoft.searchmachinary.view;

import com.tcsoft.searchmachinary.bean.Book;

import java.util.List;

/**
 * Created by Admin on 2019/6/19.
 */

public interface SearchView extends BaseView {

    void showList(List<Book> list);

    void showNoResult(String key);

    void showEnd();

    void showHotBook(List<Book> list);

    void showNewBook(List<Book> list);
}
