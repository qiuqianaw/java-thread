package xyz.qiuqian.bq;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * SynchronousQueue 不存储元素，put 了一个元素，必须从里面先 take 出，否则不能再 put 值进去
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        // 同步队列
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue();

        new Thread(() -> {
            try {
                // 写入一个元素后必须等待拿出后才能继续写入
                System.out.println(Thread.currentThread().getName() + " put 1 ");
                synchronousQueue.put("1");

                System.out.println(Thread.currentThread().getName() + " put 2 ");
                synchronousQueue.put("2");

                System.out.println(Thread.currentThread().getName() + " put 3 ");
                synchronousQueue.put("3");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " => " + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " => " + synchronousQueue.take());

                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " => " + synchronousQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
