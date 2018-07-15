package com.example.my.onepoint.eight.completablefuture;

import java.util.concurrent.CompletableFuture;

//there is also a method allOf
public class CompletableFutureAnyOf {
    public static void main(String[] args) {
        CompletableFuture<Integer> a1 = CompletableFuture.supplyAsync(() -> generate(2));
        CompletableFuture<Integer> a2 = CompletableFuture.supplyAsync(() -> generate(3));
        CompletableFuture<Integer> a3 = CompletableFuture.supplyAsync(() -> generate(5));
        CompletableFuture<Integer> a4 = CompletableFuture.supplyAsync(() -> generate(5));
        CompletableFuture<Double> a5 = CompletableFuture.supplyAsync(() -> generate(10) * 1.0);
        CompletableFuture<Void> timeout = CompletableFuture.supplyAsync(() -> timeout(1));

        //the behavior is identical to whatever the first responder does
//        CompletableFuture.anyOf(a1, a2, a3, a4, a5, timeout)
        CompletableFuture.anyOf(timeout, a1, a4)
                .thenAccept(System.out::println)
                .exceptionally(CompletableFutureAnyOf::handleException);

        invokeSleep(3000);
    }

    private static Void timeout(int i) {
        invokeSleep((int) (Math.random() * 1000));
        throw new RuntimeException("Processing Timeout error..");
    }

    private static Void handleException(Throwable throwable) {
        System.out.println("Error:: " + throwable);
        throw new RuntimeException("oops");
    }

    private static Integer generate(int i) {
        invokeSleep((int) (Math.random() * 1000));
        if (i == 5) {
            throw new RuntimeException("oops 5 is an invalid number..");
        }
        return i;
    }

    private static void invokeSleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
