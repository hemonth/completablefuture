package com.example.my.onepoint.eight.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CompletableFutureAsyncMethods {

    static Consumer<Integer> printOutput = output -> {
        System.out.println("Output is:: " + output);
        System.out.println("Thread printing the output is.. " + Thread.currentThread());
    };

    //either comment out one of the sleep methods to compare and see difference in outputs.
    private static Supplier<Integer> calculate = () -> {
        sleep(500);
//        sleep(2000);
        System.out.println("Calculation running in the Thread:: " + Thread.currentThread());
        return 2;
    };


    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            System.out.println("Exception Thrown.. " + ex);
        }
    }

    public static void main(String[] args) {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(calculate);
        sleep(1000);
        //If you dont use thenAccept "Async" the thread is either executed by thread that completes the computable or the thread that calls the thenAceept.
        //by the time it comes into thenAccept method if the feature has completed then the thenAccept will run in main method.
        completableFuture.thenAccept(printOutput);
        //with async it always runs in completely different thread, and the thread is within fork join common pool or we can specify the pool to run.
        completableFuture.thenAcceptAsync(printOutput);

        ForkJoinPool taskpool = new ForkJoinPool(10);
        completableFuture.thenAcceptAsync(printOutput, taskpool);

        sleep(5000);

    }

}
