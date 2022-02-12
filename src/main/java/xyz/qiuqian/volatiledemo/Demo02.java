package xyz.qiuqian.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

// volatile 不保证原子性
public class Demo02 {

    // 原子类Integer

    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        /**
         * 这个函数在字节码中可以查看到一共分为三步
         *  1 获取这个值
         *  2 +1
         *  3 写回这个值
         */
        // num++; // 不是一个原子性操作
        num.getAndIncrement(); // 使用的是底层 CAS
    }

    public static void main(String[] args) {

        // 此时理论上结果为20000
        for (int i = 1; i <= 20 ; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while ( Thread.activeCount() > 2 ) { // main GC
            Thread.yield(); // 礼让
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
