package com.dontah.controller;

import com.dontah.domain.Company;
import com.dontah.processors.BoostAnalizer;
import com.dontah.processors.impl.DividaProcessor;
import com.dontah.processors.impl.LucroProcessor;
import com.dontah.processors.impl.ROEProcessor;
import com.dontah.repository.CompanyRepository;
import com.dontah.repository.ResultsRepository;
import com.dontah.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    public static class Params{
        int whereAmI = 0;
        int offset = 30;
    }

    @Autowired
    private  CompanyRepository companyRepository;

    @Autowired
    private ResultsRepository resultsRepository;

    @Autowired DividaProcessor dividaProcessor;
    @Autowired ROEProcessor roeProcessor;
    @Autowired LucroProcessor lucroProcessor;

    @RequestMapping("/")
    public String home(
            Params params,
            Model model) {
        model.addAttribute("results", resultsRepository.list(params.whereAmI, params.offset));
        return "template";
    }

    @RequestMapping("/infinite")
    public String home(
            @RequestParam(value = "whereAmI") int whereAmI,
            Model model) {
        model.addAttribute("results", resultsRepository.list(whereAmI, new Params().offset));
        return "data";
    }

//    @RequestMapping("/calc")
//    @ResponseBody
    public String calcula(
            Params params) {
        List<Result> results = rank(params);
        resultsRepository.persist(results);
        return "OK";
    }

    private  List<Result>  rank(Params params) {
        Collection<Company> companyList = companyRepository.getCompanyList();

        return companyList
                .stream()
                .map(company -> new Result(company, getBoostAnalizers(params)))
                .sorted()
                .collect(Collectors.toList());
    }

    private List<BoostAnalizer> getBoostAnalizers(Params params ) {
        return Arrays.asList(
                new BoostAnalizer(1, lucroProcessor),
                new BoostAnalizer(1, roeProcessor),
                new BoostAnalizer(1, dividaProcessor)
        );
    }

}
