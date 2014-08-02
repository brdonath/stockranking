package com.dontah.repository;

import com.dontah.domain.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Bruno on 19/07/14.
 */
@Repository
public class CompanyRepository {

    @PersistenceContext
    EntityManager session;

    public List<Company> getCompanyList(){
        return session.createQuery("select c from com.dontah.domain.Company c",Company.class ).getResultList();

    }

    public Company getCompany(String id){
        return (Company) session.find(Company.class,id);
    }

    @Transactional
    public void saveOrUpdate(Company company){
        Company data = session.find(Company.class, company.getCodBolsa());
        if(data != null){
            data.setNome(company.getNome());
        }else {
            session.persist(company);
        }
    }
}
