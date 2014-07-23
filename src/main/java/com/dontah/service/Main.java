package com.dontah.service;

import com.dontah.domain.Company;
import com.dontah.processors.BoostAnalizer;
import com.dontah.processors.impl.LucroProcessor;
import com.dontah.processors.impl.ROEProcessor;
import com.dontah.report.ReportWriter;
import com.dontah.repository.CompanyRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bruno on 14/07/14.
 */
public class Main {

    public static void main(String[] args) throws Exception {

//        new StockNamesExtractor().extract();
//        new StockDataExtractor().extract();

        rank();
    }

    private static void rank() {
        List<Company> companyList = CompanyRepository.getCompanyList();

        List<Result> results = companyList
                .stream()
                .map(company -> new Result(company, getBoostAnalizers()))
                .sorted()
                .collect(Collectors.toList());

        new ReportWriter(results, getBoostAnalizers());

    }

    private static List<BoostAnalizer> getBoostAnalizers() {
        return Arrays.asList(
                new BoostAnalizer(2, new LucroProcessor()),
                new BoostAnalizer(1, new ROEProcessor())
        );
    }
}
