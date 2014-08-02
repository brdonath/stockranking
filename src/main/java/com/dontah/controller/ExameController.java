package com.dontah.controller;

import com.dontah.service.exame.ExameNamesExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bruno on 01/08/14.
 */
@Controller
public class ExameController {

    @Autowired
    ExameNamesExtractor exameNamesExtractor;

    @RequestMapping("/exame")
    @ResponseBody
    public String home() throws Exception {
        exameNamesExtractor.extract();
        return "OK";
    }
}
