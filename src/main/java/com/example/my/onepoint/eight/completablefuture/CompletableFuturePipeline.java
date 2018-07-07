package com.example.my.onepoint.eight.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFuturePipeline {
    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = new CompletableFuture();
        System.out.println("Is Done?? : " + completableFuture.isDone());
        process(completableFuture);
        System.out.println("Is Done?? : " + completableFuture.isDone());

        completableFuture.complete(2);
        System.out.println("Is Done?? : " + completableFuture.isDone());

        completableFuture.cancel(true);

        System.out.println("Is Canceled?? : " + completableFuture.isCancelled());

        CompletableFuture<Integer> completableFuture1 = new CompletableFuture();

        processThrowsException(completableFuture1);

        completableFuture1.complete(6);

        System.out.println("Is Completed Exceptionally?? : " + completableFuture1.isCompletedExceptionally());

    }

    //pipeline
    private static void process(CompletableFuture<Integer> completableFuture) {
        System.out.println("Starting to process completableFuture...");
        completableFuture.thenApply(CompletableFuturePipeline::transform)
                .thenAccept(CompletableFuturePipeline::print);
    }

    //pipeline
    private static void processThrowsException(CompletableFuture<Integer> completableFuture) {
        completableFuture.thenApply(CompletableFuturePipeline::transformThrowsException)
                .exceptionally(CompletableFuturePipeline::handleException)
                .thenAccept(CompletableFuturePipeline::print);

        System.out.println("Is Completed Exceptionally?? : " + completableFuture.isCompletedExceptionally());
    }

    private static Integer transformThrowsException(Integer integer) {
        throw new RuntimeException("Something went wrong..");
    }

    private static Integer transform(Integer o) {
        return o * 4;
    }

    private static int handleException(Throwable throwable) {
        System.out.println("Exception Thrown:: " + throwable.getMessage());
        throw new RuntimeException();
    }

    private static void print(Integer o) {
        System.out.println("After the transformation the value obtained is: " + o);
    }
}
