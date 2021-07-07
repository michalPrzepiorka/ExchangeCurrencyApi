package com.currency.exchange.project.infrastructure.out.exchangeapi;

import com.currency.exchange.project.domain.ExchangeRates;
import com.currency.exchange.project.domain.ExchangeRatesProvider;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class ExternalClientBasedExchangeRatesProviderAdapter implements ExchangeRatesProvider {
    private final CurrencyClient currencyClient;

    public ExternalClientBasedExchangeRatesProviderAdapter(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    @Override
    public ExchangeRates getFor(Currency currency) {
        CurrencyResponse currencyResponse = currencyClient.findByCode(currency.getCurrencyCode());

        return new ExchangeRates(currency, currencyResponse.getRates());
    }
}
