package com.currency.exchange.project;

import com.currency.exchange.project.domain.ExchangeRates;
import com.currency.exchange.project.domain.ExchangeRatesProvider;
import com.currency.exchange.project.infrastructure.in.api.ConversionRequest;
import com.currency.exchange.project.infrastructure.out.exchangeapi.RatesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ExchangeCurrencyEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ExchangeRatesProvider exchangeRatesProvider;

    @Test
    public void wheneverExchangingToTheSameCurrencyTheResultIsTheSame() throws Exception {
        //given:
        ConversionRequest request = new ConversionRequest();

        request.setInputAmount(BigDecimal.TEN);
        request.setInputCurrency("PLN");
        request.setTargetCurrency("PLN");
        //when: + then:
        mockMvc.perform(
                post("/exchange")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"exchangedInput\":\"10.00 PLN\"}"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenExchangeToForeignCurrencyShouldReturnExchangedValueWithSingleCommission() throws Exception {
        //given:
        ConversionRequest request = new ConversionRequest();

        request.setInputAmount(BigDecimal.TEN);
        request.setInputCurrency("PLN");
        request.setTargetCurrency("EUR");

        Mockito.when(exchangeRatesProvider.getFor(Currency.getInstance("EUR")))
                .thenReturn(
                        new ExchangeRates(
                                Currency.getInstance("EUR"),
                                Collections.singletonList(new RatesResponse("pp", "11", BigDecimal.valueOf(3.0), BigDecimal.valueOf(3.5)))
                        )
                );
        //when: + then:
        mockMvc.perform(
                post("/exchange")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"exchangedInput\":\"3.26 EUR\"}"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void whenExchangeForeignCurrencyToBasicCurrencyShouldReturnExchangedValueWithSingleCommission() throws Exception {
        //given:
        ConversionRequest request = new ConversionRequest();

        request.setInputAmount(BigDecimal.TEN);
        request.setInputCurrency("EUR");
        request.setTargetCurrency("PLN");

        Mockito.when(exchangeRatesProvider.getFor(Currency.getInstance("EUR")))
                .thenReturn(
                        new ExchangeRates(
                                Currency.getInstance("EUR"),
                                Collections.singletonList(new RatesResponse("pp", "11", BigDecimal.valueOf(3.0), BigDecimal.valueOf(3.5)))
                        )
                );
        //when: + then:
        mockMvc.perform(
                post("/exchange")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"exchangedInput\":\"34.30 PLN\"}"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenExchangeTwoForeignCurrencyFirstSellInputCurrencyAndThenBuyTargetCurrencyExpectedValueIsWithTwiceCommission() throws Exception {
        //given:
        ConversionRequest request = new ConversionRequest();

        request.setInputAmount(BigDecimal.TEN);
        request.setInputCurrency("EUR");
        request.setTargetCurrency("USD");

        Mockito.when(exchangeRatesProvider.getFor(Currency.getInstance("EUR")))
                .thenReturn(
                        new ExchangeRates(
                                Currency.getInstance("EUR"),
                                Collections.singletonList(new RatesResponse("pp", "11", BigDecimal.valueOf(3.0), BigDecimal.valueOf(3.5)))
                        )
                );

        Mockito.when(exchangeRatesProvider.getFor(Currency.getInstance("USD")))
                .thenReturn(
                        new ExchangeRates(
                                Currency.getInstance("USD"),
                                Collections.singletonList(new RatesResponse("pp", "11", BigDecimal.valueOf(2.0), BigDecimal.valueOf(2.5)))
                        )
                );

        //when: + then:
        mockMvc.perform(
                post("/exchange")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(request))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{\"exchangedInput\":\"16.81 USD\"}"))
                .andDo(MockMvcResultHandlers.print());
    }
}
