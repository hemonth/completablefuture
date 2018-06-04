package com.example.my.onepoint.eight.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//avoid get method, get method is blocking method.
public class CompletableFuture2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 4;
        });

        CompletableFuture.supplyAsync(() ->  7 ).thenAccept(System.out::println);

        Thread.sleep(1000);

        //get is a blocking method, It stops the main to execute util the value returns
        System.out.println("Future get Method returns.. " + future.get());

        //thenAccept is non-blocking. Try commenting out either one of these and see difference in output.
//        System.out.println("Future thenAccept Method returns.. "+ future.thenAccept(System.out::println));

        System.out.println("In Main..");
        Thread.sleep(4000);
        System.out.println("In main completed..");
    }
}
