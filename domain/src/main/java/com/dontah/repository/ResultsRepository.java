package com.dontah.repository;

import com.dontah.domain.ResultEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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

    public List<ResultEntity> list(int whereAmI, int offset, boolean getAll){
        Query criteria = sessionFactory.getCurrentSession()
                .createQuery("FROM ResultEntity order by position")
                .setFirstResult(whereAmI)
                .setMaxResults(offset)
                .setCacheable(true);

        for (Object o : criteria.list()) {
            ResultEntity resultEntity = (ResultEntity) o;
            if(getAll){
                resultEntity.setCompany(
                        companyRepository.getCompany(resultEntity.getCodBolsa()));
                continue;
            }

            resultEntity.setCompany(companyRepository.getCompanyOnly(resultEntity.getCodBolsa()));
        }
        return criteria.list();
    }

    /**
     *
     * @param codBolsas codBolsa splits by ,
     * @return
     */
    public List<ResultEntity> get(String codBolsas, boolean getAll){
        Query criteria = sessionFactory.getCurrentSession()
                .createQuery("FROM ResultEntity r where r.codBolsa in (:codBolsa)")
                .setParameterList("codBolsa", codBolsas.split(","))
                .setCacheable(true);

        for (Object o : criteria.list()) {
            ResultEntity resultEntity = (ResultEntity) o;
            if(getAll) {
                resultEntity.setCompany(
                        companyRepository.getCompany(resultEntity.getCodBolsa()));
                continue;
            }
            resultEntity.setCompany(companyRepository.getCompanyOnly(resultEntity.getCodBolsa()));
        }
        return criteria.list();
    }

    public List<ResultEntity> getOnly(String codBolsa){
        ResultEntity resultEntity = (ResultEntity)sessionFactory.getCurrentSession()
                .createCriteria(ResultEntity.class)
                .add(Restrictions.like("codBolsa", codBolsa).ignoreCase())
                .uniqueResult();
        if( resultEntity == null ){
            return new ArrayList<>();
        }
        resultEntity.setCompany(companyRepository.getCompanyOnly(resultEntity.getCodBolsa()));
        return Arrays.asList(resultEntity);
    }

    public void persist(List<ResultEntity> results) {

        int index = 0;
        for (ResultEntity resultEntity : results) {
            index++;
            List<ResultEntity> only = getOnly(resultEntity.getCodBolsa());
            ResultEntity before;
            if(only.size() == 0){
                before = new ResultEntity();
                before.setCodBolsa(resultEntity.getCodBolsa());
                before.setLastPosition(0L);
            }else {
                before = only.get(0);
                before.setLastPosition(before.getPosition());
            }

            before.setPosition((long) index);
            before.setLucro(resultEntity.getLucro());
            before.setRoe(resultEntity.getRoe());
            before.setDivida(resultEntity.getDivida());
            before.setFinalResult(resultEntity.getFinalResult());
            sessionFactory.getCurrentSession().saveOrUpdate(before);
        }
    }
}
