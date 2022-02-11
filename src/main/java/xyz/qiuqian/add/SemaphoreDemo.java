package xyz.qiuqian.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 停车位 3个停车位，6个车
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 参数为线程数量
        // 限流时也会用
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(()-> {
                // acquire() 得到
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
                // release() 释放
            }, String.valueOf(i)).start();
        }
    }
}
