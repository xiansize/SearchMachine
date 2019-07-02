package com.tcsoft.searchmachinary.bean;


public class Holding {

    private String shelf;
    private String local;
    private String status;
    private String barcode;
    private int totalLoanNum;

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(int status) {
        if (status == 2)
            this.status = "在馆";
        else
            this.status = "不在馆";
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getTotalLoanNum() {
        return totalLoanNum;
    }

    public void setTotalLoanNum(int totalLoanNum) {
        this.totalLoanNum = totalLoanNum;
    }
}
