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
        Rate plnRate = new Rate(BigDecimal.ONE, Currency.getInstance("PLN"));

        Money money = input.toCurrency(plnRate, BigDecimal.ZERO);
        if (!input.getCurrency().equals(Currency.getInstance("PLN"))) {
            ExchangeRates inputCurrencyExchangeRates = exchangeRatesProvider.getFor(input.getCurrency());
            plnRate = inputCurrencyExchangeRates.rateToAsk(targetCurrency);
            money = input.toCurrency(plnRate, BigDecimal.valueOf(0.02));
        }

        Rate targetPlnRate = new Rate(BigDecimal.ONE, Currency.getInstance("PLN"));
        Money result = money.divideBy(targetPlnRate, BigDecimal.ZERO);
        if (!targetCurrency.equals(Currency.getInstance("PLN"))) {
            ExchangeRates targetCurrencyExchangeRates = exchangeRatesProvider.getFor(targetCurrency);
            targetPlnRate = targetCurrencyExchangeRates.rateToBid(targetCurrency);
            result = money.divideBy(targetPlnRate, BigDecimal.valueOf(0.02));
        }
        return result;
    }
}
