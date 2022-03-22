package com.mthree.orderbook;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class OrderRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(Order order) {
        entityManager.createNativeQuery("insert into orderbook.`order` (orderid, symbol, type, price, qty, userid) VALUES (?,?,?,?,?,?)")
                .setParameter(1, order.getOrderid())
                .setParameter(2, order.getSymbol())
                .setParameter(3, order.getType())
                .setParameter(4, order.getPrice())
                .setParameter(5, order.getQty())
                .setParameter(6, order.getUserid())
                .executeUpdate();
    }

    public ArrayList<Order> getOrders(Boolean adminFlag){
        Query query = entityManager.createNativeQuery("select * from orderbook.`order`",Order.class);
        ArrayList<Order> resultList = (ArrayList<Order>) query.getResultList();

        //not sure what this is doing, the Connection object is declared and initialized but never used
        if(adminFlag){
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection(url,username,password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
    
    public ArrayList<Order> getLimitedOrders(int page, int perPage){
        int offset = page * perPage;
        Query query = entityManager.createNativeQuery("select * from orderbook.`order` limit ? offset ?",Order.class)
                                   .setParameter(1, perPage)
                                   .setParameter(2, offset);
        ArrayList<Order> resultList = (ArrayList<Order>) query.getResultList();
        return resultList;
    }
    
    public int getOrderCount(){
        Query query = entityManager.createNativeQuery("select Count(orderid) from `order`");
        int orderCount = ((BigInteger) query.getSingleResult()).intValue();
        return orderCount;
    }
}