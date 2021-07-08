package com.currency.exchange.project.domain;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;

@Service
public class Exchanger {
    private final ExchangeRatesProvider exchangeRatesProvider;

    public Exchanger(ExchangeRatesProvider exchangeRatesProvider) {
        this.exchangeRatesProvider = exchangeRatesProvider;
    }

    public Money exchange(Money input, Currency targetCurrency) {
//        Money inputCurrency = new Money(BigDecimal.ONE, Currency.getInstance("PLN"));
        Rate plnRate = new Rate(BigDecimal.ONE, Currency.getInstance("PLN"));
        if (!input.getCurrency().equals(Currency.getInstance("PLN"))) {
            ExchangeRates inputCurrencyExchangeRates = exchangeRatesProvider.getFor(input.getCurrency());
            plnRate = inputCurrencyExchangeRates.rateToBid(targetCurrency);
        }

        Money money = input.toCurrency(plnRate);

        Rate targetPlnRate = new Rate(BigDecimal.ONE, Currency.getInstance("PLN"));
        if (!targetCurrency.equals(Currency.getInstance("PLN"))) {
            ExchangeRates targetCurrencyExchangeRates = exchangeRatesProvider.getFor(targetCurrency);
            targetPlnRate = targetCurrencyExchangeRates.rateToAsk(targetCurrency);
        }
        Money result = money.divideBy(targetPlnRate);
        return result;
    }
}
