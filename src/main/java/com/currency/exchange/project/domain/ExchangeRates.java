package com.currency.exchange.project.domain;

import com.currency.exchange.project.infrastructure.out.exchangeapi.JSONObject;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

public class ExchangeRates {
    private final Currency currency;
    private final List<JSONObject> rates;

    public ExchangeRates(Currency currency, List<JSONObject> rates) {
        this.currency = currency;
        this.rates = rates;
    }

    public Rate rateTo(Currency targetCurrency) {
        return Optional.of(targetCurrency.getCurrencyCode())
                .map(e -> rates.stream().findFirst().get())
                .map(rate -> new Rate(rate.getBid(), targetCurrency))
                .orElseThrow(() -> new RuntimeException(String.format("Currency code %s not available", targetCurrency.getCurrencyCode())));
    }
}
