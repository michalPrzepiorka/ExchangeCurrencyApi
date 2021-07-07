package com.currency.exchange.project.infrastructure.in.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConversionRequest {
    private BigDecimal inputAmount;
    private String inputCurrency;
    private String targetCurrency;
}
