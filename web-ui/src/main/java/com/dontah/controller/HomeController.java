package com.dontah.controller;

import com.dontah.domain.ResultEntity;
import com.dontah.service.CompanyService;
import com.dontah.service.ResultService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Value("${infinite.offset}")
    private int offset;

    @Autowired
    private ResultService resultService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("results",resultService.findAll(0,offset));
        model.addAttribute("companyNames", companyService.findAll());
        model.addAttribute("hasNext", true);

        return "template";
    }

    @RequestMapping("/infinite")
    public String infinite(
            @RequestParam(value = "whereAmI") int whereAmI, Model model) {
        List<ResultEntity> list = resultService.findAll(whereAmI/ offset, offset);
        model.addAttribute("results", list);
        model.addAttribute("hasNext", !list.isEmpty() &&  list.size() == offset);
        return "data";
    }

    @RequestMapping("/company/{cod}")
    public String getCompany(
            @PathVariable String cod, Model model) {

        if(StringUtils.isBlank(cod)) return "";

        model.addAttribute("results", resultService.findAll(Arrays.asList(cod.split(","))));
        model.addAttribute("hasNext", false);

        return "data";
    }
}
