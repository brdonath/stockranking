package com.dontah.processors;

import com.dontah.domain.Company;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Bruno on 19/07/14.
 */
public class BoostAnalizer{
    private int boost;
    private Processor processor;
    private BigDecimal result;
    private BigDecimal resultWithBoostApplied;

    public BoostAnalizer(int boost, Processor processor) {
        this.boost = boost;
        this.processor = processor;
    }

    public BigDecimal getFinalScore(Company c){
        result = processor.process(c);
        resultWithBoostApplied =  result.multiply(BigDecimal.valueOf(boost));
        return resultWithBoostApplied;
    }

    public int getBoost() {
        return boost;
    }

    public Processor getProcessor() {
        return processor;
    }

    public BigDecimal getResult() {
        return result;
    }

    public BigDecimal getResultWithBoostApplied() {
        if(resultWithBoostApplied == null){
            return new BigDecimal(BigInteger.ZERO,3);
        }
        return resultWithBoostApplied;
    }

    @Override
    public String toString() {
        return "BoostAnalizer{" +
                "boost=" + boost +
                ", processor=" + processor.getClass().getCanonicalName() +
                ", result=" + result +
                '}';
    }
}
