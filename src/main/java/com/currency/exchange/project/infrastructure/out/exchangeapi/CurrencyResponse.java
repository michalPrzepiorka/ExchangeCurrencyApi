package com.currency.exchange.project.infrastructure.out.exchangeapi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CurrencyResponse {
    private final List<JSONObject> rates;
    private final String code;

    public CurrencyResponse(List<JSONObject> rates, String code) {
        this.rates = rates;
        this.code = code;
    }

//    public Map<List<JSONObject>, BigDecimal> getRates() {
//        return rates;
//    }


    public List<JSONObject> getRates() {
        return rates;
    }

    public String getCode() {
        return code;
    }
}
