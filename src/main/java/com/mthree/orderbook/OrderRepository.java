package com.mthree.orderbook;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {

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

    public ArrayList<Order> getOrders(){
        Query query = entityManager.createNativeQuery("select * from orderbook.`order`",Order.class);
        ArrayList<Order> resultList = (ArrayList<Order>) query.getResultList();
        return resultList;


    }

}