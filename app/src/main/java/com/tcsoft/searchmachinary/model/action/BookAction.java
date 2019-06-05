package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.model.listener.BookListener;

import java.util.List;

/**
 * Created by Admin on 2019/5/22.
 */

public interface BookAction {

    void newBook(BookListener<List<Book>> listener);

    void hotBook(BookListener<List<Book>> listener);

    void searchBook(String title,String page,BookListener<List<Book>> listener);

    void getBookDetails(String recNo,BookListener<List<Book>> listener);

    void consultant(String msg,BookListener<List<Book>> listener);

}
