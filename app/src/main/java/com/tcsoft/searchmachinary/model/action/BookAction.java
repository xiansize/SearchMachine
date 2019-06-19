package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.model.listener.ActionListener;

import java.util.List;

/**
 * Created by Admin on 2019/5/22.
 */

public interface BookAction {

    void newBook(ActionListener<List<Book>> listener);

    void hotBook(ActionListener<List<Book>> listener);

    void searchBook(String title,String page,ActionListener<List<Book>> listener);

    void getBookDetails(String recNo,ActionListener<List<Book>> listener);

    void consultant(String msg,ActionListener<List<Book>> listener);

}
