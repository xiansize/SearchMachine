package com.tcsoft.searchmachinary.view;

import com.tcsoft.searchmachinary.bean.Book;


public interface BookView extends BaseView{

    void showBookDetails(Book book);

    void showBookHolding();



}
