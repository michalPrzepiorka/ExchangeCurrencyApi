package com.currency.exchange.project.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money toCurrency(Rate rate) {
        return new Money(
                amount.multiply(rate.getRate())
                        .subtract((amount.multiply(rate.getRate()))
                                .multiply(BigDecimal.valueOf(0.02))),
                rate.getTargetCurrency()
        );
    }

    public Money divideBy(Rate targetRate) {
        return new Money(
                amount.divide(targetRate.getRate(), 2, RoundingMode.HALF_UP)
                        .subtract((amount.divide(targetRate.getRate(), 2, RoundingMode.HALF_UP))
                                .multiply(BigDecimal.valueOf(0.02))),
                targetRate.getTargetCurrency()
        );
    }
}
