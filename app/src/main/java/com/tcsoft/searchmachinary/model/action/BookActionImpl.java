package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.model.api.BookApi;
import com.tcsoft.searchmachinary.model.api.BookApiImpl;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskAction;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskListener;
import com.tcsoft.searchmachinary.model.listener.BookListener;

import java.util.List;

/**
 * Created by Admin on 2019/5/22.
 */
public class BookActionImpl implements BookAction{

    private static final String TAG = "BookActionImpl";
    private BookApi bookApi;

    public BookActionImpl() {
        bookApi = new BookApiImpl();
    }

    @Override
    public void newBook(BookListener<List<Book>> listener) {

    }

    @Override
    public void hotBook(BookListener<List<Book>> listener) {

    }

    @Override
    public void searchBook(final String title, final String page, final BookListener<List<Book>> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.searchBook(title,page);
            }

            @Override
            public void result(String result) {
                if(result != null){

                }else{
                    listener.onFailure(TAG,"连接失败");
                }
            }
        });
        asyncTaskAction.execute();
    }

    @Override
    public void getBookDetails(String recNo, BookListener<List<Book>> listener) {

    }

    @Override
    public void consultant(String msg, BookListener<List<Book>> listener) {

    }
}
