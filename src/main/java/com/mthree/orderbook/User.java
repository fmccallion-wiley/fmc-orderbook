package com.mthree.orderbook;

import javax.persistence.*;

@Entity
public class User {
    @Id
    private String userid;
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userID) {
        this.userid =userID;
    }


}
