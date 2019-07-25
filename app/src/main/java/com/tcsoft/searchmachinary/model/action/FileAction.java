package com.tcsoft.searchmachinary.model.action;

import com.tcsoft.searchmachinary.model.listener.ActionListener;

import java.util.List;



public interface FileAction {



    void getFileContent(ActionListener<List<String>> listener);


}
