package com.dontah.repository;

import com.dontah.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bruno on 19/07/14.
 */
public interface CompanyRepository extends JpaRepository<Company,String> {
}
