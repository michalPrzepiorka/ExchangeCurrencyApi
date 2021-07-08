package com.currency.exchange.project.infrastructure.out.exchangeapi;

import java.util.List;

public class CurrencyResponse {
    private final List<RatesResponse> rates;
    private final String code;

    public CurrencyResponse(List<RatesResponse> rates, String code) {
        this.rates = rates;
        this.code = code;
    }

    public List<RatesResponse> getRates() {
        return rates;
    }

    public String getCode() {
        return code;
    }
}
