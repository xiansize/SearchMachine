package com.tcsoft.searchmachinary.bean;

import java.util.List;


public class Search {

    private int total;
    private List<Book> bList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Book> getbList() {
        return bList;
    }

    public void setbList(List<Book> bList) {
        this.bList = bList;
    }
}
