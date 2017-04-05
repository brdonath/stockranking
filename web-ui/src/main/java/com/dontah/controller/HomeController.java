package com.dontah.controller;

import com.dontah.domain.Company;
import com.dontah.domain.ResultEntity;
import com.dontah.repository.CompanyRepository;
import com.dontah.repository.ResultsRepository;
import com.dontah.service.CompanyService;
import com.dontah.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    public static class Params {
        int whereAmI = 0;
        int offset = 30;
    }

    @Autowired
    private ResultService resultService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/")
    public String home(Params params, Model model) {
        model.addAttribute("results",resultService.findAll(0,params.offset));
        model.addAttribute("companyNames", companyService.findAll());
        model.addAttribute("hasNext", true);
        return "template";
    }

    @RequestMapping("/infinite")
    public String infinite(
            @RequestParam(value = "whereAmI") int whereAmI, Model model) {
        int offset = new Params().offset;
        List<ResultEntity> list = resultService.findAll(whereAmI/ offset, offset);
        model.addAttribute("results", list);
        model.addAttribute("hasNext", !list.isEmpty() &&  list.size() == offset);
        return "data";
    }

    @RequestMapping("/company/{cod}")
    public String getCompany(
            @PathVariable String cod, Model model) {
        model.addAttribute("results", resultService.findAll(Arrays.asList(cod.split(","))));
        model.addAttribute("hasNext", false);

        return "data";
    }
}
