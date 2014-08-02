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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    public static class Params{
        int lucro = 1;
        int roe = 1;
        int div = 1;


        public int getLucro() {
            return lucro;
        }

        public void setLucro(int lucro) {
            this.lucro = lucro;
        }

        public int getRoe() {
            return roe;
        }

        public void setRoe(int roe) {
            this.roe = roe;
        }

        public int getDiv() {
            return div;
        }

        public void setDiv(int div) {
            this.div = div;
        }
    }

    @Autowired
    private  CompanyRepository companyRepository;

    @Autowired DividaProcessor dividaProcessor;
    @Autowired ROEProcessor roeProcessor;
    @Autowired LucroProcessor lucroProcessor;

    @RequestMapping("/")
    public String home(
            Params params,
            Model model) {
        List<Result> results = rank(params);
        model.addAttribute("results", results);
        model.addAttribute("boostAnalizers",results.get(0).getBoostAnalizerList());
        return "template";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    private  List<Result>  rank(Params params) {
        List<Company> companyList = companyRepository.getCompanyList();

        return companyList
                .stream()
                .map(company -> new Result(company, getBoostAnalizers(params)))
                .sorted()
                .collect(Collectors.toList());
    }

    private List<BoostAnalizer> getBoostAnalizers(Params params ) {
        return Arrays.asList(
                new BoostAnalizer(params.getLucro(), lucroProcessor),
                new BoostAnalizer(params.getRoe(), roeProcessor),
                new BoostAnalizer(params.getDiv(), dividaProcessor)
        );
    }

}
