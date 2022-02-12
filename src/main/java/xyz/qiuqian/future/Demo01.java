package xyz.qiuqian.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步调用： Ajax
 *  异步执行
 *  成功回调
 *  失败回调
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的 runAsync 异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + "runAsync => Void");
//        });
//
//        System.out.println("111");
//
//        completableFuture.get(); // 获取阻塞执行结果

        // 有返回值的 supplyAsync 异步回调
        // Ajax 成功和失败的回调
        // 失败： 返回错误信息
        CompletableFuture<Integer> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " => supplyAsync=>Integer");
            int i = 10 / 0;
            return 200;
        });

        uCompletableFuture.whenComplete((t, u) -> {
            System.out.println("t => " + t);
            System.out.println("u => " + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 500; // 可以获取到错误的返回结果
        }).get();
    }
}
