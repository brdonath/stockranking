package com.dontah.controller.impl;

import com.dontah.controller.IRankingAPIController;
import com.dontah.domain.Company;
import com.dontah.domain.ResultEntity;
import com.dontah.repository.CompanyRepository;
import com.dontah.repository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * Created by Bruno on 04/09/14.
 */
@Controller
public class RankingAPIController implements IRankingAPIController {

    @Autowired ResultsRepository resultsRepository;
    @Autowired CompanyRepository companyRepository;

    @Override
    public List<ResultEntity> getData(
            @RequestParam(defaultValue = "0") int whereAmI,
            @RequestParam(defaultValue = "30")  int offset){
        return resultsRepository.list(whereAmI,offset, false);
    }

    @Override
    public List<ResultEntity> getResultDetails(
            @PathVariable String codBolsa){
        return resultsRepository.get(codBolsa, true);
    }

    @Override
    public List<ResultEntity> getResultDetailsOnly(
            @PathVariable String codBolsa) {
        return resultsRepository.getOnly(codBolsa);
    }

    @Override
    public Collection<Company> getCompanyNames() {
        return companyRepository.getCompanyNames();
    }

    @Override
    public List<ResultEntity> search(
            @RequestParam(value="q") String query) {
        return resultsRepository.get(query, false);
    }
}
