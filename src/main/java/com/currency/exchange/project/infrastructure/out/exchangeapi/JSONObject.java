package com.currency.exchange.project.infrastructure.out.exchangeapi;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class JSONObject {
    String no;
    String effectiveDate;
    BigDecimal bid;
    BigDecimal ask;
}
