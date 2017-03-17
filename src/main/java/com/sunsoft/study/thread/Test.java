package com.sunsoft.study.thread;


import java.util.StringTokenizer;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Wangwei
 * @date 2017/2/6
 * @desc
 */
public class Test {

    static ExecutorService executor = Executors.newFixedThreadPool(5);

    private static void test0() throws ExecutionException, InterruptedException {
        Future<String> result = executor.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return "hello";
        });
        System.out.println(result.get());
    }
    private static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "hello";
        }, executor);
        System.out.println(resultCompletableFuture.get());
    }

    private static void test2() {
        CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return "hello";
        },executor);
        System.out.println(resultCompletableFuture.thenAcceptAsync(s -> {
            System.out.println(s);
            System.out.println(Thread.currentThread().getName());
        },executor));
        System.out.println(123);
    }
    private static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "3", executor);

        CompletableFuture<Integer> f2 = f1.thenApply(t -> {
            System.out.println(t);
            return Integer.valueOf(t);
        });

        CompletableFuture<Double> f3 = f2.thenApply(r -> r * 2.0);
        System.out.println(f3.get());
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* StringTokenizer itr = new StringTokenizer("word count");
        while (itr.hasMoreTokens()) {
            System.out.println(itr.nextToken());
        }*/
//        test0();
//        test1();
//        test2();
        test3();
        executor.shutdown();
    }
}
