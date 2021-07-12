package com.currency.exchange.project.infrastructure.out.exchangeapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyClientProvider {
    @Bean
    public CurrencyClient currencyClient(CurrencyClientFactory clientFactory) {
        return clientFactory.createClient();
    }
}
