package com.dontah.repository;

import com.dontah.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bruno on 25/07/14.
 */
public interface BalanceRepository extends JpaRepository<Balance, Balance.CompanyBalancePK>{
}
