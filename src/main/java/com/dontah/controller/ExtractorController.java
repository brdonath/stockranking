package com.dontah.controller;

import com.dontah.domain.Company;
import com.dontah.processors.BoostAnalizer;
import com.dontah.processors.impl.DividaProcessor;
import com.dontah.processors.impl.LucroProcessor;
import com.dontah.processors.impl.ROEProcessor;
import com.dontah.repository.CompanyRepository;
import com.dontah.repository.ResultsRepository;
import com.dontah.service.Result;
import com.dontah.service.extractor.StockDataExtractor;
import com.dontah.service.extractor.StockNamesExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bruno on 16/08/14.
 */
//@RequestMapping("/ext")
//@Controller
public class ExtractorController {

    @Autowired CompanyRepository companyRepository;
    @Autowired ResultsRepository resultsRepository;
    @Autowired DividaProcessor dividaProcessor;
    @Autowired ROEProcessor roeProcessor;
    @Autowired LucroProcessor lucroProcessor;
    @Autowired StockDataExtractor stockDataExtractor;
    @Autowired StockNamesExtractor stockNamesExtractor;

    @RequestMapping("/calc")
    @ResponseBody
    public String calcula() throws Exception {

        stockNamesExtractor.extract();
        stockDataExtractor.extract();

        List<Result> results = rank();
        resultsRepository.persist(results);
        return "OK";
    }

    private  List<Result>  rank() {
        Collection<Company> companyList = companyRepository.getCompanyList();

        return companyList
                .stream()
                .map(company -> new Result(company, getBoostAnalizers()))
                .sorted()
                .collect(Collectors.toList());
    }

    private List<BoostAnalizer> getBoostAnalizers( ) {
        return Arrays.asList(
                new BoostAnalizer(1, lucroProcessor),
                new BoostAnalizer(1, roeProcessor),
                new BoostAnalizer(1, dividaProcessor)
        );
    }
}
