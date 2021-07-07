package com.currency.exchange.project.domain;

import java.math.BigDecimal;
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

    public Money multiplyBy(BigDecimal value){
        return new Money(amount.multiply(value), currency);
    }

    public Money toCurrency(Rate rate){
        return new Money(
                amount.multiply(rate.getRate()),
                rate.getTargetCurrency()
        );
    }
}
