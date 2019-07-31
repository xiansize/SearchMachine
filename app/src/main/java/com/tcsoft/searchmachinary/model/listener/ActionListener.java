package com.tcsoft.searchmachinary.model.listener;


public interface ActionListener<T> {

    void onSuccess(T t);

    void onFailure(String tag,String msg);

}
