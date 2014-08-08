package com.dontah.repository;

import com.dontah.domain.Company;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Bruno on 19/07/14.
 */
@Repository
@Transactional
public class CompanyRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Collection<Company> getCompanyList() {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Company.class)
                .setCacheable(true)
                .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    public Company getCompany(String id) {
        return (Company) sessionFactory.getCurrentSession().get(Company.class, id);
    }

    public void saveOrUpdate(Company company) {
        sessionFactory.getCurrentSession().saveOrUpdate(company);
    }
}
