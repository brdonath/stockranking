package com.dontah.repository;

import com.dontah.domain.Balance;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Bruno on 25/07/14.
 */
@Repository
public class BalanceRepository {
    @PersistenceContext
    EntityManager session;

    public void persist(Balance balance){
        session.persist(balance);
    }
}
