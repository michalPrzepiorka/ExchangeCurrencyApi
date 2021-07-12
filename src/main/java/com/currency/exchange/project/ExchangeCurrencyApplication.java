package com.currency.exchange.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ExchangeCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeCurrencyApplication.class, args);
    }

}
