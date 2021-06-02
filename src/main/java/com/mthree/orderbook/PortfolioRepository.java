package com.mthree.orderbook;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PortfolioRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public ArrayList<Portfolio> getPortfolio(String userid, Boolean adminflag){
        String q = "select * from orderbook.`portfolio` as p where p.userid=?";
        Query query = entityManager.createNativeQuery(q,Portfolio.class).setParameter(1, userid);
        ArrayList<Portfolio> resultList = (ArrayList<Portfolio>) query.getResultList();
        return resultList;


    }

}