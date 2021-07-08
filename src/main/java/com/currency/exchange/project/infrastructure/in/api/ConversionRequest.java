package com.currency.exchange.project.infrastructure.in.api;

import com.currency.exchange.project.domain.ValidCurrency;
import com.currency.exchange.project.infrastructure.in.api.constraints.AnyOfEnumValues;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
public class ConversionRequest {
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal inputAmount;
    @AnyOfEnumValues(ValidCurrency.class)
    private String inputCurrency;
    @AnyOfEnumValues(ValidCurrency.class)
    private String targetCurrency;
}
