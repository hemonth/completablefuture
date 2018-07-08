package com.example.my.onepoint.eight.completablefuture;

import com.example.my.service.QuoteService;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureThenCombine {

    public static void main(String[] args) throws InterruptedException {
        CompletableFutureThenCombine completableFutureThenCombine = new CompletableFutureThenCombine();
        QuoteService quoteService = new QuoteService(new RestTemplateBuilder());

        final CompletableFuture<String> future1 = completableFutureThenCombine.generateQuote(quoteService);
        final CompletableFuture<String> future2 = completableFutureThenCombine.generateQuote(quoteService);

        future1.thenCombine(future2, (line1, line2) -> line1.concat(" :: " + line2)).thenAccept(System.out::println);

        Thread.sleep(2000); //keeping the main thread alive until worker threads execute the job
    }

    public CompletableFuture<String> generateQuote(QuoteService service) {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> service.generateQuote());
        return completableFuture;
    }

}
