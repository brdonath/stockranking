package com.dontah.controller;

import com.dontah.domain.Company;
import com.dontah.processors.BoostAnalizer;
import com.dontah.processors.impl.DividaProcessor;
import com.dontah.processors.impl.LucroProcessor;
import com.dontah.processors.impl.ROEProcessor;
import com.dontah.repository.CompanyRepository;
import com.dontah.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private  CompanyRepository companyRepository;

    @Autowired DividaProcessor dividaProcessor;
    @Autowired ROEProcessor roeProcessor;
    @Autowired LucroProcessor lucroProcessor;

    @RequestMapping("/")
    public String hello(
            @RequestParam(value="lucroProcessorBoost", required=false, defaultValue = "0") int lucroProcessorBoost,
            @RequestParam(value="roeProcessorBoost", required=false, defaultValue = "0") int roeProcessorBoost,
            Model model) {
        List<Result> results = rank(lucroProcessorBoost,roeProcessorBoost);
        model.addAttribute("results", results);
        model.addAttribute("boostAnalizers",results.get(0).getBoostAnalizerList());
        return "template";
    }


    private  List<Result>  rank(
            int lucroProcessorBoost,
            int roeProcessorBoost) {
        List<Company> companyList = companyRepository.getCompanyList();

        return companyList
                .stream()
                .map(company -> new Result(company, getBoostAnalizers(lucroProcessorBoost, roeProcessorBoost)))
                .sorted()
                .collect(Collectors.toList());
    }

    private List<BoostAnalizer> getBoostAnalizers(int lucroProcessorBoost, int roeProcessorBoost) {
        return Arrays.asList(
                new BoostAnalizer(lucroProcessorBoost, lucroProcessor),
                new BoostAnalizer(roeProcessorBoost, roeProcessor)
        );
    }

}
