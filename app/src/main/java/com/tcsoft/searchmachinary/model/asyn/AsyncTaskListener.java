package com.tcsoft.searchmachinary.model.asyn;

/**
 * Created by Admin on 2019/4/17.
 */

public interface AsyncTaskListener<T> {


    String background();


    void result(T t);


}
