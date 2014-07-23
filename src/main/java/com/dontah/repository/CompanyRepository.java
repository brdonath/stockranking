package com.dontah.repository;

import com.dontah.db.SessionDAO;
import com.dontah.domain.Company;

import java.util.List;

/**
 * Created by Bruno on 19/07/14.
 */
public class CompanyRepository {

    public static List<Company> getCompanyList(){
        return SessionDAO.getSession().createCriteria(Company.class).list();
    }

    public static Company getCompany(String id){
        return (Company) SessionDAO.getSession().get(Company.class,id);
    }
}
