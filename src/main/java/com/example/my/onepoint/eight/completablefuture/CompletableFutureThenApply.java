package com.example.my.onepoint.eight.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureThenApply {

    public static void main(String[] args) throws InterruptedException {
        Supplier<Integer> generateInt = () -> {
            System.out.println("Generating value..");
            return 2;
        };
        Function<? super Integer, Float> transformToFloat = integer -> integer * 6.78f;
        Function<? super Integer, Double> transformToDouble = integer -> integer * 4.0;

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(generateInt);
        CompletableFuture<Double> completableFuture2 = completableFuture1.thenApply(transformToDouble);
        completableFuture2.thenAccept(i -> {
            System.out.println("Consuming value..");
            System.out.println("Final output is:: " + i);
        });

        //or
        // thenApply will take Function as argument, transforms and returns the value.
        // thenRun is a terminal operation which can be used for logging or reporting or closing
        CompletableFuture.supplyAsync(generateInt).thenApply(transformToFloat).thenAccept(System.out::println).thenRun(CompletableFutureThenApply::closingMethod);

        Thread.sleep(1000);
    }

    private static void closingMethod() {
        System.out.println("We are done!! \nTerminal operation called...");
    }

}
