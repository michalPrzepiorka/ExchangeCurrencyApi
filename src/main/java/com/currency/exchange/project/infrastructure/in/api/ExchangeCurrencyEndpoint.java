package com.currency.exchange.project.infrastructure.in.api;

import com.currency.exchange.project.domain.Exchanger;
import com.currency.exchange.project.domain.Money;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Currency;

@RestController
public class ExchangeCurrencyEndpoint {
    private final Exchanger exchanger;

    public ExchangeCurrencyEndpoint(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    @PostMapping(value = "/exchange")
    public ConversionResponse exchange(@RequestBody @Valid ConversionRequest conversionRequest) {
        Money input = toInputMoney(conversionRequest);
        Currency targetCurrency = toInputCurrency(conversionRequest);

        Money result = exchanger.exchange(input, targetCurrency);

        return toResponse(result);
    }

    private Money toInputMoney(ConversionRequest conversionRequest) {
        return new Money(
                conversionRequest.getInputAmount(),
                Currency.getInstance(conversionRequest.getInputCurrency())
        );
    }

    private Currency toInputCurrency(ConversionRequest conversionRequest) {
        return Currency.getInstance(conversionRequest.getTargetCurrency());
    }

    private ConversionResponse toResponse(Money input) {
        return new ConversionResponse(
                String.format("%.2f %s", input.getAmount(), input.getCurrency().getCurrencyCode())
        );
    }
}
