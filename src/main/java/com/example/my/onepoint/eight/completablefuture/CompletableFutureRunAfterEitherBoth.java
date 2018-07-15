package com.example.my.onepoint.eight.completablefuture;

import java.util.concurrent.CompletableFuture;

//runAfterEither runAfterBoth
public class CompletableFutureRunAfterEitherBoth {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> odd = CompletableFuture.supplyAsync(() -> generate(3));
        CompletableFuture<Integer> even = CompletableFuture.supplyAsync(() -> generate(2));

        odd.runAfterEither(even, () -> System.out.println("Perform some kind of side effect. Can be anything like database call"))
                .exceptionally(CompletableFutureRunAfterEitherBoth::handleException);
        odd.runAfterBoth(even, () -> System.out.println("Perform some kind of side effect. Can be anything like database call"))
                .exceptionally(CompletableFutureRunAfterEitherBoth::handleException);


        Thread.sleep(3000);
    }

    private static Void handleException(Throwable throwable) {
        System.out.println("Error: " + throwable);
        throw new RuntimeException("end the program..");
    }


    private static Integer generate(int i) {
        if (i == 2) {
            invokeSleep(2000);
            return i;
        }
        if (i == 3) {
//            Thread.sleep(2000);
            throw new RuntimeException("oops");
//            return i;
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
