package com.dontah.processors.impl;

import com.dontah.domain.Balance;
import com.dontah.domain.Company;
import com.dontah.processors.Processor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Bruno on 19/07/14.
 */
@Component
public class LucroProcessor implements Processor {
    @Override
    public BigDecimal process(Company o) {
        Set<Balance> balanceList = o.getBalanceList();

        BigDecimal score = BigDecimal.ZERO;

        BigDecimal before = null;

        for (Balance balance : balanceList) {
            if(before == null ){
                before = BigDecimal.ZERO;
            }

            BigDecimal lucro = new BigDecimal(balance.getLucro());
            if(lucro.compareTo(BigDecimal.ZERO) > 0) {
                if ((lucro.compareTo(before) >= 0)) {
                    score = score.add(BigDecimal.ONE);
                } else {
                    score = score.add(new BigDecimal(.5));
                }
            }
            before = lucro;
        }
        return score.divide(BigDecimal.valueOf(balanceList.size()),3,BigDecimal.ROUND_HALF_UP);

    }

    @Override
    public String getName() {
        return "Lucro";
    }
}
