package com.dontah.controller;

import com.dontah.domain.ResultEntity;
import com.dontah.repository.CompanyRepository;
import com.dontah.repository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    public static class Params{
        int whereAmI = 0;
        int offset = 30;
    }

    @Autowired CompanyRepository companyRepository;

    @Autowired ResultsRepository resultsRepository;

    @RequestMapping("/")
    public String home(
            Params params,
            Model model) {
        model.addAttribute("results", resultsRepository.list(0, params.offset));
        model.addAttribute("companyNames", companyRepository.getCompanyNames());
        model.addAttribute("hasNext", true);
        return "template";
    }

    @RequestMapping("/infinite")
    public String infinite(
            @RequestParam(value = "whereAmI") int whereAmI,
            Model model) {
        List<ResultEntity> list = resultsRepository.list(whereAmI, new Params().offset);
        model.addAttribute("results", list);
        model.addAttribute("hasNext", hasNext(list));
        return "data";
    }

    @RequestMapping("/company/{cod}")
    public String getCompany(
            @PathVariable String cod,
            Model model) {
        model.addAttribute("results", resultsRepository.get(cod));
        model.addAttribute("hasNext", false);

        return "data";
    }

    private boolean hasNext(List<ResultEntity> list){
        return !(list == null || list.isEmpty()) &&
                list.get(list.size() - 1).getOrder() <= companyRepository.getCount();
    }
}