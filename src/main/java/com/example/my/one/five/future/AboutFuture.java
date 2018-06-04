package com.example.my.one.five.future;

import java.util.concurrent.*;

import static java.lang.System.out;

public class AboutFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> future = executorService.submit(() -> {
            out.println("In Callable call method returning integer");
            return 2;
        });

        Future<Integer> future1 = executorService.submit(() -> process(1000));

        // waiting for the result to come by checking isDone
        while(!future1.isDone()){
            System.out.println("In while loop waiting for result..");
        }


        out.println("In main method:: ");
        out.println("Async future returned:: " + future.get());
        out.println("Async future1 returned:: "+ future1.get());

        executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
        executorService.shutdown();
    }

    public static Integer process(int sleepTime) throws InterruptedException {
        Thread.sleep(sleepTime);
        System.out.println("In Process method..");
        return 5;
    }
}
