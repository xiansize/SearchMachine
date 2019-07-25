package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.model.listener.ActionListener;



public interface TokenAction {

    void getToken(ActionListener<String> listener);
}
