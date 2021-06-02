package com.mthree.orderbook;
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
import java.util.List;

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

}