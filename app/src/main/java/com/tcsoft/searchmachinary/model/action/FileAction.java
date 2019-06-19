package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.model.listener.ActionListener;

import java.util.List;

/**
 * Created by Admin on 2019/6/19.
 */

public interface FileAction {



    void getFileContent(ActionListener<List<String>> listener);


}
