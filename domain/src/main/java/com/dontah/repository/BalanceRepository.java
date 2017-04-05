package com.dontah.repository;

import com.dontah.domain.Balance;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bruno on 25/07/14.
 */
public interface BalanceRepository extends JpaRepository<Balance, Balance.CompanyBalancePK>{
}
