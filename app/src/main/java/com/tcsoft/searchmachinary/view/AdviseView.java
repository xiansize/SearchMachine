package com.tcsoft.searchmachinary.view;

import com.tcsoft.searchmachinary.bean.Consult;


public interface AdviseView extends BaseView {

    void sendMsg(Consult consult);

    void getMsg(Consult consult);

    void getQuestion(Consult consult);

}
