package xyz.qiuqian.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 关于锁的8个问题
 *  1. 标准情况下，两个线程是先发短信还是先打电话？
 *  2. 发短信的方法延迟4秒，两个线程是先发短信还是先打电话？
 *      synchronized 锁的对象是方法的调用者，两个方法用同一个锁，谁先拿到锁谁先执行！
 *  3.
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        // 锁的存在
        new Thread(()-> {
            phone.sendMsg();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()-> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
    // synchronized 锁的对象是方法的调用者
    // 两个方法用同一个锁，谁先拿到锁谁先执行！

    public synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendMsg");
    }

    public synchronized void call() {
        System.out.println("call");
    }
}
