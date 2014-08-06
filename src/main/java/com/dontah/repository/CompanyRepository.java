package com.dontah.repository;

import com.dontah.domain.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

/**
 * Created by Bruno on 19/07/14.
 */
@Repository
public class CompanyRepository {

    @PersistenceContext
    EntityManager session;

    public Collection<Company> getCompanyList() {
//        TypedQuery<Company> query = session.createQuery("select c from Company c", Company.class) //left join fetch c.balanceList
//                .setHint("org.hibernate.cacheable", true);
//        List<Company> resultList = query.getResultList();
//
//        for (Company company : resultList) {
//            List<Balance> balanceList = session.createQuery("select b from Balance b where b.pk.codBolsa = :codBolsa", Balance.class) //left join fetch c.balanceList
//                .setParameter("codBolsa", company.getCodBolsa())
//                .setHint("org.hibernate.cacheable", true).getResultList();
//            company.setBalanceList(ImmutableSet.copyOf(balanceList));
//        }

        TypedQuery<Company> query = session
                .createQuery("select distinct c from Company c left join fetch c.balanceList", Company.class);
//                  .setHint("org.hibernate.cacheable", true);
        return query.getResultList();
    }

    public Company getCompany(String id) {
        return (Company) session.find(Company.class, id);
    }

    @Transactional
    public void saveOrUpdate(Company company) {
        Company data = session.find(Company.class, company.getCodBolsa());
        if (data != null) {
            data.setNome(company.getNome());
        } else {
            session.persist(company);
        }
    }
}
