package xyz.qiuqian.pool;

import java.util.concurrent.*;

// Executors 工具类，三大方法
public class Demo01 {
    public static void main(String[] args) {
        // 获取 CPU 核心数量
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 自定义线程池
        /**
         * 银行业务窗口
         *  两个开放，三个不开放
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        try {
            // 最大承载 = 队列 + max值
            // 超出最大承载被拒绝策略拒绝，且抛出异常 java.util.concurrent.RejectedExecutionException
            for (int i = 1; i <= 5; i++) {
    //            new Thread().start(); // 传统方式
                // 使用线程池创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " => ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
