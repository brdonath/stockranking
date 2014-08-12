package com.dontah.repository;

import com.dontah.domain.ResultEntity;
import com.dontah.processors.BoostAnalizer;
import com.dontah.processors.impl.DividaProcessor;
import com.dontah.processors.impl.LucroProcessor;
import com.dontah.processors.impl.ROEProcessor;
import com.dontah.service.Result;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public void persist(List<Result> results) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM Result").executeUpdate();
        List<ResultEntity> resultEntities = transform(results);
        for (ResultEntity resultEntity : resultEntities) {
            sessionFactory.getCurrentSession().persist(resultEntity);
        }
    }

    private List<ResultEntity> transform(List<Result> results) {
        ArrayList<ResultEntity> resultEntities = new ArrayList<>();
        for (Result result : results) {
            resultEntities.add(transform(result));
        }
        return resultEntities;
    }

    private ResultEntity transform(Result result) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCompany(result.getCompany());
        resultEntity.setFinalResult(result.getResult());
        resultEntity.setCodBolsa(result.getCompany().getCodBolsa());
        for (BoostAnalizer boostAnalizer : result.getBoostAnalizerList()) {
            if(boostAnalizer.getProcessor() instanceof LucroProcessor){
                resultEntity.setLucro(boostAnalizer.getResult());
            }
            if(boostAnalizer.getProcessor() instanceof DividaProcessor){
                resultEntity.setDivida(boostAnalizer.getResult());
            }
            if(boostAnalizer.getProcessor() instanceof ROEProcessor){
                resultEntity.setRoe(boostAnalizer.getResult());
            }
        }
        return resultEntity;
    }
}
