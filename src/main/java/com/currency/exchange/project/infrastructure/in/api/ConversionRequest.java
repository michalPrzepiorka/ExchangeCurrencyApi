package com.currency.exchange.project.infrastructure.in.api;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
public class ConversionRequest {
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal inputAmount;
    private String inputCurrency;
    private String targetCurrency;
}
