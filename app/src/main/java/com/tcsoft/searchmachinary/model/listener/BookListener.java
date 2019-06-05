package com.tcsoft.searchmachinary.model.listener;

/**
 * Created by Admin on 2019/5/22.
 */

public interface BookListener<T> {

    void onSuccess(T t);

    void onFailure(String tag,String msg);

}
