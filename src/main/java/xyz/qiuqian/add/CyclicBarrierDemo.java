package xyz.qiuqian.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 集齐七颗龙珠召唤神龙

        // 召唤龙珠的线程

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()-> {
            System.out.println("done!");
        });

        for (int i = 1; i <= 7; i++) {
            // Lambda 能操作到 i 吗 ？ 不能 需要使用 final 修饰
            final int temp = i;
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "收集了 " + temp + " 个龙珠");

                // 这个辅助类是通过await来记数的，await之后本次线程会被阻塞，直到done后才执行下面的
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, String.valueOf(i)).start();
        }
    }
}
