package com.dontah.service;

import com.dontah.config.LocalConfig;
import com.dontah.domain.Company;
import com.dontah.domain.ResultEntity;
import com.dontah.processors.BoostAnalizer;
import com.dontah.processors.impl.DividaProcessor;
import com.dontah.processors.impl.LucroProcessor;
import com.dontah.processors.impl.ROEProcessor;
import com.dontah.repository.CompanyRepository;
import com.dontah.repository.ResultsRepository;
import com.dontah.service.extractor.StockDataExtractor;
import com.dontah.service.extractor.StockNamesExtractor;
import com.dontah.utils.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Bruno on 22/08/14.
 */
@Component
public class Main {

    @Autowired CompanyRepository companyRepository;
    @Autowired ResultsRepository resultsRepository;
    @Autowired StockNamesExtractor stockNamesExtractor;
    @Autowired StockDataExtractor stockDataExtractor;

    @Autowired LucroProcessor lucroProcessor;
    @Autowired ROEProcessor roeProcessor;
    @Autowired DividaProcessor dividaProcessor;

    public static void main(String[] args) throws Exception {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(LocalConfig.class);
        Main main = applicationContext.getBean(Main.class);
        main.start(args);
    }

    private void start(String[] args) throws Exception {
//        stockNamesExtractor.extract();
//        stockDataExtractor.extract();

//        List<Result> results = rank();
//        resultsRepository.persist(transform(results));
//        System.out.println("cheguei");

        tempName();
    }

    private void tempName() throws IOException {
        Collection<Company> companyNames = companyRepository.getCompanyNames();
        for (Company companyName : companyNames) {
            String s = String.format(Constants.HTTP_STOCK_DATA, companyName.getCodBolsa());
            Document doc = Jsoup.connect(s).get();
            Elements select = doc.select(".multiplus thead tr");

        }
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

    private List<ResultEntity> transform(List<Result> results) {
        return results.stream().map(this::transform).collect(Collectors.toList());
    }

    private ResultEntity transform(Result result) {
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCompany(result.getCompany());
        resultEntity.setFinalResult(result.getResult());
        resultEntity.setCodBolsa(result.getCompany().getCodBolsa());
        for (BoostAnalizer boostAnalizer : result.getBoostAnalizerList()) {
            if(boostAnalizer.getProcessor() instanceof LucroProcessor){
                resultEntity.setLucro(boostAnalizer.getResult());
            }
            if(boostAnalizer.getProcessor() instanceof DividaProcessor){
                resultEntity.setDivida(boostAnalizer.getResult());
            }
            if(boostAnalizer.getProcessor() instanceof ROEProcessor){
                resultEntity.setRoe(boostAnalizer.getResult());
            }
        }
        return resultEntity;
    }
}
