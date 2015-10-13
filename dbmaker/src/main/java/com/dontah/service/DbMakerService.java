package com.dontah.service;

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
import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by Bruno on 22/08/14.
 */
@Component
public class DbMakerService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ResultsRepository resultsRepository;
    @Autowired
    StockNamesExtractor stockNamesExtractor;
    @Autowired
    StockDataExtractor stockDataExtractor;

    @Autowired
    LucroProcessor lucroProcessor;
    @Autowired
    ROEProcessor roeProcessor;
    @Autowired
    DividaProcessor dividaProcessor;

    @Autowired
    SessionFactory sessionFactory;

    public void make() throws Exception {
        backUpDb();
//        stockNamesExtractor.extract();
        stockDataExtractor.extract();

        List<Result> results = rank();
        resultsRepository.persist(transform(results));
        sessionFactory.getCache().evictAllRegions();
    }

    private void backUpDb() throws IOException {
        FileUtils.copyFile(
                        new File(ClassLoader.getSystemResource("mydb.sqlite").getFile()),new File("mydb.sqlite-backup"));
    }

    private List<Result> rank() {
        Collection<Company> companyList = companyRepository.getCompanyList();

        companyList.forEach(a ->
                a.setBalanceList(
                        a.getBalanceList().stream()
                                .filter(b -> Integer.parseInt(b.getPk().getAno()) >= 2009)
                                .collect(Collectors.toCollection(TreeSet::new))
                ));

        return companyList
                .stream()
                .map(company -> new Result(company, getBoostAnalizers()))
                .sorted()
                .collect(Collectors.toList());
    }

    private List<BoostAnalizer> getBoostAnalizers() {
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
            if (boostAnalizer.getProcessor() instanceof LucroProcessor) {
                resultEntity.setLucro(boostAnalizer.getResult());
            }
            if (boostAnalizer.getProcessor() instanceof DividaProcessor) {
                resultEntity.setDivida(boostAnalizer.getResult());
            }
            if (boostAnalizer.getProcessor() instanceof ROEProcessor) {
                resultEntity.setRoe(boostAnalizer.getResult());
            }
        }
        return resultEntity;
    }

    public void rollback() {
        try {
            FileUtils.copyFile(
                   new File("mydb.sqlite-backup"), new File(ClassLoader.getSystemResource("mydb.sqlite").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        sessionFactory.getCache().evictAllRegions();
    }
}
