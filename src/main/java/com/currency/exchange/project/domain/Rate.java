package com.currency.exchange.project.domain;

import java.math.BigDecimal;
import java.util.Currency;

public class Rate {
    private final BigDecimal rate;
    private final Currency targetCurrency;

    public Rate(BigDecimal rate, Currency targetCurrency) {
        this.rate = rate;
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }
}
