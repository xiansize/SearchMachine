package com.tcsoft.searchmachinary.model.api;

/**
 * Created by Admin on 2019/5/22.
 */

public interface BookApi {

    String newBook();

    String hotBook();

    String searchBook(String title,String page);

    String getBookDetails(String recNo);

    String consultant(String msg);



}
