package com.example.my.onepoint.eight.completablefuture;

import com.example.my.service.QuoteService;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureThenCompose {
    public static void main(String[] args) throws InterruptedException {
//If you have a function that returns CompletableFuture the use "thenCompose"
        getRandomValue()
                .thenCompose(value -> doubleTheValue(value))
                .thenAccept(System.out::println);

        Thread.sleep(3000);

    }

    private static CompletableFuture<Long> doubleTheValue(Long value) {
        System.out.println("Doubling the value given " + value);
        return CompletableFuture.supplyAsync(() -> value * 2);
    }

    private static CompletableFuture<Long> getRandomValue() {
        QuoteService service = new QuoteService(new RestTemplateBuilder());
        return CompletableFuture.supplyAsync(() -> service.getNewQuote().getValue().getId());
    }

}
