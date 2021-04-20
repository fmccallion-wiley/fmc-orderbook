package com.mthree.orderbook;


import javax.persistence.*;

@Entity
public class Portfolio {
    @Id
    private String portfolioid;
    private String symbol;
    private String userid;
    private String qty;
    public String getPorfolioid() {
        return this.portfolioid;
    }
    public void setPorfolioid(String porfolioid) {
        this.portfolioid =porfolioid;
    }
    public String getSymbol() {
        return this.symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol =symbol;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userID) {
        this.userid =userID;
    }
    public String getQty() {
        return this.qty;
    }
    public void setQty(String qty) {
        this.qty =qty;
    }

}

