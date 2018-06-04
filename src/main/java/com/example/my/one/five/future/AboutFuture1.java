package com.example.my.one.five.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AboutFuture1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final int max = 10;

        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            int finalI = i;
            futures.add(executorService.submit(() -> process(finalI)));
        }

        for (Future future : futures) {
            System.out.println("Printing Output:::" + future.get());
        }

        executorService.awaitTermination(10000, TimeUnit.MILLISECONDS);
        executorService.shutdown();

    }

    private static int process(int value) throws InterruptedException {
        System.out.println("processing " + value + " with wait time of: " + value + " sec...");
        Thread.sleep(value * 1000);
        System.out.println("Processed " + value);
        return value;
    }
}
