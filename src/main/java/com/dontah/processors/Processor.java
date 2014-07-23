package com.dontah.processors;

import com.dontah.domain.Company;

import java.math.BigDecimal;

/**
 * Created by Bruno on 19/07/14.
 */
public interface Processor  {
    BigDecimal process(Company t);
}
