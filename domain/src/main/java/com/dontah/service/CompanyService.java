package com.dontah.service;

import com.dontah.domain.Company;
import com.dontah.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cin_bdonath on 05/04/2017.
 */
@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> findAll(){
        return companyRepository.findAll();
    }
}
