package com.dontah.processors.impl;

import com.dontah.domain.Balance;
import com.dontah.domain.Company;
import com.dontah.processors.Processor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Collection;

/**
 * Created by Bruno on 25/07/14.
 */
@Component
public class DividaProcessor implements Processor {

    public static BigDecimal  convertDiv(String roe) throws ParseException {
        DecimalFormat decimalFormat = new DecimalFormat("0.0#");
        decimalFormat.setParseBigDecimal(true);
        return (BigDecimal) decimalFormat.parse(roe);
    }

    @Override
    public BigDecimal process(Company t) {
        Processor processor = getProcessor(t);
        return processor.process(t);
    }

    private Processor getProcessor(Company t) {
        Balance balance = t.getBalanceList().iterator().next();
        if(balance.getDivDivLL()!= null){
            return new DivProcessor();
        }
        return new PddProcessor();
    }

    @Override
    public String getName() {
        return "Dívida";
    }

    class PddProcessor extends DividaProcessor{

        @Override
        public BigDecimal process(Company t) {
            Collection<Balance> balanceCollection = t.getBalanceList();

            BigDecimal score = BigDecimal.ZERO;

            for (Balance balance : balanceCollection) {
                try {
                    BigDecimal div = convertDiv(balance.getPddDivLL());
                    if(div.compareTo(BigDecimal.valueOf(0.75)) <= 0){
                        score = score.add(BigDecimal.ONE);
                    }else if(div.compareTo(BigDecimal.ONE) <= 0 ){
                        score = score.add(new BigDecimal(".5"));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
           return score.divide(BigDecimal.valueOf(balanceCollection.size()), 3, BigDecimal.ROUND_HALF_UP);
        }
    }

    class DivProcessor extends DividaProcessor{

        @Override
        public BigDecimal process(Company t) {
            Collection<Balance> balanceCollection = t.getBalanceList();
            BigDecimal score = BigDecimal.ZERO;

            for (Balance balance : balanceCollection) {
                try {
                    if(balance.getDivDivLL().trim().equalsIgnoreCase("P")){
                        score = score.add(BigDecimal.ZERO);
                        continue;
                    }else if(balance.getDivDivLL().trim().equalsIgnoreCase("-")){
                        score = score.add(BigDecimal.ONE);
                        continue;
                    }

                    BigDecimal div= convertDiv(balance.getDivDivLL());
                    if(div.compareTo(new BigDecimal(4))>= 0){
                        score = score.add(BigDecimal.ZERO);
                    }else{

                        BigDecimal divide = div.divide(new BigDecimal(4), 3, BigDecimal.ROUND_HALF_UP);
                        divide = divide.subtract(BigDecimal.ONE).abs();
                        score = score.add(divide);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return score.divide(BigDecimal.valueOf(balanceCollection.size()), 3, BigDecimal.ROUND_HALF_UP);
        }
    }
}
