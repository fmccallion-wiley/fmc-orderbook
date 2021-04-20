package com.mthree.orderbook;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    private String orderid;
    private String symbol;
    private String userid;
    private String type;
    private String qty;
    private String price;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String id) {
        this.orderid = id;
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
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price) {
        this.price =price;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type =type;
    }

}
