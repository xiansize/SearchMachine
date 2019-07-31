package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.bean.Consult;
import com.tcsoft.searchmachinary.bean.Search;
import com.tcsoft.searchmachinary.model.listener.ActionListener;
import java.util.Map;


public interface BookAction {

    void newBook(String page,ActionListener<Search> listener);

    void hotBook(ActionListener<Search> listener);

    void searchBook(String type,String title,String page,ActionListener<Search> listener);

    void getBookDetails(Book book,ActionListener<Book> listener);

    void getBookCover(String isbn, ActionListener<Map<String,String>> listener);


    void consultant(String msg,ActionListener<Consult> listener);

}
