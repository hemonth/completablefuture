package com.example.my.onepoint.eight.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

//Completable futures are nothing but promises in JavaScript..
public class CompletableFuture1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        CompletableFuture.runAsync(() -> System.out.println("Running Async method in "+Thread.currentThread()));
        CompletableFuture.runAsync(() -> System.out.println("Running Async method in "+Thread.currentThread()),forkJoinPool);

        // completable future supplier
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() ->{
            System.out.println("In  completableFuture supplyAsync method..");
            return 4;
        });

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() ->{
            System.out.println("In completableFuture1 supplyAsync method..");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 4;
        });


//When the main thread stops, program terminates (JVM terminates).
//Making the main thread sleep for a while giving deamon threads time to execute.
//JVM terminates itself when all user threads finish their execution.
//If JVM finds running daemon thread, it terminates the thread and after that shutdown itself.
//JVM does not care whether Daemon thread is running or not.
        Thread thread = Thread.currentThread();
//        thread.sleep(1000);
        System.out.println("Is Deamon Thread:: "+thread.isDaemon());
        System.out.println("In Main Method...");

        System.out.println("Value returned form supplier:: "+completableFuture.get());
        // getNow method is much better than get method..
        System.out.println("completable future getNow.."+ completableFuture.getNow(-1000));
        //as completableFuture1 sleep time is greater than main thread execution time, completableFuture1 returns value -1000
        System.out.println("completable future 1 getNow.."+ completableFuture1.getNow(-1000));
    }
}
