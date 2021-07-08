package com.currency.exchange.project.infrastructure.out.exchangeapi;

import feign.Param;
import feign.RequestLine;

public interface CurrencyClient {
    @RequestLine("GET /api/exchangerates/rates/C/{code}/?format=json")
    CurrencyResponse findByCode(@Param("code") String code);
}
