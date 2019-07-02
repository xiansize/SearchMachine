package com.tcsoft.searchmachinary.bean;

import java.io.Serializable;
import java.util.List;


public class Book implements Serializable{

    private int total;
    private String title;
    private String author;
    private String publisher;
    private String pubDate;
    private String callNo;
    private String isbn;
    private String price;
    private String page;
    private String size;
    private String summary;
    private String cover;
    private String recNo;

    private List<Holding> hList;

    //可借
    private int canLoanNum;
    //全部
    private int shelfNum;
    //总借
    private int loanNum;

    public int getCanLoanNum() {
        return canLoanNum;
    }

    public void setCanLoanNum(int canLoanNum) {
        this.canLoanNum = canLoanNum;
    }

    public int getShelfNum() {
        return shelfNum;
    }

    public void setShelfNum(int shelfNum) {
        this.shelfNum = shelfNum;
    }

    public int getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(int loanNum) {
        this.loanNum = loanNum;
    }




    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    public List<Holding> gethList() {
        return hList;
    }

    public void sethList(List<Holding> hList) {
        this.hList = hList;
    }

    public String getRecNo() {
        return recNo;
    }

    public void setRecNo(String recNo) {
        this.recNo = recNo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }





}
