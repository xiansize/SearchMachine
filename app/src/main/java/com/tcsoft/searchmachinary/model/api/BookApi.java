package com.tcsoft.searchmachinary.model.api;

/**
 * Created by Admin on 2019/5/22.
 */

public interface BookApi {

    String newBook(String page);

    String hotBook();

    String searchBook(String type,String title,String page);

    String getBookDetails(String recNo);

    String getBookCover(String isbn);


    String consultant(String msg);



}
