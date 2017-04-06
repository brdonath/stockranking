package com.dontah.controller.impl;

import com.dontah.controller.IRankingAPIController;
import com.dontah.domain.Company;
import com.dontah.domain.ResultEntity;
import com.dontah.service.CompanyService;
import com.dontah.service.ResultService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bruno on 04/09/14.
 */
@Controller
public class RankingAPIController implements IRankingAPIController {

    @Autowired
    private ResultService resultService;
    @Autowired
    private CompanyService companyService;

    @Override
    public List<ResultEntity> getData(
            @RequestParam(defaultValue = "0") int whereAmI,
            @RequestParam(defaultValue = "30")  int offset){
        List<ResultEntity> all = resultService.findAll(whereAmI / offset, offset);
        return all;
    }

    @Override
    public List<ResultEntity> getResultDetails(
            @PathVariable String codBolsa){
        if(StringUtils.isBlank(codBolsa))
            return Collections.emptyList();

        return resultService.findAll(Arrays.asList(codBolsa.split(",")));
    }

    @Override
    public List<ResultEntity> getResultDetailsOnly(
            @PathVariable String codBolsa) {
        return Collections.singletonList(resultService.findByCodBolsa(codBolsa));
    }

    @Override
    public Collection<Company> getCompanyNames() {
        return companyService.findAll();
    }

    @Override
    public List<ResultEntity> search(
            @RequestParam(value="q") String query) {
        return resultService.findAll(Arrays.asList(query.split(",")));
    }
}
