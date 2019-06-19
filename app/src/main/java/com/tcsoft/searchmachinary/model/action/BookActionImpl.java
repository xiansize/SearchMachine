package com.tcsoft.searchmachinary.model.action;

import android.util.Log;

import com.tcsoft.searchmachinary.bean.Book;
import com.tcsoft.searchmachinary.model.api.BookApi;
import com.tcsoft.searchmachinary.model.api.BookApiImpl;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskAction;
import com.tcsoft.searchmachinary.model.asyn.AsyncTaskListener;
import com.tcsoft.searchmachinary.model.listener.ActionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2019/5/22.
 */
public class BookActionImpl implements BookAction {

    private static final String TAG = "BookActionImpl";
    private BookApi bookApi;

    public BookActionImpl() {
        bookApi = new BookApiImpl();
    }

    @Override
    public void newBook(ActionListener<List<Book>> listener) {

    }

    @Override
    public void hotBook(ActionListener<List<Book>> listener) {

    }

    @Override
    public void searchBook(final String title, final String page, final ActionListener<List<Book>> listener) {
        AsyncTaskAction asyncTaskAction = new AsyncTaskAction(new AsyncTaskListener<String>() {
            @Override
            public String background() {
                return bookApi.searchBook(title, page);
            }

            @Override
            public void result(String result) {
                if (result != null) {

                    Log.d(TAG, result);
                    List<Book> list = new ArrayList<>();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("pagelist");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Book book = new Book();
                            book.setTitle(jsonArray.getJSONObject(i).getString("title"));
                            book.setAuthor(jsonArray.getJSONObject(i).getString("author"));
                            book.setPublisher(jsonArray.getJSONObject(i).getString("publisher"));
                            book.setCallNo(jsonArray.getJSONObject(i).getString("callno"));
                            list.add(book);
                        }

                        listener.onSuccess(list);


                    } catch (JSONException e) {
                        listener.onFailure(TAG, "连接异常");
                    }

                } else {
                    listener.onFailure(TAG, "连接失败");
                }
            }
        });
        asyncTaskAction.execute();
    }


    @Override
    public void getBookDetails(String recNo, ActionListener<List<Book>> listener) {

    }


    @Override
    public void consultant(String msg, ActionListener<List<Book>> listener) {

    }
}
