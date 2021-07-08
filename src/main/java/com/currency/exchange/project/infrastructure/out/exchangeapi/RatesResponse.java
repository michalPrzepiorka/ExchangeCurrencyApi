package com.currency.exchange.project.infrastructure.out.exchangeapi;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RatesResponse {
    String no;
    String effectiveDate;
    BigDecimal bid;
    BigDecimal ask;
}
