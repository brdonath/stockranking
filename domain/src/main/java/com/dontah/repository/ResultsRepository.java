package com.dontah.repository;

import com.dontah.domain.ResultEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Bruno on 10/08/14.
 */
@Repository
@Transactional
public class ResultsRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    CompanyRepository companyRepository;

    public List<ResultEntity> list(int whereAmI, int offset){
        Query criteria = sessionFactory.getCurrentSession()
                .createQuery("FROM ResultEntity")
                .setFirstResult(whereAmI)
                .setMaxResults(offset)
                .setCacheable(true);

        for (Object resultEntity : criteria.list()) {
            ((ResultEntity) resultEntity).setCompany(
                    companyRepository.getCompany(((ResultEntity) resultEntity).getCodBolsa()));
        }
        return criteria.list();
    }

    public List<ResultEntity> get(String codBolsa){
        Query criteria = sessionFactory.getCurrentSession()
                .createQuery("FROM ResultEntity r where r.codBolsa in (:codBolsa)")
                .setParameterList("codBolsa", codBolsa.split(","))
                .setCacheable(true);

        for (Object resultEntity : criteria.list()) {
            ((ResultEntity) resultEntity).setCompany(
                    companyRepository.getCompany(((ResultEntity) resultEntity).getCodBolsa()));
        }
        return criteria.list();
    }


    public void persist(List<ResultEntity> results) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Result").executeUpdate();

        for (ResultEntity resultEntity : results) {
            sessionFactory.getCurrentSession().persist(resultEntity);
        }
    }
}
