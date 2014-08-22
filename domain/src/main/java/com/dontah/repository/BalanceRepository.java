package com.dontah.repository;

import com.dontah.domain.Balance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bruno on 25/07/14.
 */
@Repository
@Transactional
public class BalanceRepository {

    @Autowired
    SessionFactory sessionFactory;

    public void saveOrUpdate(Balance balance){
        sessionFactory.getCurrentSession().saveOrUpdate(balance);
    }
}
