package com.dontah.service;

import com.dontah.domain.Company;
import com.dontah.processors.BoostAnalizer;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Bruno on 19/07/14.
 */
public class Result implements Comparable<Result>{
    private Company company;
    private List<BoostAnalizer> boostAnalizerList;
    private BigDecimal result;

    public Result(Company company,  List<BoostAnalizer> boostAnalizerList) {
        this.company = company;
        this.boostAnalizerList = boostAnalizerList;
        this.result = calculateResult();
    }

    private BigDecimal calculateResult(){
        int boosts = 0;
        BigDecimal scores = BigDecimal.ZERO;
        for (BoostAnalizer boostAnalizer : boostAnalizerList) {
            boosts += boostAnalizer.getBoost();
            scores = scores.add(boostAnalizer.getFinalScore(company));
        }

        BigDecimal divisor = BigDecimal.valueOf(boosts);
        if(divisor.compareTo(BigDecimal.ZERO) == 0){
            return divisor;
        }
        result = scores.divide(divisor,3,BigDecimal.ROUND_HALF_UP);
        return result;
    }

    public BigDecimal getResult() {
        return result;
    }

    public Company getCompany() {
        return company;
    }

    public List<BoostAnalizer> getBoostAnalizerList() {
        return boostAnalizerList;
    }

    @Override
    public int compareTo(Result o) {
        return o.result.compareTo(this.result);
    }

    @Override
    public String toString() {
        return "Result{" +
                "company=" + company.getCodBolsa() +
                ", boostAnalizerList=" + boostAnalizerList +
                ", result=" + result +
                '}';
    }
}
