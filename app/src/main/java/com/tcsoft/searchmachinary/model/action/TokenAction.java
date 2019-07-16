package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.model.listener.ActionListener;

/**
 * Created by Admin on 2019/5/22.
 */

public interface TokenAction {

    void getToken(ActionListener<String> listener);
}
