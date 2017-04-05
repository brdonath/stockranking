package com.dontah.controller;

import com.dontah.domain.Company;
import com.dontah.domain.ResultEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;

/**
 * Created by Bruno on 06/09/14.
 */
@Controller
@RequestMapping("/data/" )
public interface IRankingAPIController {

    /*static final String RESULT_LIST = "/result/list";
    static final String RESULT_COD_BOLSA= "/result/{codBolsa}";
    static final String RESULT_COD_BOLSA_ONLY = "/result/{codBolsa}/only";
    static final String RESULT_COMPANY_LIST = "/result/company/list";
    static final String RESULT_SEARCH = "/result/search/";

    @RequestMapping(RESULT_LIST)
    @ResponseBody
    List<ResultEntity> getData(int whereAmI,int offset);

    @RequestMapping(RESULT_COD_BOLSA)
    @ResponseBody
    List<ResultEntity> getResultDetails(String codBolsa);

    @RequestMapping(RESULT_COD_BOLSA_ONLY)
    @ResponseBody
    List<ResultEntity> getResultDetailsOnly(String codBolsa);

    @RequestMapping(RESULT_COMPANY_LIST)
    @ResponseBody
    Collection<Company> getCompanyNames();

    @RequestMapping(RESULT_SEARCH)
    @ResponseBody
    List<ResultEntity> search(String query);
    */
}
