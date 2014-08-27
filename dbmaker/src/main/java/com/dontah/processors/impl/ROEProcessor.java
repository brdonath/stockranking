package com.dontah.processors.impl;

import com.dontah.domain.Balance;
import com.dontah.domain.Company;
import com.dontah.processors.Processor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Bruno on 22/07/14.
 */
@Component
public class ROEProcessor implements Processor {

    BigDecimal GOOD_ROE = BigDecimal.valueOf(.30);

    @Override
    public BigDecimal process(Company t) {
        Set<Balance> balanceList = t.getBalanceList();

        BigDecimal scores = BigDecimal.ZERO;

        BigDecimal div = BigDecimal.ZERO;
        for (Balance balance : balanceList) {
            try {
                div = getROE(balance.getRoe()).divide(GOOD_ROE, 3, BigDecimal.ROUND_HALF_UP);
                if (div.compareTo(BigDecimal.ONE) > 0) {
                    div = BigDecimal.ONE;
                }
                scores = scores.add(div);
            } catch (ParseException ignored) {
            }
        }
        return scores.divide(BigDecimal.valueOf(balanceList.size()), 3, BigDecimal.ROUND_HALF_UP);
    }

    @Override
    public String getName() {
        return "ROE";
    }

    public BigDecimal getROE(String roe) throws ParseException {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("0.0#%",symbols);
        decimalFormat.setParseBigDecimal(true);
        return (BigDecimal) decimalFormat.parse(roe);
    }
}