package com.currency.exchange.project.infrastructure.out.exchangeapi;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CurrencyClientFactory {
    private final URI uri;

    public CurrencyClientFactory() {
        uri = URI.create("http://api.nbp.pl");
    }

    public CurrencyClient createClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(CurrencyClient.class, uri.toString());
    }
}
