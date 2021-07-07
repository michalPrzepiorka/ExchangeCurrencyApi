package com.currency.exchange.project.domain;

import java.util.Currency;

public interface ExchangeRatesProvider {
    ExchangeRates getFor(Currency currency);
}
