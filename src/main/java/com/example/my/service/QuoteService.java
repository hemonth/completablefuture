package com.example.my.service;

import com.example.my.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuoteService {

    @Autowired
    private RestTemplate restTemplate;

    public QuoteService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public String generateQuote(){
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote.getValue().getQuote();
    }

    public Quote getNewQuote(){
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote;
    }

}
