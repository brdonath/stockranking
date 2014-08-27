package com.dontah.repository;

import com.dontah.domain.Company;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
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
        return (Company) sessionFactory.getCurrentSession()
                .get(Company.class, id);
    }

    public void saveOrUpdate(Company company) {
        sessionFactory.getCurrentSession().saveOrUpdate(company);
    }

    public Collection<Company> getCompanyNames(){
        Criteria cr = sessionFactory.getCurrentSession().createCriteria(Company.class)
                .setCacheable(true)
                .setProjection(Projections.projectionList()
                        .add(Projections.property("codBolsa"), "codBolsa")
                        .add(Projections.property("nome"), "nome"))
                .setResultTransformer(Transformers.aliasToBean(Company.class));

        return cr.list();
    }

    public Long getCount(){
        return ((Long) sessionFactory.getCurrentSession().createCriteria(Company.class).
                setCacheable(true)
                .setProjection(Projections.rowCount()).uniqueResult());
    }
}