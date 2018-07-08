package com.example.my.onepoint.eight.completablefuture;

import com.example.my.service.QuoteService;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureAcceptEither {
    public static void main(String[] args) throws InterruptedException {

        //prints either if the completable future results
        getSpringQuote()
                .acceptEither(getSpringQuote(), System.out::println);

        //go work on both the completable futures & which ever completes first will go and apply toUpperCase transformation
        getSpringQuote()
                .applyToEither(getHelloMessage(), line -> line.toUpperCase())
                .thenAccept(System.out::println);

        Thread.sleep(4000);
    }

    public static CompletableFuture<String> getSpringQuote() {
        QuoteService service = new QuoteService(new RestTemplateBuilder());
        return CompletableFuture.supplyAsync(() -> service.generateQuote());
    }

    public static CompletableFuture<String> getHelloMessage() {
        return CompletableFuture.supplyAsync(() -> "Hello Hemonth");
    }
}
